package com.amigood.domain;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author leo@amigood.com | Leo Amigud
 *         Date: 8/19/12
 *         Time: 1:08 PM
 */
@XmlRootElement
public class Coordinates {

    private Float longitude;
    private Float latitude;

    public Coordinates() {
    }

    public Coordinates(Float longitude, Float latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }
}
