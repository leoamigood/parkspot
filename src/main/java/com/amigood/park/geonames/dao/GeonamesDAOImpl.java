package com.amigood.park.geonames.dao;

import com.amigood.domain.Coordinates;
import com.amigood.domain.Intersection;
import com.amigood.park.geonames.GeonamesGeoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author leo@amigood.com | Leo Amigood, Chain Tale LLC
 *         Date: 9/7/12
 *         Time: 10:25 AM
 */
@Repository
public class GeonamesDAOImpl implements GeonamesDAO {

    @Autowired
    private RestTemplate template;

    @Override
    public List<Intersection> findIntersection(Coordinates coordinates) {
        String url = "http://api.geonames.org/findNearestIntersectionOSM?lat=40.607728&lng=-73.987902&username=leoamigood&maxRows=5";
        GeonamesGeoResponse response = template.getForEntity(url, GeonamesGeoResponse.class).getBody();

        return response.getIntersections();
    }
}
