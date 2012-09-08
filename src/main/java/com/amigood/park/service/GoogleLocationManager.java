package com.amigood.park.service;

import com.amigood.domain.LocationAddress;
import com.amigood.domain.Protocol;
import com.amigood.park.google.AddressComponent;
import com.amigood.park.google.GoogleGeoResponse;
import com.amigood.park.google.GoogleResponseStatus;
import com.amigood.park.google.exception.GoogleException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author leo@amigood.com | Leo Amigood, Chain Tale LLC
 *         Date: 8/19/12
 *         Time: 4:38 PM
 */
@Service
public class GoogleLocationManager implements LocationManager {
    private String api = "http://maps.googleapis.com/maps/api";

    @Autowired
    private RestTemplate template;

    private Protocol protocol = Protocol.JSON;

    public List<AddressComponent> findIntersection(LocationAddress address1, LocationAddress address2) throws GoogleException {
        String url = String.format("%s/geocode/%s?address=%s+and+%s&sensor=false", api, protocol, address1, address2);

        GoogleGeoResponse geo = getTemplate().getForEntity(url, GoogleGeoResponse.class).getBody();
        if (geo.getStatus() != GoogleResponseStatus.OK) {
            throw new GoogleException(GoogleResponseStatus.OVER_QUERY_LIMIT);
        }

        return geo.getComponents();
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
