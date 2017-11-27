package com.bookstore.api.lambda.persistence.entity;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

/**
 * Created by Gregorio on 10/11/17.
 */
@Entity("genre")
public class Genre {

   @Id
   private ObjectId id;
   private String name;

    public Genre() {
    }

    public Genre(ObjectId id, String name) {
        this.id = id;
        this.name = name;
    }

    public Genre(String name) {
        this.name = name;
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
}
