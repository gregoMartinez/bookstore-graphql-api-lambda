package com.bookstore.api.lambda.persistence.dao.impl;

import com.bookstore.api.lambda.persistence.dao.GenreDAO;
import com.bookstore.api.lambda.persistence.entity.Genre;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.aggregation.AggregationPipeline;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;

import java.util.List;

/**
 * Created by Gregorio on 10/11/17.
 */
public class GenreDAOImpl extends BasicDAO<Genre, ObjectId> implements GenreDAO {

    public GenreDAOImpl(Class<Genre> entityClass, Datastore ds) {
        super(entityClass, ds);
    }

    public List<Genre> findAll(Integer skip, Integer first){

        Query<Genre> query = createQuery().order("name");

        if(first != null && skip != null){
            query.limit(first).offset(skip);
        }
        return query.asList();
    }

    public Genre getRandomGenre(Integer randomSkip){
        Query<Genre> query = createQuery();
        query.limit(1).offset(randomSkip);
        return query.get();
    }


}
