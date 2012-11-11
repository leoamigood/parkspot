package com.amigood.park.google;

import com.amigood.domain.Coordinates;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlElement;

/**
 * User: leo@amigood.com | Leo Amigood
 * Date: 8/24/12
 * Time: 10:37 AM
 */
public class Geometry {

    public static class Location {

        @JsonCreator
        public Location() {}

        @XmlElement
        @JsonProperty
        public String lat;

        @XmlElement
        @JsonProperty
        public  String lng;
    }

    public Coordinates getCoordinates() {
        return new Coordinates(location.lat, location.lng);
    }

    @XmlElement
    @JsonProperty
    private Location location;

    @JsonIgnore
    @JsonProperty
    private String location_type;

    @JsonIgnore
    @JsonProperty
    private Object bounds;

    @JsonIgnore
    @JsonProperty
    private Object viewport;

}
