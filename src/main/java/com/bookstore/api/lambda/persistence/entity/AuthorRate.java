package com.bookstore.api.lambda.persistence.entity;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Indexed;

/**
 * Created by Gregorio on 09/11/17.
 */
@Entity("author_rate")
public class AuthorRate extends Rate{

    @Indexed
    private String authorId;

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

}
