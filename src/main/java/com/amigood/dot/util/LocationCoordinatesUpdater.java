package com.amigood.dot.util;

import com.amigood.domain.Coordinates;
import com.amigood.domain.LocationAddress;
import com.amigood.dot.domain.Location;
import com.amigood.dot.domain.ParkingSign;
import com.amigood.park.exception.IntersectionException;
import com.amigood.park.exception.LocationException;
import com.amigood.park.service.GoogleLocationManager;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author leo@amigood.com | Leo Amigood, Chain Tale LLC
 *         Date: 9/7/12
 *         Time: 10:30 PM
 */
@Component
public class LocationCoordinatesUpdater {
    private static final Logger logger = LoggerFactory.getLogger(LocationCoordinatesUpdater.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private GoogleLocationManager manager;

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/conf/spring-beans.xml");

        LocationCoordinatesUpdater updater = context.getBean(LocationCoordinatesUpdater.class);
        updater.updateLocations();
    }

    @SuppressWarnings("unchecked")
    public long updateLocations() {
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

                try {
                    Coordinates from = manager.findIntersection(mainAddress, fromAddress);
                    Coordinates to = manager.findIntersection(mainAddress, toAddress);

                    location.setCoordinates(from, to);
                    location.setCenterLat((from.getLatitude() + to.getLatitude()) / 2);
                    location.setCenterLng((from.getLongitude() + to.getLongitude()) / 2);
                    location.setLength(Math.sqrt(Math.pow(from.getLatitude() - to.getLatitude(), 2) + Math.pow(from.getLongitude() - to.getLongitude(), 2)));
                    location.setValidated(true);
                } catch (IntersectionException ie) {
                    logger.error("Cant find location for " + location.getSign() + ": " + ie.getMessage());
                    location.setValidated(true);
                }

                if ( ++i % 100 == 0 ) {
                    session.flush();
                }
                System.out.print(i + " rows processed.\r");
            }
        } catch (LocationException e) {
            logger.error("Failed to update location coordinates!", e);
        } finally {
            session.flush();
            session.close();
            System.out.print(i + " rows updated.\r");
        }

        return i;
    }
}
