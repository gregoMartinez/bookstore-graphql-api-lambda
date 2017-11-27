package com.bookstore.api.lambda.graphql.type.response;

import com.bookstore.api.lambda.persistence.entity.Book;

/**
 * Created by Gregorio on 15/11/17.
 */
public class MutationResponse {

    private Boolean ok;

    public MutationResponse() {
    }

    public MutationResponse(Boolean ok) {

        this.ok = ok;
    }

    public Boolean getOk() {
        return ok;
    }

    public void setOk(Boolean ok) {
        this.ok = ok;
    }
}
