package com.amigood.domain;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author leo@amigood.com | Leo Amigud, Chain Tale LLC
 *         Date: 8/19/12
 *         Time: 4:40 PM
 */
@XmlRootElement
public class PostalAddress {

    private String apt;
    private String street;
    private String city;
    private String state;
    private String zip;
    private String country;

    public String getApt() {
        return apt;
    }

    public void setApt(String apt) {
        this.apt = apt;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
