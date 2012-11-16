package com.amigood.dot.util;

import com.amigood.domain.Coordinates;
import com.amigood.domain.LocationAddress;
import com.amigood.dot.domain.Location;
import com.amigood.dot.domain.ParkingSign;
import com.amigood.log.Loggable;
import com.amigood.mvc.interceptor.ClientHeaderInterceptor;
import com.amigood.park.exception.IntersectionException;
import com.amigood.park.exception.LocationException;
import com.amigood.park.service.GoogleLocationManager;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;
import org.apache.http.HttpHost;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.impl.client.DefaultHttpClient;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @author leo@amigood.com | Leo Amigood, Chain Tale LLC
 *         Date: 9/7/12
 *         Time: 10:30 PM
 *
 *  Run this class to update location of street cross by retrieving it via Google Maps API
 *  You may use proxy to avoid Google's quota limitations
 */
@Component
public class LocationCoordinatesUpdater {
    @Loggable
    private static Logger logger;

    private Random random = new Random();
    private WKTReader wktReader = new WKTReader();

    public static final int MAX_SLEEP_TIMEOUT = 1000;

    @Autowired
    private DefaultHttpClient httpClient;

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private GoogleLocationManager manager;

    public static void main(String[] args) throws InterruptedException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/conf/spring-beans.xml");

        LocationCoordinatesUpdater updater = context.getBean(LocationCoordinatesUpdater.class);
        //updater.initProxy(context);
        updater.updateLocations();
    }

    public void initProxy(ClassPathXmlApplicationContext context) {
        HttpHost proxy = new HttpHost("us.proxymesh.com", 31280, "http");
        httpClient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);

        //intercept request to print response headers
        RestTemplate template = context.getBean(RestTemplate.class);
        template.setInterceptors(Arrays.asList(new ClientHttpRequestInterceptor[] {context.getBean(ClientHeaderInterceptor.class)}));
    }

    @SuppressWarnings("unchecked")
    public long updateLocations() throws InterruptedException {
        int i = 0;
        Session session = sessionFactory.openSession();
        try {
            Criteria criteria = session.createCriteria(Location.class)
                                    .add(Restrictions.eq("validated", Boolean.FALSE))
                                    .add(Property.forName("sign")
                                            .in(DetachedCriteria.forClass(ParkingSign.class)
                                                .setProjection(Projections.distinct(Projections.property("number")))
                                            )
                                    );

            List<Location> locations = criteria.list();
            for (Location location: locations) {
                LocationAddress mainAddress = location.getAddress(location.getMainStreet());
                LocationAddress fromAddress = location.getAddress(location.getFromStreet());
                LocationAddress toAddress = location.getAddress(location.getToStreet());

                Coordinates from = null, to = null;
                try {
                    from = manager.findIntersection(mainAddress, fromAddress);
                    if (from == null) {
                        logger.error("Cant find location for " + location.getSign() + ": " + mainAddress + " and " + fromAddress);
                    }

                    to = manager.findIntersection(mainAddress, toAddress);
                    if (to == null) {
                        logger.error("Cant find location for " + location.getSign() + ": " + mainAddress + " and " + toAddress);
                    }

                    if (from != null && to != null) {
                        Coordinates center = new Coordinates((from.getLatitude() + to.getLatitude()) / 2, (from.getLongitude() + to.getLongitude()) / 2);
                        location.setCenter(center, wktReader);
                        location.setLength(Math.sqrt(Math.pow(from.getLatitude() - to.getLatitude(), 2) + Math.pow(from.getLongitude() - to.getLongitude(), 2)));
                    }
                } catch (IntersectionException ie) {
                    logger.error("Cant find location for " + location.getSign() + ": " + ie.getMessage());
                } catch (ParseException e) {
                    logger.error("Cant store location for " + location.getSign() + ": " + e.getMessage());
                } finally {
                    location.setCoordinates(from, to);
                    location.setValidated(true);
                }

                if (MAX_SLEEP_TIMEOUT > 0) Thread.sleep(random.nextInt(MAX_SLEEP_TIMEOUT));

                if ( ++i % 50 == 0 ) {
                    session.flush();
                }
                System.out.print(i + " rows processed.\r");
            }
        } catch (LocationException e) {
            logger.error("Failed to update location coordinates!", e);
        } finally {
            session.flush();
            session.close();
            System.out.println(i + " rows updated.");
        }

        return i;
    }
}
