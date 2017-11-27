package com.bookstore.api.lambda.persistence.entity;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.EntityListeners;
import org.mongodb.morphia.annotations.Indexed;

/**
 * Created by Gregorio on 09/11/17.
 */
@Entity("book_rate")
public class BookRate extends Rate{

    @Indexed
    private String bookId;

    public BookRate(Integer rate, String bookId) {
        super(rate);
        this.bookId = bookId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }
}
