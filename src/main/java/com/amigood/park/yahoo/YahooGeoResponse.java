package com.amigood.park.yahoo;

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
@XmlRootElement(name = "ResultSet")
@XmlAccessorType(XmlAccessType.FIELD)
public class YahooGeoResponse {

    @XmlElement(name = "Error", required = true)
    private YahooResponseStatus status;

    @XmlElement(name = "ErrorMessage", required = true)
    private String errorMessage;

    @XmlElement(name = "Found")
    private Integer found;

    @XmlElement(name = "Result")
    private List<AddressComponent> components;

    public List<AddressComponent> getComponents() {
        return components;
    }

    public void setComponents(List<AddressComponent> components) {
        this.components = components;
    }

    public YahooResponseStatus getStatus() {
        return status;
    }

    public void setStatus(YahooResponseStatus status) {
        this.status = status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Integer getFound() {
        return found;
    }

    public void setFound(Integer found) {
        this.found = found;
    }

}
