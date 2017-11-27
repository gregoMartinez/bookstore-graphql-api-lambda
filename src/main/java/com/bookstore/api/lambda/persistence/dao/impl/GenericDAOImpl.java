package com.bookstore.api.lambda.persistence.dao.impl;

import com.bookstore.api.lambda.persistence.dao.GenericDAO;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Key;

import java.io.Serializable;

/**
 * Created by Gregorio on 09/11/17.
 */
public class GenericDAOImpl<T,K> implements GenericDAO<T,K>{

    private Datastore ds;
    private Class<T> type;

    public GenericDAOImpl(Class<T> entityClass, Datastore datastore) {
        this.type = entityClass;
        this.ds = datastore;
    }

    public Key<T> save(T entity) {
        return ds.save(entity);
    }

    public T get(Class<T> clazz, K id) {
        return ds.get(clazz, id);
    }

    public void delete(T entity) {
        ds.delete(entity);
    }
}
