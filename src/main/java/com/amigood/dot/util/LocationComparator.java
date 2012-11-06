package com.amigood.dot.util;

import com.amigood.domain.Coordinates;
import com.amigood.dot.domain.Location;

import java.util.Comparator;

/**
 * @author leo@amigood.com | Leo Amigood, Chain Tale LLC
 *         Date: 11/5/12
 *         Time: 6:11 PM
 */
public class LocationComparator implements Comparator<Location> {

    private Coordinates center;

    public LocationComparator(Coordinates center) {
        this.center = center;
    }

    @Override
    public int compare(Location l1, Location l2) {
        double h1 = getTriangleHeight(l1);
        double h2 = getTriangleHeight(l2);

        if (h1 == h2) return 0;
        return h1 - h2 > 0 ? 1 : -1;
    }

    private double getTriangleHeight(Location location) {
        double a = location.getLength();
        double b = Math.sqrt(Math.pow(location.getFromLat() - center.getLatitude(), 2) + Math.pow(location.getFromLng() - center.getLongitude(), 2));
        double c = Math.sqrt(Math.pow(location.getToLat() - center.getLatitude(), 2) + Math.pow(location.getToLng() - center.getLongitude(), 2));
        double p = (a + b + c) / 2;

        return 2/a * Math.sqrt(p*(p-a)*(p-b)*(p-c));
    }

}
