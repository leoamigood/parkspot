package com.amigood.park.google;

import org.codehaus.jackson.annotate.JsonProperty;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * User: leo@amigood.com | Leo Amigood
 * Date: 8/23/12
 * Time: 3:18 PM
 */
@XmlRootElement(name = "GeocodeResponse")
@XmlAccessorType(XmlAccessType.FIELD)
public class GoogleGeoResponse {

    @JsonProperty
    @XmlElement
    private GoogleResponseStatus status;

    @JsonProperty("results")
    @XmlElement(name = "result")
    private List<AddressComponent> components;

    public GoogleResponseStatus getStatus() {
        return status;
    }

    public void setStatus(GoogleResponseStatus status) {
        this.status = status;
    }

    public List<AddressComponent> getComponents() {
        return components;
    }

    public void setComponents(List<AddressComponent> components) {
        this.components = components;
    }

}
