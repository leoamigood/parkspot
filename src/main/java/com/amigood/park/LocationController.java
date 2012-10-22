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

import java.text.DecimalFormat;
import java.util.List;

/**
 * User: leo@amigood.com | Leo Amigood
 * Date: 8/15/12
 * Time: 4:48 PM
 */
@Controller
public class LocationController {
    public static final int MAX_LOCATIONS = 25;

    @Autowired
    private StreetManagerImpl manager;

    private final DecimalFormat format = new DecimalFormat("0.######");

    // Using regexp matching as setting useDefaultSuffixPattern to false
    // in RequestMappingHandlerMapping or DefaultAnnotationHandlerMapping does not seem to help
    @RequestMapping(method = RequestMethod.GET, value={"/location/{latitude},{longitude:.+}", "/location/{latitude},{longitude}/"})
    @ResponseBody
    public List<Location> getLocations(@PathVariable Double latitude, @PathVariable Double longitude, Model model) {
        return getLocationsLimited(latitude, longitude, 10, model);
    }

    @RequestMapping(method = RequestMethod.GET, value={"/location/{latitude},{longitude}/{limit}"})
    @ResponseBody
    public List<Location> getLocationsLimited(@PathVariable Double latitude, @PathVariable Double longitude, @PathVariable Integer limit, Model model) {
        return manager.getLocations(new Coordinates(format.format(latitude), format.format(longitude)), Math.min(limit, MAX_LOCATIONS));
    }

    public StreetManagerImpl getManager() {
        return manager;
    }

    public void setManager(StreetManagerImpl manager) {
        this.manager = manager;
    }

}
