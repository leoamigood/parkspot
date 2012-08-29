package com.amigood.park.service;

import com.amigood.domain.Coordinates;
import com.amigood.domain.LocationAddress;
import com.amigood.domain.Protocol;
import com.amigood.park.google.AddressComponent;
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
    private String api = "http://maps.googleapis.com/maps/api";

    @Autowired
    private RestTemplate template;

    public LocationAddress findLocation(Coordinates coordinates, Protocol protocol) {
        String url = String.format("%s/geocode/%s?latlng=%s&sensor=false", api, protocol, coordinates);

        GeoResponse geo = getTemplate().getForEntity(url, GeoResponse.class).getBody();
        return this.getAddress(geo);
    }

    private LocationAddress getAddress(GeoResponse response) {
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

}
