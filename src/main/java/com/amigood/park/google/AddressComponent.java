package com.amigood.park.google;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

/**
 * User: leo@amigood.com | Leo Amigood
 * Date: 8/23/12
 * Time: 4:11 PM
 */
public class AddressComponent {

    public class Entry {

        @JsonCreator
        public Entry() {}
    }

    @JsonProperty
    private List<String> types;

    @JsonProperty("short_name")
    private String shortName;

    @JsonProperty("long_name")
    private String longName;

    @JsonProperty("address_components")
    private List<AddressComponent.Entry> entries;

    @JsonProperty("formatted_address")
    private String address;

    @JsonProperty("geometry")
    private Geometry geometry;

}
