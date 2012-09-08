package com.amigood.park.google.exception;

import com.amigood.park.google.GoogleResponseStatus;

/**
 * @author leo@amigood.com | Leo Amigood, Chain Tale LLC
 *         Date: 9/8/12
 *         Time: 10:47 AM
 */
public class GoogleException extends Exception {
    public GoogleException(GoogleResponseStatus status) {
        super(status.toString());
    }
}
