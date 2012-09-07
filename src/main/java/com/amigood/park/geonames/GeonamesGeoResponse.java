package com.amigood.park.geonames;

import com.amigood.domain.Intersection;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @author leo@amigood.com | Leo Amigood, Chain Tale LLC
 *         Date: 9/7/12
 *         Time: 10:30 AM
 */
@XmlRootElement(name = "geonames")
@XmlAccessorType(XmlAccessType.FIELD)
public class GeonamesGeoResponse {

    @XmlElement(name = "intersection")
    private List<Intersection> intersections;

    public List<Intersection> getIntersections() {
        return intersections;
    }

    public void setIntersections(List<Intersection> intersections) {
        this.intersections = intersections;
    }

}
