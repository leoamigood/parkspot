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

    public static final String STREET_ADDRESS = "street_address";

    public enum Element {
        STREET_NUMBER("street_number"),
        STREET_NAME("route"),
        CITY("sublocality"),
        STATE("administrative_area_level_1"),
        COUNTRY("country");

        private String value;

        Element(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }
    }

    public static class Entry {
        @JsonCreator
        public Entry() {}

        @XmlElement(name = "type")
        @JsonProperty
        private List<String> types;

        @XmlElement(name = "long_name")
        @JsonProperty("long_name")
        private String longName;

        @XmlElement(name = "short_name")
        @JsonProperty("short_name")
        private String shortName;

        public List<String> getTypes() {
            return types;
        }

        public void setTypes(List<String> types) {
            this.types = types;
        }

        public String getLongName() {
            return longName;
        }

        public void setLongName(String longName) {
            this.longName = longName;
        }

        public String getShortName() {
            return shortName;
        }

        public void setShortName(String shortName) {
            this.shortName = shortName;
        }
    }

    @XmlElement(name = "type")
    @JsonProperty
    private List<String> types;

    @XmlElement(name = "address_component")
    @JsonProperty("address_components")
    private List<AddressComponent.Entry> entries;

    @XmlElement(name = "formatted_address")
    @JsonProperty("formatted_address")
    private String address;

    @XmlElement
    @JsonProperty("geometry")
    private Geometry geometry;

    public List<Entry> getEntries() {
        return entries;
    }

    public void setEntries(List<Entry> entries) {
        this.entries = entries;
    }

    public List<String> getTypes() {
        return types;
    }

}
