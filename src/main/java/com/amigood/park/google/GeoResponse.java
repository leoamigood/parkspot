package com.amigood.park.google;

import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

/**
 * User: leo@amigood.com | Leo Amigood
 * Date: 8/23/12
 * Time: 3:18 PM
 */
public class GeoResponse {

    @JsonProperty
    private String status;

    @JsonProperty("results")
    private List<AddressComponent> components;
}
