package com.amigood.park;

import com.amigood.domain.Coordinates;
import com.amigood.domain.PostalAddress;
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

    @Autowired
    private LocationManager manager;

    // Using regexp matching as setting useDefaultSuffixPattern to false
    // in RequestMappingHandlerMapping or DefaultAnnotationHandlerMapping does not seem to help
    @RequestMapping(method= RequestMethod.GET, value={"/location/{latitude},{longitude:.+}", "/location/{latitude},{longitude}/"})
    public String getAddress(@PathVariable String latitude, @PathVariable String longitude, Model model) {

        PostalAddress address = manager.geoCode(new Coordinates(latitude, longitude));
        model.addAttribute("address", address);
        return "location";
    }

}
