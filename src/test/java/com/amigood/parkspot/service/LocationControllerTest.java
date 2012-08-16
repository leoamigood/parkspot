package com.amigood.parkspot.service;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;

/**
 * User: leo@amigood.com | Leo Amigood
 * Date: 8/15/12
 * Time: 4:59 PM
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/conf/spring-test.xml")
public class LocationControllerTest {

    MockHttpServletRequest requestMock;
    MockHttpServletResponse responseMock;
    AnnotationMethodHandlerAdapter handlerAdapter;

    @Autowired
    LocationController controller;

    @Before
    public void setUp() {
        requestMock = new MockHttpServletRequest();
        responseMock = new MockHttpServletResponse();
        handlerAdapter = new AnnotationMethodHandlerAdapter();
    }

    @Test
    public void testGetAddress() throws Exception {
        requestMock.setMethod("GET");
        requestMock.setRequestURI("/location/40.697488,-73.979681");

        ModelAndView model = handlerAdapter.handle(requestMock, responseMock, controller);

        Assert.assertEquals("location", model.getViewName());
    }

}
