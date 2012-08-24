package com.amigood.park.google;

import javax.xml.bind.annotation.XmlElement;
import java.util.List;

/**
 * User: leo@amigood.com | Leo Amigood
 * Date: 8/23/12
 * Time: 3:29 PM
 */
public class Result {

    @XmlElement
    private String type;

    @XmlElement(name = "address_component")
    private List<AddressComponent> addressComponents;

}
