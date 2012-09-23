package com.amigood.park.exception;

/**
 * @author leo@amigood.com | Leo Amigood, Chain Tale LLC
 *         Date: 9/18/12
 *         Time: 2:46 PM
 */
public class LocationException extends Exception {

    public LocationException(String status) {
        super(status);
    }

    public LocationException(String status, Throwable throwable) {
        super(status, throwable);
    }

}
