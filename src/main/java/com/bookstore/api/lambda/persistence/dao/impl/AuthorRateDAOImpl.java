package com.bookstore.api.lambda.persistence.dao.impl;

import com.bookstore.api.lambda.persistence.dao.AuthorRateDAO;
import com.bookstore.api.lambda.persistence.entity.AuthorRate;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;

/**
 * Created by Gregorio on 10/11/17.
 */
public class AuthorRateDAOImpl extends BasicDAO<AuthorRate, ObjectId> implements AuthorRateDAO{

    public AuthorRateDAOImpl(Class<AuthorRate> entityClass, Datastore ds) {
        super(entityClass, ds);
    }


}
