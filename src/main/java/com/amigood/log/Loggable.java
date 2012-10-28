package com.amigood.log;


import java.lang.annotation.*;

/**
 * @author leo@amigood.com | Leo Amigood, Chain Tale LLC
 *         Date: 10/26/12
 *         Time: 12:02 PM
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
public @interface Loggable {
    //for slf4j
}