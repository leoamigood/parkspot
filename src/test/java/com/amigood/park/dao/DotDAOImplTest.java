package com.amigood.park.dao;

import com.amigood.domain.Coordinates;
import com.amigood.dot.domain.Location;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @author leo@amigood.com | Leo Amigood, Chain Tale LLC
 *         Date: 9/21/12
 *         Time: 12:19 PM
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/conf/spring-test.xml")
public class DotDAOImplTest {

    @Autowired
    DotDAO dao;

    @Test
    public void testHappyGetLocations() throws Exception {
        List<Location> locations = dao.getLocations(new Coordinates("40.655891", "-73.93064"), 10);

        Assert.assertEquals(10, locations.size());

        Location location = locations.get(0);
        Assert.assertEquals("P-219746", location.getSign());
        Assert.assertEquals("UTICA AVENUE", location.getMainStreet());
        Assert.assertEquals("CLARKSON AVENUE", location.getFromStreet());
        Assert.assertEquals("LENOX ROAD", location.getToStreet());
        Assert.assertEquals("W", location.getOrientation());
        Assert.assertEquals(Location.Borough.K, location.getBorough());
    }

//    @Test
//    public void testSadGetLocations() throws Exception {
//    }

}
