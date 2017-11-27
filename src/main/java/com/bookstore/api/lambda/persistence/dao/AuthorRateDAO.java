package com.bookstore.api.lambda.persistence.dao;

import com.bookstore.api.lambda.persistence.entity.AuthorRate;
import org.bson.types.ObjectId;
import org.mongodb.morphia.dao.DAO;

/**
 * Created by Gregorio on 09/11/17.
 */
public interface AuthorRateDAO extends DAO<AuthorRate, ObjectId> {
}
