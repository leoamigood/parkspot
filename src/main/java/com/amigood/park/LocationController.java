package com.amigood.park;

import com.amigood.domain.Coordinates;
import com.amigood.domain.LocationAddress;
import com.amigood.domain.Protocol;
import com.amigood.park.service.LocationManagerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * User: leo@amigood.com | Leo Amigood
 * Date: 8/15/12
 * Time: 4:48 PM
 */
@Controller
public class LocationController {

    @Autowired
    private LocationManagerImpl manager;

    private Protocol protocol = Protocol.JSON;

    // Using regexp matching as setting useDefaultSuffixPattern to false
    // in RequestMappingHandlerMapping or DefaultAnnotationHandlerMapping does not seem to help
    @RequestMapping(method= RequestMethod.GET, value={"/location/{latitude},{longitude:.+}", "/location/{latitude},{longitude}/"})
    @ResponseBody
    public LocationAddress getAddress(@PathVariable String latitude, @PathVariable String longitude, Model model) {

        LocationAddress address = manager.findLocation(new Coordinates(latitude, longitude), protocol);
        return address;
    }

    public LocationManagerImpl getManager() {
        return manager;
    }

    public void setManager(LocationManagerImpl manager) {
        this.manager = manager;
    }

    public Protocol getProtocol() {
        return protocol;
    }

    public void setProtocol(Protocol protocol) {
        this.protocol = protocol;
    }

}
