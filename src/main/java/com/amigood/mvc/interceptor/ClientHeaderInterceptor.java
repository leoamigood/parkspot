package com.amigood.mvc.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author leo@amigood.com | Leo Amigood, Chain Tale LLC
 *         Date: 10/21/12
 *         Time: 4:12 PM
 */
@Component
public class ClientHeaderInterceptor implements ClientHttpRequestInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(ClientHeaderInterceptor.class);

    @Override
    public ClientHttpResponse intercept(HttpRequest httpRequest, byte[] bytes, ClientHttpRequestExecution client) throws IOException {
        ClientHttpResponse response = client.execute(httpRequest, bytes);
        logger.debug("RESPONSE HEADERS: {}", response.getHeaders());

        return response;
    }
}
