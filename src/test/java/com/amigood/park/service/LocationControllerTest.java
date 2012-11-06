package com.amigood.park.service;

import com.amigood.test.SpatialTests;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

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
@Category(SpatialTests.class)
public class LocationControllerTest {

    MockHttpServletRequest requestMock;
    MockHttpServletResponse responseMock;

    @Autowired
    RequestMappingHandlerAdapter handlerAdapter;

    @Autowired
    RequestMappingHandlerMapping handlerMapping;

    RestTemplate templateMock;

    @Before
    public void setUp() {
        requestMock = new MockHttpServletRequest();
        responseMock = new MockHttpServletResponse();

        templateMock = mock(RestTemplate.class);
    }

    @Test
    public void testGetLocations() throws Exception {
        requestMock.setMethod("GET");
        requestMock.setRequestURI("/location/40.60661439639105,-73.98747128362048/");

        Object controller = handlerMapping.getHandler(requestMock).getHandler();
        final HandlerInterceptor[] interceptors = handlerMapping.getHandler(requestMock).getInterceptors();
        for (HandlerInterceptor interceptor : interceptors) {
            final boolean carryOn = interceptor.preHandle(requestMock, responseMock, controller);
            if (!carryOn) {
                return;
            }
        }

        ModelAndView model = handlerAdapter.handle(requestMock, responseMock, controller);

        for (HandlerInterceptor interceptor : interceptors) {
            interceptor.postHandle(requestMock, responseMock, controller, model);
        }

        assertNull(model);

        List<Map> locations = new ObjectMapper().readValue(responseMock.getContentAsString(), List.class);
        assertEquals("S-214406", locations.get(0).get("sign"));
    }

}
