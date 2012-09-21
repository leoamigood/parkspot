package com.amigood.park.dao;

import com.amigood.domain.Coordinates;
import com.amigood.dot.domain.Location;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author leo@amigood.com | Leo Amigood, Chain Tale LLC
 *         Date: 9/7/12
 *         Time: 10:51 AM
 */
@Repository
public class DotDAOImpl extends HibernateTemplate implements DotDAO {

    @Autowired
    public DotDAOImpl(SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }

    @Override
    public List<Location> getLocations(Coordinates coordinates, Integer limit) {
        String[] params = new String[] {"fromLat", "fromLng"};
        setMaxResults(limit);

        return findByNamedQueryAndNamedParam("findNearestStreet", params, coordinates.getAsArray());
    }

}
