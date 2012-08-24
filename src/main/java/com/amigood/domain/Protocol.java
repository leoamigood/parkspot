package com.amigood.domain;

/**
 * User: leo@amigood.com | Leo Amigood
 * Date: 8/23/12
 * Time: 4:27 PM
 */
public enum Protocol {

    XML, JSON;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
