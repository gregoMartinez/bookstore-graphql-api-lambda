package com.bookstore.api.lambda.persistence.dao;

import com.bookstore.api.lambda.persistence.entity.Author;
import org.bson.types.ObjectId;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.dao.DAO;

/**
 * Created by Gregorio on 09/11/17.
 */
public interface AuthorDAO extends DAO<Author, ObjectId> {

    Author findByName(String name);
}
