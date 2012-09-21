package com.amigood.park;

import com.amigood.domain.Coordinates;
import com.amigood.dot.domain.Location;
import com.amigood.park.service.StreetManagerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * User: leo@amigood.com | Leo Amigood
 * Date: 8/15/12
 * Time: 4:48 PM
 */
@Controller
public class LocationController {

    @Autowired
    private StreetManagerImpl manager;

    // Using regexp matching as setting useDefaultSuffixPattern to false
    // in RequestMappingHandlerMapping or DefaultAnnotationHandlerMapping does not seem to help
    @RequestMapping(method= RequestMethod.GET, value={"/location/{latitude},{longitude:.+}", "/location/{latitude},{longitude}/"})
    @ResponseBody
    public List<Location> getLocations(@PathVariable String latitude, @PathVariable String longitude, Model model) {
        return manager.getLocations(new Coordinates(latitude, longitude), 10);
    }

    public StreetManagerImpl getManager() {
        return manager;
    }

    public void setManager(StreetManagerImpl manager) {
        this.manager = manager;
    }

}
