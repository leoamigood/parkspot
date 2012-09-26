package com.amigood.park.dao;

import com.amigood.domain.Coordinates;
import com.amigood.dot.domain.Location;
import org.hibernate.Query;
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

    public List<Location> getLocations(Coordinates coordinates, Integer limit) {
        String[] params = new String[] {"fromLat", "fromLng"};
        setMaxResults(limit);

        String sql = "SELECT * FROM (" +
                        "SELECT *, (a+b+c)/2 as p FROM (" +
                            "SELECT *, length as a, sqrt(pow(abs(:fromLng) - abs(from_lng), 2) + pow(abs(:fromLat) - abs(from_lat), 2)) as b, sqrt(pow(abs(:fromLng) - abs(to_lng), 2) + pow(abs(:fromLat) - abs(to_lat), 2)) as c from location " +
                            "WHERE from_lng is not null AND from_lat is not null AND to_lng is not null AND to_lat is not null AND validated = 1) as triangle " +
                            "ORDER BY abs(abs(:fromLng) - abs(center_lng)) + abs(abs(:fromLat) - abs(center_lat)) limit :limit) as nearby " +
                        "ORDER BY 2/a * sqrt(p*(p-a)*(p-b)*(p-c)) asc";

        Query query = getSession().createSQLQuery(sql).addEntity(Location.class);
        query.setDouble("fromLat", coordinates.getLatitude());
        query.setDouble("fromLng", coordinates.getLongitude());
        query.setInteger("limit", limit);

        return query.list();
        //return findByNamedQueryAndNamedParam("findNearestStreet", params, coordinates.getAsArray());
    }

}
