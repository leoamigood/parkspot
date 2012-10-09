package com.amigood.park.dot;

import com.amigood.dot.domain.Location;
import com.amigood.dot.domain.ParkingSign;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;

/**
 * @author leo@amigood.com | Leo Amigood, Chain Tale LLC
 *         Date: 9/7/12
 *         Time: 11:37 AM
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/conf/spring-test.xml")
public class HibernateConfigTest {

    @Autowired
    private SessionFactory sessionFactory;

    @Test
    public void getParkingSignLocation() {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Location l where l.borough=:borough and l.sign=:sign").setCharacter("borough", 'B').setString("sign", "P-004958");
        Location location = (Location) query.uniqueResult();

        assertEquals("P-004958", location.getSign());
        assertEquals("RANDALL AVENUE", location.getMainStreet());
        assertEquals("FAILE STREET", location.getFromStreet());
        assertEquals("COSTER STREET", location.getToStreet());
        assertEquals("N", location.getOrientation());

        assertEquals(8, location.getSigns().size());

        ParkingSign sign = location.getSigns().get(3);
        assertEquals("1 HOUR PARKING 9AM-7PM EXCEPT SUNDAY", sign.getDescription());
        assertEquals(30, sign.getDistance().intValue());
        assertNull(sign.getDirection());

        session.close();
    }

}
