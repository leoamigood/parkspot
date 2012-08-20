package com.amigood.park.service;

import com.amigood.domain.Coordinates;
import com.amigood.domain.PostalAddress;

/**
 * @author leo@amigood.com | Leo Amigood, Chain Tale LLC
 *         Date: 8/19/12
 *         Time: 4:46 PM
 */
public interface LocationManager {

    public PostalAddress geoCode(Coordinates coordinates);
}
