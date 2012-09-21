package com.amigood.park.service;

import com.amigood.domain.Coordinates;
import com.amigood.domain.LocationAddress;
import com.amigood.park.exception.LocationException;
import com.amigood.park.yahoo.YahooGeoResponse;
import com.amigood.park.yahoo.YahooResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;

/**
 * @author leo@amigood.com | Leo Amigood, Chain Tale LLC
 *         Date: 9/18/12
 *         Time: 2:42 PM
 */
@Service
public class YahooLocationManager implements LocationManager {
    private String api = "http://where.yahooapis.com/geocode";

    @Autowired
    private RestTemplate template;

    public Coordinates findIntersection(LocationAddress address1, LocationAddress address2) throws LocationException {
        String street = URLEncoder.encode(address1.getStreet().trim());
        String cross = URLEncoder.encode(address2.getStreet().trim());
        String city = URLEncoder.encode(address1.getCity());
        String state = URLEncoder.encode(address1.getState());

        String url = String.format("%s?line1=%s+at+%s&line2=%s,+%s&appid=parkspot", api, street, cross, city, state);

        YahooGeoResponse geo = template.getForEntity(url, YahooGeoResponse.class).getBody();
        if (geo.getStatus() != YahooResponseStatus.OK) {
            throw new LocationException(geo.getStatus().toString());
        }

        if (geo.getFound() != 1) {
            throw new LocationException("Can't determine intersection");
        }

        return geo.getComponents().get(0).getCoordinates();
    }
}
