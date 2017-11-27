package com.bookstore.api.lambda.persistence.entity;

import org.mongodb.morphia.annotations.Embedded;

/**
 * Created by Gregorio on 13/11/17.
 */
@Embedded
public class Catalog {

    String id;
    String name;

    public Catalog() {
    }

    public Catalog(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
