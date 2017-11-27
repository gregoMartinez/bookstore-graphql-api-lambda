package com.bookstore.api.lambda.persistence.dao.impl;

import com.bookstore.api.lambda.persistence.dao.BookDAO;
import com.bookstore.api.lambda.persistence.entity.Book;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gregorio on 10/11/17.
 */
public class BookDAOImpl extends BasicDAO<Book, ObjectId> implements BookDAO{


    public BookDAOImpl(Class<Book> entityClass, Datastore datastore) {
        super(entityClass, datastore);
    }

    public List<Book> findBookByAuthor(String autorId){

        Query<Book> query = createQuery().
            field("authors.id").equal(autorId);

        return query.asList();
    }

    public List<Book> findBookByGenre(String genreId){

        Query<Book> query = createQuery().
            field("genre.id").equal(genreId);

        return query.asList();
    }

    public List<Book> findAll(Number skip, Number first){

        Query<Book> query = createQuery();

        if(first != null && skip != null){
            query.limit(first.intValue()).offset(skip.intValue());
        }
        return query.asList();
    }

}
