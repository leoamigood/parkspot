package com.amigood.dot.util;

import com.amigood.domain.Coordinates;
import com.amigood.dot.domain.Location;
import com.vividsolutions.jts.util.Assert;
import org.junit.Test;

import java.util.Comparator;

/**
 * @author leo@amigood.com | Leo Amigood, Chain Tale LLC
 *         Date: 11/5/12
 *         Time: 6:25 PM
 */
public class LocationComparatorTest {

    @Test
    public void testComparator() {
        Coordinates coordinates = new Coordinates("40.686073","-73.963719");
        Comparator comparator = new LocationComparator(coordinates);

        Location l1 = new Location();
        l1.setSign("Sign A");
        l1.setFromLat(40.68478);
        l1.setFromLng(-73.963072);
        l1.setToLat(40.686977);
        l1.setToLng(-73.963509);
        l1.setLength(0.002240039731786919);

        Location l2 = new Location();
        l2.setSign("Sign B");
        l2.setFromLat(40.6868692);
        l2.setFromLng(-73.9644718);
        l2.setToLat(40.684668);
        l2.setToLng(-73.964033);
        l2.setLength(0.002244510387584477);

        Location l3 = new Location();
        l3.setSign("Sign C");
        l3.setFromLat(40.686977);
        l3.setFromLng(-73.963509);
        l3.setToLat(40.6870904);
        l3.setToLng(-73.9625382);
        l3.setLength(0.00097740073665296);

        Location l3a = new Location();
        l3a.setSign("Sign C");
        l3a.setFromLat(40.686977);
        l3a.setFromLng(-73.963509);
        l3a.setToLat(40.6870904);
        l3a.setToLng(-73.9625382);
        l3a.setLength(0.00097740073665296);

        Assert.equals(-1, comparator.compare(l1, l2));
        Assert.equals(1, comparator.compare(l2, l1));
        Assert.equals(-1, comparator.compare(l2, l3));
        Assert.equals(1, comparator.compare(l3, l1));
        Assert.equals(0, comparator.compare(l3, l3a));
    }
}
