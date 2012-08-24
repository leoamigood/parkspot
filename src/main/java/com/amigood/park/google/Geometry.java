package com.amigood.park.google;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * User: leo@amigood.com | Leo Amigood
 * Date: 8/24/12
 * Time: 10:37 AM
 */
public class Geometry {

    public class Location {

        @JsonCreator
        public Location() {}

        @JsonProperty
        private String lat;

        @JsonProperty
        private String lng;
    }

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
