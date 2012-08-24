package com.amigood.park.service;

import com.amigood.domain.Coordinates;
import com.amigood.domain.Protocol;
import com.amigood.park.google.GeoResponse;
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

    public GeoResponse geoCode(Coordinates coordinates, Protocol protocol) {
        String url = String.format("%s/geocode/%s?latlng=%s&sensor=false", api, protocol, coordinates);

        GeoResponse response = rest.getForEntity(url, GeoResponse.class).getBody();
        return response;
    }
}
