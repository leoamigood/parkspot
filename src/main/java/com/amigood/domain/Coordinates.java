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

    private Double longitude;
    private Double latitude;

    public Coordinates() {
    }

    public Coordinates(String latitude, String longitude) {
        this.latitude = Double.parseDouble(latitude);
        this.longitude = Double.parseDouble(longitude);
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double[] getAsArray() {
        return new Double[]{latitude, longitude};
    }
}
