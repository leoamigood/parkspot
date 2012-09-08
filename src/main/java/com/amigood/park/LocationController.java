package com.amigood.park;

import com.amigood.domain.LocationAddress;
import com.amigood.park.service.GoogleLocationManager;
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
    private GoogleLocationManager manager;

    // Using regexp matching as setting useDefaultSuffixPattern to false
    // in RequestMappingHandlerMapping or DefaultAnnotationHandlerMapping does not seem to help
    @RequestMapping(method= RequestMethod.GET, value={"/location/{latitude},{longitude:.+}", "/location/{latitude},{longitude}/"})
    @ResponseBody
    public LocationAddress getAddress(@PathVariable String latitude, @PathVariable String longitude, Model model) {
        return null;
    }

    public GoogleLocationManager getManager() {
        return manager;
    }

    public void setManager(GoogleLocationManager manager) {
        this.manager = manager;
    }

}
