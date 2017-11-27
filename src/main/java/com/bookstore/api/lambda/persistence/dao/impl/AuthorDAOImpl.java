package com.bookstore.api.lambda.persistence.dao.impl;

import com.bookstore.api.lambda.persistence.dao.AuthorDAO;
import com.bookstore.api.lambda.persistence.entity.Author;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;

/**
 * Created by Gregorio on 13/11/17.
 */
public class AuthorDAOImpl extends BasicDAO<Author, ObjectId> implements AuthorDAO {

    public AuthorDAOImpl(Class<Author> entityClass, Datastore ds) {
        super(entityClass, ds);
    }

    public Author findByName(String name){
        Query<Author> query = createQuery().
            field("name").equal(name);
        return query.get();
    }
}
