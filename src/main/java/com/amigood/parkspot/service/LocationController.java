package com.amigood.parkspot.service;

import com.amigood.domain.Location;
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

    @RequestMapping(method=RequestMethod.GET, value="/location/{coordinates}")
    public String getAddress(@PathVariable String coordinates, Model model) {
        String loc[] = coordinates.split(",");
        model.addAttribute("location", new Location(Float.parseFloat(loc[0]), Float.parseFloat(loc[1])));
        return "location";
    }

}
