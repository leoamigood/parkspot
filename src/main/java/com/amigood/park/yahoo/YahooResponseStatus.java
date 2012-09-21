package com.amigood.park.yahoo;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;

/**
 * @author leo@amigood.com | Leo Amigood, Chain Tale LLC
 *         Date: 9/8/12
 *         Time: 10:48 AM
 */
@XmlEnum
public enum YahooResponseStatus {
    @XmlEnumValue("0")
    OK;
}
