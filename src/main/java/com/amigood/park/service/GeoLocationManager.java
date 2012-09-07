package com.amigood.park.service;

import com.amigood.domain.Coordinates;
import com.amigood.domain.Intersection;
import com.amigood.park.dao.DotDAO;
import com.amigood.park.geonames.dao.GeonamesDAO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author leo@amigood.com | Leo Amigood, Chain Tale LLC
 *         Date: 9/7/12
 *         Time: 10:19 AM
 */
public class GeoLocationManager implements LocationManager {

    @Autowired
    private GeonamesDAO geoDao;

    @Autowired
    private DotDAO dotDao;

    public List<Intersection> findIntersections(Coordinates coordinates) {
        return geoDao.findIntersection(coordinates);
    }
}
