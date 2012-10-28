package com.amigood.park.service;

import com.amigood.domain.Coordinates;
import com.amigood.domain.LocationAddress;
import com.amigood.park.exception.LocationException;

/**
 * @author leo@amigood.com | Leo Amigood, Chain Tale LLC
 *         Date: 8/19/12
 *         Time: 4:46 PM
 */
public interface LocationManager {

    public Coordinates findIntersection(LocationAddress address1, LocationAddress address2) throws LocationException;

}
