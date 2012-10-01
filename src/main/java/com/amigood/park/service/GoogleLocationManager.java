package com.amigood.park.service;

import com.amigood.domain.Coordinates;
import com.amigood.domain.LocationAddress;
import com.amigood.domain.Protocol;
import com.amigood.park.exception.LocationException;
import com.amigood.park.google.AddressComponent;
import com.amigood.park.google.GoogleGeoResponse;
import com.amigood.park.google.GoogleResponseStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author leo@amigood.com | Leo Amigood, Chain Tale LLC
 *         Date: 8/19/12
 *         Time: 4:38 PM
 */
@Service
public class GoogleLocationManager implements LocationManager {
    private static final Logger logger = LoggerFactory.getLogger(GoogleLocationManager.class);

    private String api = "http://maps.googleapis.com/maps/api";

    @Autowired
    private RestTemplate template;

    private Protocol protocol = Protocol.JSON;

    public Coordinates findIntersection(LocationAddress address1, LocationAddress address2) throws LocationException {
        String url = String.format("%s/geocode/%s?address=%s+and+%s&components=intersection&sensor=false", api, protocol, address1, address2);

        try {
            GoogleGeoResponse geo = template.getForEntity(url, GoogleGeoResponse.class).getBody();
            if (geo.getStatus() != GoogleResponseStatus.OK) {
                throw new LocationException(geo.getStatus().toString());
            }

            if (geo.getComponents().size() == 1 &&
                    geo.getComponents().get(0).getTypes().contains(AddressComponent.Type.INTERSECTION)) {
                return geo.getComponents().get(0).getGeometry().getCoordinates();
            } else {
                return null;
            }
        } catch (HttpMessageNotReadableException e) {
            throw new LocationException(address1 + " and " + address2, e.getCause());
        }
    }

    public RestTemplate getTemplate() {
        return template;
    }

    public void setTemplate(RestTemplate template) {
        this.template = template;
    }

    public Protocol getProtocol() {
        return protocol;
    }

    public void setProtocol(Protocol protocol) {
        this.protocol = protocol;
    }
}
