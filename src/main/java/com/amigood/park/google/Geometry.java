package com.amigood.park.google;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

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
        private String lat;

        @XmlElement
        @JsonProperty
        private String lng;
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
