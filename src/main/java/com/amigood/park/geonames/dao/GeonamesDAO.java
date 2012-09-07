package com.amigood.park.geonames.dao;

import com.amigood.domain.Coordinates;
import com.amigood.domain.Intersection;

import java.util.List;

/**
 * @author leo@amigood.com | Leo Amigood, Chain Tale LLC
 *         Date: 9/7/12
 *         Time: 10:25 AM
 */
public interface GeonamesDAO {
    public List<Intersection> findIntersection(Coordinates coordinates);
}
