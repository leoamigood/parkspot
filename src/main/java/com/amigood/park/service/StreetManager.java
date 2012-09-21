package com.amigood.park.service;

import com.amigood.domain.Coordinates;
import com.amigood.dot.domain.Location;

import java.util.List;

/**
 * @author leo@amigood.com | Leo Amigood, Chain Tale LLC
 *         Date: 9/21/12
 *         Time: 12:03 PM
 */
public interface StreetManager {

    public List<Location> getLocations(Coordinates coordinates);

}
