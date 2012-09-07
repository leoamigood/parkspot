package com.amigood.park.geonames.dao;

import com.amigood.domain.Coordinates;
import com.amigood.domain.Intersection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static junit.framework.Assert.assertEquals;

/**
 * @author leo@amigood.com | Leo Amigood, Chain Tale LLC
 *         Date: 9/7/12
 *         Time: 10:36 AM
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/conf/spring-test.xml")
public class GeonamesDAOImplTest {

    @Autowired
    GeonamesDAO dao;

    @Test
    public void testDAO() {
        List<Intersection> intersections = dao.findIntersection(new Coordinates());

        assertEquals(5, intersections.size());

        Intersection intersection = intersections.get(0);
        assertEquals("Bay Parkway", intersection.getStreet1());
        assertEquals("76th Street", intersection.getStreet2());
        assertEquals(40.607566, intersection.getLat());
        assertEquals(-73.987965, intersection.getLng());
        assertEquals(0.02, intersection.getDistance());
    }
}
