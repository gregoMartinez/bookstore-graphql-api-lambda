package com.bookstore.api.lambda.persistence.dao.impl;

import com.bookstore.api.lambda.persistence.dao.BookRateDAO;
import com.bookstore.api.lambda.persistence.entity.BookRate;
import com.bookstore.api.lambda.persistence.entity.aggregation.AverageRateResult;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.aggregation.AggregationPipeline;
import org.mongodb.morphia.aggregation.AggregationPipelineImpl;
import org.mongodb.morphia.dao.BasicDAO;

import java.util.Iterator;

import static org.mongodb.morphia.aggregation.Accumulator.accumulator;
import static org.mongodb.morphia.aggregation.Group.grouping;

/**
 * Created by Gregorio on 10/11/17.
 */
public class BookRateDAOImpl extends BasicDAO<BookRate, ObjectId> implements BookRateDAO{

    public BookRateDAOImpl(Class<BookRate> entityClass, Datastore ds) {
        super(entityClass, ds);
    }

    public AverageRateResult getAverageRating(String bookId){

        AggregationPipeline pipeline = ds.createAggregation(BookRate.class)
            .match(ds.getQueryFactory().createQuery(ds)
                .field("bookId").equal(bookId))
            .group(grouping("avg", accumulator("$avg", "rate")),
                grouping("count", accumulator("$sum", 1)));
        Iterator<AverageRateResult> it = pipeline.aggregate(AverageRateResult.class);

        return it.next();
    }
}
