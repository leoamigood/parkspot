package com.amigood.domain;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author leo@amigood.com | Leo Amigud
 *         Date: 8/19/12
 *         Time: 1:08 PM
 */
@XmlRootElement
public class Coordinates {

    @Override
    public String toString() {
        return String.format("%s,%s", longitude, latitude);
    }

    private String longitude;
    private String latitude;

    public Coordinates() {
    }

    public Coordinates(String longitude, String latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
}
