package com.amigood.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * @author leo@amigood.com | Leo Amigood, Chain Tale LLC
 *         Date: 9/7/12
 *         Time: 10:26 AM
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Intersection {

    @XmlElement
    private String street1;
    @XmlElement
    private String street2;
    @XmlElement
    private String highway1;
    @XmlElement
    private String highway2;

    @XmlElement
    private Double lat;
    @XmlElement
    private Double lng;

    @XmlElement
    private Double distance;

    public String getStreet1() {
        return street1;
    }

    public void setStreet1(String street1) {
        this.street1 = street1;
    }

    public String getStreet2() {
        return street2;
    }

    public void setStreet2(String street2) {
        this.street2 = street2;
    }

    public String getHighway1() {
        return highway1;
    }

    public void setHighway1(String highway1) {
        this.highway1 = highway1;
    }

    public String getHighway2() {
        return highway2;
    }

    public void setHighway2(String highway2) {
        this.highway2 = highway2;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

}
