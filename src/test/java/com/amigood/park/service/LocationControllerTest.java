package com.amigood.park.service;

import com.amigood.park.LocationController;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;

import java.util.List;
import java.util.Map;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;
import static org.mockito.Mockito.mock;

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

    @Autowired
    AnnotationMethodHandlerAdapter handlerAdapter;

    RestTemplate templateMock;

    @Autowired
    LocationController controller;

    @Before
    public void setUp() {
        requestMock = new MockHttpServletRequest();
        responseMock = new MockHttpServletResponse();

        templateMock = mock(RestTemplate.class);
    }

    @Test
    public void testGetLocations() throws Exception {
        requestMock.setMethod("GET");
        requestMock.setRequestURI("/location/40.60281,-73.996821");

        ModelAndView model = handlerAdapter.handle(requestMock, responseMock, controller);
        assertNull(model);

        List<Map> locations = new ObjectMapper().readValue(responseMock.getContentAsString(), List.class);
        assertEquals("P-227058", locations.get(0).get("sign"));
        assertEquals("P-227060", locations.get(1).get("sign"));
    }

}
