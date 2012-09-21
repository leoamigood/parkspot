package com.amigood.park.yahoo;

import com.amigood.domain.Coordinates;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * User: leo@amigood.com | Leo Amigood
 * Date: 8/23/12
 * Time: 4:11 PM
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class AddressComponent {

    @XmlElement
    private String latitude;

    @XmlElement
    private String longitude;

    public Coordinates getCoordinates() {
        return new Coordinates(latitude, longitude);
    }

    @XmlElement(name = "street")
    private String street;

    @XmlElement(name = "xstreet")
    private String cross;

    @XmlElement
    private Integer quality;

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCross() {
        return cross;
    }

    public void setCross(String cross) {
        this.cross = cross;
    }

    public Integer getQuality() {
        return quality;
    }

    public void setQuality(Integer quality) {
        this.quality = quality;
    }

}
