package com.amigood.park;

import com.amigood.domain.Coordinates;
import com.amigood.domain.Protocol;
import com.amigood.park.google.GeoResponse;
import com.amigood.park.service.LocationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * User: leo@amigood.com | Leo Amigood
 * Date: 8/15/12
 * Time: 4:48 PM
 */
@Controller
public class LocationController {
    public static final String RESPONSE = "response";

    @Autowired
    private LocationManager manager;

    private Protocol protocol;

    // Using regexp matching as setting useDefaultSuffixPattern to false
    // in RequestMappingHandlerMapping or DefaultAnnotationHandlerMapping does not seem to help
    @RequestMapping(method= RequestMethod.GET, value={"/location/{latitude},{longitude:.+}", "/location/{latitude},{longitude}/"})
    public String getAddress(@PathVariable String latitude, @PathVariable String longitude, Model model) {

        GeoResponse response = manager.geoCode(new Coordinates(latitude, longitude), protocol);
        model.addAttribute(RESPONSE, response);
        return "location";
    }

    public Protocol getProtocol() {
        return protocol;
    }

    public void setProtocol(Protocol protocol) {
        this.protocol = protocol;
    }

}
