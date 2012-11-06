package com.amigood.park;

import com.amigood.domain.Coordinates;
import com.amigood.dot.domain.Location;
import com.amigood.park.exception.LocationException;
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
    public static final double MAX_DISTANCE = 1500;

    @Autowired
    private StreetManagerImpl manager;

    private final DecimalFormat format = new DecimalFormat("0.######");

    // Using regexp matching as setting useDefaultSuffixPattern to false
    // in RequestMappingHandlerMapping or DefaultAnnotationHandlerMapping does not seem to help
    @RequestMapping(method = RequestMethod.GET, value={"/location/{latitude},{longitude:.+}", "/location/{latitude},{longitude}/"})
    @ResponseBody
    public List<Location> getLocations(@PathVariable Double latitude, @PathVariable Double longitude, Model model) throws LocationException {
        return getLocationsLimited(latitude, longitude, 300.0, model);
    }

    @RequestMapping(method = RequestMethod.GET, value={"/location/{latitude},{longitude}/{distance}"})
    @ResponseBody
    public List<Location> getLocationsLimited(@PathVariable Double latitude, @PathVariable Double longitude, @PathVariable Double distance, Model model) throws LocationException {
        Coordinates coordinates = new Coordinates(format.format(latitude), format.format(longitude));

        return manager.getLocations(coordinates, Math.min(distance, MAX_DISTANCE));
    }

    public StreetManagerImpl getManager() {
        return manager;
    }

    public void setManager(StreetManagerImpl manager) {
        this.manager = manager;
    }

}
