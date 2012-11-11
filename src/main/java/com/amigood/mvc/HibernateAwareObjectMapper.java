package com.amigood.mvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;

/**
 * @author leo@amigood.com | Leo Amigood, Chain Tale LLC
 *         Date: 11/10/12
 *         Time: 12:35 PM
 */
public class HibernateAwareObjectMapper extends ObjectMapper {
    public HibernateAwareObjectMapper() {
        Hibernate4Module hm = new Hibernate4Module();
        registerModule(hm);
        //configure(Feature.FAIL_ON_EMPTY_BEANS, false);
    }
}
