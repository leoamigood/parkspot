package com.amigood.park.service;

import com.amigood.domain.Coordinates;
import com.amigood.dot.domain.Location;
import com.amigood.park.dao.DotDAO;
import com.amigood.park.exception.LocationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author leo@amigood.com | Leo Amigood, Chain Tale LLC
 *         Date: 9/21/12
 *         Time: 12:02 PM
 */
@Service
@Transactional(readOnly = true)
public class StreetManagerImpl implements StreetManager {

    @Autowired
    private DotDAO dao;

    @Override
    public List<Location> getLocations(Coordinates coordinates, Double distance) throws LocationException {
        return dao.getLocations(coordinates, distance);
    }
}
