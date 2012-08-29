package com.amigood.park.service;

import com.amigood.domain.Coordinates;
import com.amigood.domain.LocationAddress;
import com.amigood.domain.Protocol;

/**
 * @author leo@amigood.com | Leo Amigood, Chain Tale LLC
 *         Date: 8/19/12
 *         Time: 4:46 PM
 */
public interface LocationManager {

    public LocationAddress findLocation(Coordinates coordinates, Protocol protocol);

}
