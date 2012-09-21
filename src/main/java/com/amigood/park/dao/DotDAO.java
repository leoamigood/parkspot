package com.amigood.park.dao;

import com.amigood.domain.Coordinates;

import java.util.List;

/**
 * @author leo@amigood.com | Leo Amigood, Chain Tale LLC
 *         Date: 9/7/12
 *         Time: 10:51 AM
 */
public interface DotDAO {

    public List getLocations(Coordinates coordinates, Integer limit);

}
