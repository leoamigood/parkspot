package com.amigood.park.service;

import com.amigood.domain.Coordinates;
import com.amigood.domain.LocationAddress;
import com.amigood.domain.Protocol;
import com.amigood.park.google.GoogleGeoResponse;
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
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author leo@amigood.com | Leo Amigood, Chain Tale LLC
 *         Date: 9/23/12
 *         Time: 12:29 AM
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/conf/spring-test.xml")
public class GoogleLocationManagerTest {

    MockHttpServletRequest requestMock;
    MockHttpServletResponse responseMock;

    @Autowired
    AnnotationMethodHandlerAdapter handlerAdapter;

    RestTemplate templateMock;

    @Autowired
    GoogleLocationManager manager;

    @Before
    public void setUp() {
        templateMock = mock(RestTemplate.class);
        manager.setTemplate(templateMock);
        manager.setProtocol(Protocol.JSON);
    }

    @Test
    public void testFindIntersection() throws Exception {
        ResponseEntity entity = mock(ResponseEntity.class);
        String url = "http://maps.googleapis.com/maps/api/geocode/json?address=NEW UTRECHT AVENUE,Brooklyn,NY+and+67 STREET,Brooklyn,NY&components=intersection&sensor=false";
        when(templateMock.getForEntity(url, GoogleGeoResponse.class)).thenReturn(entity);
        GoogleGeoResponse response = new ObjectMapper().readValue(new ClassPathResource("google/location.json").getFile(), GoogleGeoResponse.class);
        when(entity.getBody()).thenReturn(response);

        LocationAddress main = new LocationAddress();
        main.setStreet("NEW UTRECHT AVENUE");
        main.setCity("Brooklyn");
        main.setState("NY");
        LocationAddress cross = new LocationAddress();
        cross.setStreet("67 STREET");
        cross.setCity("Brooklyn");
        cross.setState("NY");

        Coordinates coordinates = manager.findIntersection(main, cross);
        assertNotNull(coordinates);
        assertEquals(40.62164890, coordinates.getLatitude());
        assertEquals(-73.99832060, coordinates.getLongitude());
    }

}
