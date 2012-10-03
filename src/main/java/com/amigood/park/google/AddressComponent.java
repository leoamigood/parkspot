package com.amigood.park.google;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.annotate.JsonDeserialize;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlEnum;
import java.io.IOException;
import java.util.List;

/**
 * User: leo@amigood.com | Leo Amigood
 * Date: 8/23/12
 * Time: 4:11 PM
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class AddressComponent {

    public static final String STREET_ADDRESS = "street_address";

    public enum Element {
        STREET_NUMBER("street_number"),
        STREET_NAME("route"),
        CITY("sublocality"),
        STATE("administrative_area_level_1"),
        COUNTRY("country"),
        ZIP("postal_code");

        private String value;

        Element(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }
    }

    @XmlEnum
    @JsonDeserialize(using = AddressComponent.Type.Deserializer.class)
    public static enum Type {
        //TODO: implement AddressComponent.isIntersection()
        INTERSECTION,
        ROUTE,
        BUS_STATION,
        SUBWAY_STATION,
        TRAIN_STATION,
        TRANSIT_STATION,
        ESTABLISHMENT,
        POINT_OF_INTEREST,
        STORE,
        PHARMACY,
        DOCTOR,
        DENTIST,
        HEALTH,
        HOSPITAL,
        VETERINARY_CARE,
        SUBLOCALITY,
        POLITICAL,
        ADMINISTRATIVE_AREA_LEVEL_2,
        STREET_ADDRESS,
        PARK,
        PARKING,
        CHURCH,
        SYNAGOGUE,
        PLACE_OF_WORSHIP,
        MUSEUM,
        UNIVERSITY,
        SCHOOL,
        LIBRARY,
        POST_OFFICE,
        FINANCE;

        public static class Deserializer extends JsonDeserializer<Type> {
            @Override
            public Type deserialize(JsonParser jsonParser, DeserializationContext context) throws IOException, JsonProcessingException {
                return Type.valueOf(jsonParser.getText().toUpperCase());
            }
        }

    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Entry {
        @JsonCreator
        public Entry() {}

        @JsonProperty
        @XmlElement(name = "type")
        private List<String> types;

        @JsonProperty("long_name")
        @XmlElement(name = "long_name")
        private String longName;

        @JsonProperty("short_name")
        @XmlElement(name = "short_name")
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

    @JsonProperty
    @XmlElement(name = "type")
    private List<Type> types;

    @JsonProperty("address_components")
    @XmlElement(name = "address_component")
    private List<AddressComponent.Entry> entries;

    @JsonProperty("formatted_address")
    @XmlElement(name = "formatted_address")
    private String address;

    @JsonProperty
    @XmlElement
    private Geometry geometry;

    @JsonProperty("partial_match")
    @XmlElement(name = "partial_match")
    private Boolean partialMatch;

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    public List<Entry> getEntries() {
        return entries;
    }

    public void setEntries(List<Entry> entries) {
        this.entries = entries;
    }

    public List<Type> getTypes() {
        return types;
    }

    @Override
    public String toString() {
        return "AddressComponent{" +
                ", entries=" + entries +
                ", address='" + address + '\'' +
                ", geometry=" + geometry +
                ", partialMatch=" + partialMatch +
                '}';
    }
}
