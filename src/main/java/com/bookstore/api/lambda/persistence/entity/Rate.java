package com.bookstore.api.lambda.persistence.entity;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Index;
import org.mongodb.morphia.annotations.Indexed;

/**
 * Created by Gregorio on 09/11/17.
 */
abstract class Rate {

    @Id
    private ObjectId id;
    private Integer rate;

    public Rate() {
    }

    public Rate(Integer rate) {
        this.rate = rate;
    }

    public Rate(ObjectId id, Integer rate) {
        this.id = id;
        this.rate = rate;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }
}
