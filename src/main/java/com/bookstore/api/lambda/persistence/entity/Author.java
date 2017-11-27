package com.bookstore.api.lambda.persistence.entity;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Indexed;
import org.mongodb.morphia.annotations.Transient;

import java.util.List;

/**
 * Created by Gregorio on 09/11/17.
 */
@Entity("author") public class Author {

    @Id private ObjectId id;
    @Indexed(unique = true)
    private String name;
    private Rating rating;

    public Author() {
    }

    public Author(ObjectId id, String name, Rating rating) {
        this.id = id;
        this.name = name;
        this.rating = rating;
    }

    public Author(String name, Rating rating) {
        this.name = name;
        this.rating = rating;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }
}

