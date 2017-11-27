package com.bookstore.api.lambda.graphql.type.input;

/**
 * Created by Gregorio on 15/11/17.
 */
public class RateInput {

    private String id;
    private Integer rate;

    public RateInput() {
    }

    public RateInput(String id, Integer rate) {
        this.id = id;
        this.rate = rate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }
}
