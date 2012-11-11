package com.amigood.park.dao;

import com.amigood.domain.Coordinates;
import com.amigood.dot.domain.Location;
import com.amigood.test.SpatialTests;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author leo@amigood.com | Leo Amigood, Chain Tale LLC
 *         Date: 9/21/12
 *         Time: 12:19 PM
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/conf/spring-test.xml")
@Category(SpatialTests.class)
@Transactional
public class DotDAOImplTest {

    @Autowired
    DotDAO dao;

    @Test
    public void testGetLocations() throws Exception {
        List<Location> locations = dao.getLocations(new Coordinates("40.57676732328746", "-73.96716132152508"), 279.0);

    	Assert.assertNotNull(locations);
        Assert.assertEquals(10, locations.size());

        Location location = locations.get(0);
        Assert.assertEquals("P-234686", location.getSign());
        Assert.assertEquals("BRIGHTON 1 STREET", location.getMainStreet());
        Assert.assertEquals("BRIGHTON BEACH AVENUE", location.getFromStreet());
        Assert.assertEquals("BRIGHTON 1 PLACE", location.getToStreet());
        Assert.assertEquals("E", location.getOrientation());
        Assert.assertEquals(Location.Borough.K, location.getBorough());
    }

}
