package com.amigood.park.service;

import com.amigood.domain.Coordinates;
import com.amigood.domain.PostalAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author leo@amigood.com | Leo Amigood, Chain Tale LLC
 *         Date: 8/19/12
 *         Time: 4:38 PM
 */
@Service
public class LocationManagerImpl implements LocationManager {

    @Autowired
    private RestTemplate rest;

    private String api = "http://maps.googleapis.com/maps/api";

    public PostalAddress geoCode(Coordinates coordinates) {
        String url = String.format("%s/geocode/json?latlng=%s&sensor=false", api, coordinates);

        return rest.getForEntity(url, PostalAddress.class).getBody();
    }
}
