package com.amigood.domain;

import org.apache.commons.lang.StringUtils;

/**
 * @author leo@amigood.com | Leo Amigood, Chain Tale LLC
 *         Date: 8/28/12
 *         Time: 4:00 PM
 */
public class LocationAddress {

    private String number;
    private String street;
    private String city;
    private String state;
    private String zip;
    private String country;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        if (StringUtils.isNotBlank(number)) {
            sb.append(number);
        }
        if (StringUtils.isNotBlank(street)) {
            sb.append(" ").append(street);
        }
        if (StringUtils.isNotBlank(city)) {
            sb.append(",").append(city);
        }
        if (StringUtils.isNotBlank(state)) {
            sb.append(",").append(state);
        }
        if (StringUtils.isNotBlank(zip)) {
            sb.append(zip);
        }
        if (StringUtils.isNotBlank(country)) {
            sb.append(",").append(country);
        }

        return sb.toString();
    }
}
