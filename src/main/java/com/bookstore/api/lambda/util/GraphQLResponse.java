package com.bookstore.api.lambda.util;

import graphql.GraphQLError;
import jdk.internal.dynalink.linker.LinkerServices;

import java.util.List;

/**
 * Created by Gregorio on 17/11/17.
 */
public class GraphQLResponse {

    private Object data;
    private List<GraphQLError> errors;

    public GraphQLResponse() {
    }

    public GraphQLResponse(Object data, List<GraphQLError> errors) {
        this.data = data;
        this.errors = errors;
    }

    public Object getData() {
        return data;
    }

    public List<GraphQLError> getErrors() {
        return errors;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void setErrors(List<GraphQLError> errors) {
        this.errors = errors;
    }
}
