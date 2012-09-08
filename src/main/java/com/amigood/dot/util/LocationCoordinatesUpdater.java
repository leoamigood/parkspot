package com.amigood.dot.util;

import com.amigood.domain.LocationAddress;
import com.amigood.dot.domain.Location;
import com.amigood.park.google.AddressComponent;
import com.amigood.park.google.exception.GoogleException;
import com.amigood.park.service.GoogleLocationManager;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
            Query query = session.createQuery("from Location l where l.fromLat is NULL or l.toLat is NULL or l.fromLng is NULL or l.toLng is NULL");
            List<Location> locations = query.list();

            List<AddressComponent> components = null;
            for (Location location: locations) {
                LocationAddress mainAddress = location.getAddress(location.getMainStreet());
                LocationAddress fromAddress = location.getAddress(location.getFromStreet());
                LocationAddress toAddress = location.getAddress(location.getToStreet());

                components = manager.findIntersection(mainAddress, fromAddress);
                if (components.size() > 1) {
                    logger.error("More than one intersections found for: {}", location);
                } else if (components.size() == 0) {
                    logger.error("NO intersections found for: {}", location);
                }

                for (AddressComponent component: components) {
                    location.setFromLat(Double.parseDouble(component.getGeometry().getCoordinates().getLatitude()));
                    location.setFromLng(Double.parseDouble(component.getGeometry().getCoordinates().getLongitude()));
                }

                components = manager.findIntersection(mainAddress, toAddress);
                if (components.size() > 1) {
                    logger.error("More than one intersections found for: {}", location);
                } else if (components.size() == 0) {
                    logger.error("NO intersections found for: {}", location);
                }

                for (AddressComponent component: components) {
                    location.setToLat(Double.parseDouble(component.getGeometry().getCoordinates().getLatitude()));
                    location.setToLng(Double.parseDouble(component.getGeometry().getCoordinates().getLongitude()));
                }

                if ( ++i % 100 == 0 ) {
                    session.flush();
                    session.clear();
                    System.out.print(i + " rows updated.\r");
                }
            }
        } catch (GoogleException e) {
            logger.error(e.getMessage());
        } finally {
            session.flush();
            session.close();
            System.out.print(i + " rows updated.\r");
        }

        return i;
    }
}
