package com.amigood.park.google;

import org.codehaus.jackson.annotate.JsonProperty;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * User: leo@amigood.com | Leo Amigood
 * Date: 8/23/12
 * Time: 3:18 PM
 */
@XmlRootElement(name = "GeocodeResponse")
public class GeoResponse {

    @JsonProperty
    @XmlElement
    private String status;

    @JsonProperty("results")
    @XmlElement(name = "result")
    private List<AddressComponent> components;

//    private List<Result> results;
}
