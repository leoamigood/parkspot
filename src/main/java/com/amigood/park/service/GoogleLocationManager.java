package com.amigood.park.service;

import com.amigood.domain.Coordinates;
import com.amigood.domain.LocationAddress;
import com.amigood.domain.Protocol;
import com.amigood.park.google.AddressComponent;
import com.amigood.park.google.GoogleGeoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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

    public LocationAddress findLocation(Coordinates coordinates) {
        String url = String.format("%s/geocode/%s?latlng=%s&sensor=false", api, protocol, coordinates);

        GoogleGeoResponse geo = getTemplate().getForEntity(url, GoogleGeoResponse.class).getBody();
        return this.getAddress(geo);
    }

    private LocationAddress getAddress(GoogleGeoResponse response) {
        LocationAddress address = new LocationAddress();

        for (AddressComponent component: response.getComponents()) {
            for (String componentType: component.getTypes()) {
                if (componentType.equals(AddressComponent.STREET_ADDRESS)) {
                    for (AddressComponent.Entry entry: component.getEntries()) {
                        for (String entryType: entry.getTypes()) {
                            if (entryType.equals(AddressComponent.Element.STREET_NUMBER.toString())) {
                                address.setNumber(entry.getShortName());
                            } else if (entryType.equals(AddressComponent.Element.STREET_NAME.toString())) {
                                address.setStreet(entry.getShortName());
                            } else if (entryType.equals(AddressComponent.Element.CITY.toString())) {
                                address.setCity(entry.getShortName());
                            } else if (entryType.equals(AddressComponent.Element.STATE.toString())) {
                                address.setState(entry.getShortName());
                            } else if (entryType.equals(AddressComponent.Element.COUNTRY.toString())) {
                                address.setCountry(entry.getShortName());
                            } else if (entryType.equals(AddressComponent.Element.ZIP.toString())) {
                                address.setZip(entry.getShortName());
                            }
                        }
                    }
                }
            }
        }

        return address;
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