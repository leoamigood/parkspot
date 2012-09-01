package com.amigood.parkspot.service;

import com.amigood.domain.LocationAddress;
import com.amigood.domain.Protocol;
import com.amigood.park.LocationController;
import com.amigood.park.google.GeoResponse;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
        controller.getManager().setTemplate(templateMock);
    }

    @Test
    public void testGetAddress() throws Exception {
        requestMock.setMethod("GET");
        requestMock.setRequestURI("/location/40.607649,-73.983339");

        controller.setProtocol(Protocol.JSON);

        ResponseEntity entity = mock(ResponseEntity.class);
        when(templateMock.getForEntity("http://maps.googleapis.com/maps/api/geocode/json?latlng=40.607649,-73.983339&sensor=false", GeoResponse.class)).thenReturn(entity);
        GeoResponse response = new ObjectMapper().readValue(new ClassPathResource("location.json").getFile(), GeoResponse.class);
        when(entity.getBody()).thenReturn(response);

        ModelAndView model = handlerAdapter.handle(requestMock, responseMock, controller);
        assertNull(model);

        LocationAddress address = new ObjectMapper().readValue(responseMock.getContentAsString(), LocationAddress.class);
        assertEquals("130", address.getNumber());
        assertEquals("Avenue P", address.getStreet());
        assertEquals("Brooklyn", address.getCity());
        assertEquals("NY", address.getState());
        assertEquals("11223", address.getZip());
        assertEquals("US", address.getCountry());
    }

}
