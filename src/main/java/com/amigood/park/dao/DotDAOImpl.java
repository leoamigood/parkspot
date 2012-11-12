package com.amigood.park.dao;

import com.amigood.domain.Coordinates;
import com.amigood.dot.domain.Location;
import com.amigood.dot.util.LocationComparator;
import com.amigood.log.Loggable;
import com.amigood.park.exception.LocationException;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.SessionFactory;
import org.hibernate.spatial.criterion.SpatialRestrictions;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

/**
 * @author leo@amigood.com | Leo Amigood, Chain Tale LLC
 *         Date: 9/7/12
 *         Time: 10:51 AM
 */
@Repository
public class DotDAOImpl implements DotDAO {
    @Loggable
    private static Logger logger;

    @Autowired
    private SessionFactory sessionFactory;

    @Cacheable(value = "closestLocations", key = "#coordinates.toString() + #distance")
    public List<Location> getLocations(Coordinates coordinates, Double distance) throws LocationException {
        String geometry = String.format("Polygon((%f %f,%f %f,%f %f,%f %f,%f %f))", coordinates.getNearbyArea(distance));
        try {
            Geometry filter = (new WKTReader()).read(geometry);

            Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Location.class);
            criteria.setFetchMode("signs", FetchMode.JOIN);
            criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            criteria.add(SpatialRestrictions.within("centerGeo", filter));
            List<Location> locations = criteria.list();

            Collections.sort(locations, new LocationComparator(coordinates));

            return locations;
        } catch (ParseException e) {
            logger.error("Can't parse geometry: {}", geometry.toString());
            throw new LocationException(e.getMessage(), e);
        }
    }

}
