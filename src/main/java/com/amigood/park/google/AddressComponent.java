package com.amigood.park.google;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

import javax.xml.bind.annotation.XmlElement;
import java.util.List;

/**
 * User: leo@amigood.com | Leo Amigood
 * Date: 8/23/12
 * Time: 4:11 PM
 */
public class AddressComponent {

    public static class Entry {

        @JsonCreator
        public Entry() {}
    }

    @XmlElement(name = "type")
    @JsonProperty
    private List<String> types;

    @XmlElement(name = "short_name")
    @JsonProperty("short_name")
    private String shortName;

    @XmlElement(name = "long_name")
    @JsonProperty("long_name")
    private String longName;

    @XmlElement(name = "address_component")
    @JsonProperty("address_components")
    private List<AddressComponent.Entry> entries;

    @XmlElement(name = "formatted_address")
    @JsonProperty("formatted_address")
    private String address;

    @XmlElement
    @JsonProperty("geometry")
    private Geometry geometry;

}
