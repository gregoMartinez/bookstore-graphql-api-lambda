package com.bookstore.api.lambda.persistence.dao;

import com.bookstore.api.lambda.persistence.entity.Genre;
import org.bson.types.ObjectId;
import org.mongodb.morphia.dao.DAO;

import java.util.List;

/**
 * Created by Gregorio on 10/11/17.
 */
public interface GenreDAO extends DAO<Genre, ObjectId> {

    public List<Genre> findAll(Integer limit, Integer skip);

    public Genre getRandomGenre(Integer randomSkip);
}
