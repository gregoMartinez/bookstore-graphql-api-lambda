package com.bookstore.api.lambda.util;

import java.util.Map;

/**
 * Created by Gregorio on 26/03/17.
 */
public class APIResponse {

    Integer statusCode;
    Map<String,Object> headers;
    Object body;

    public APIResponse(Integer statusCode, Map<String,Object> headers, Object body){
        this.statusCode = statusCode;
        this.headers = headers;
        this.body = body;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public Map<String,Object> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String,Object> headers) {
        this.headers = headers;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }
}
