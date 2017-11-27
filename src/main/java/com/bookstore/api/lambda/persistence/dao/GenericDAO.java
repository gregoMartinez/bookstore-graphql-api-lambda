package com.bookstore.api.lambda.persistence.dao;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Key;

/**
 * Created by Gregorio on 09/11/17.
 */
public interface GenericDAO<T,K> {

    public Key<T> save( T entity);

    public T get(Class<T> clazz, K id);

    public void delete( T entity);

}
