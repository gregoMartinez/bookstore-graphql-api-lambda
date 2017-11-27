package com.bookstore.api.lambda.persistence.entity;

import com.coxautodev.graphql.tools.GraphQLResolver;
import org.mongodb.morphia.annotations.Embedded;

/**
 * Created by Gregorio on 10/11/17.
 */
@Embedded
public class Rating {

    private Double averageRating;
    private Long rateCount;

    public Rating() {
    }

    public Rating(Double averageRating, Long rateCount) {
        this.averageRating = averageRating;
        this.rateCount = rateCount;
    }

    public Double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }

    public Long getRateCount() {
        return rateCount;
    }

    public void setRateCount(Long rateCount) {
        this.rateCount = rateCount;
    }
}
