package com.amigood.mvc.interceptor;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author leo@amigood.com | Leo Amigood, Chain Tale LLC
 *         Date: 10/7/12
 *         Time: 8:32 PM
 */
public class HeadersInterceptor extends HandlerInterceptorAdapter {

    private Map<String, String> headers;

    @Override
    //has to be implemented in preHandle and called BEFORE response.setContentLength()
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        for (Map.Entry<String, String> entry: headers.entrySet()) {
            response.setHeader(entry.getKey(), entry.getValue());
        }

        return super.preHandle(request, response, handler);
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    @Required
    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }
}
