package com.amigood.domain;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author leo@amigood.com | Leo Amigud
 *         Date: 8/19/12
 *         Time: 1:08 PM
 */
@XmlRootElement
public class Coordinates {
    /*
     *  FEET_TO_LENGTH is the length scaling constant in feet
     *  For example, to calculate the length of "AVENUE Y" between "OCEAN AVENUE" and "EAST 19 STREET" in "BROOKLYN, NY"
     *  Use this formula (length * FEET_TO_LENGTH) = 0.0012012523631590181 * 278875.622038 =~ 335 feet
     */
    public static final double FEET_TO_LENGTH = 278875.622038;
    public static final double METER_TO_LENGTH = 111076.675549;

    @Override
    public String toString() {
        return String.format("%s,%s", latitude, longitude);
    }

    private Double latitude;
    private Double longitude;

    public Coordinates() {
    }

    public Coordinates(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Coordinates(String latitude, String longitude) {
        this.latitude = Double.parseDouble(latitude);
        this.longitude = Double.parseDouble(longitude);
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double[] getNearbyArea(double feet) {
        Double distance = feet / FEET_TO_LENGTH;

        return new Double[] {
                latitude - distance, longitude - distance,
                latitude - distance, longitude + distance,
                latitude + distance, longitude + distance,
                latitude + distance, longitude - distance,
                latitude - distance, longitude - distance
        };
    }
}
