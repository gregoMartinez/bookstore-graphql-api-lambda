package com.bookstore.api.lambda.persistence.dao;

import com.bookstore.api.lambda.persistence.entity.BookRate;
import com.bookstore.api.lambda.persistence.entity.aggregation.AverageRateResult;
import org.bson.types.ObjectId;
import org.mongodb.morphia.dao.DAO;

/**
 * Created by Gregorio on 09/11/17.
 */
public interface BookRateDAO extends DAO<BookRate, ObjectId> {

    public AverageRateResult getAverageRating(String bookId);
}
