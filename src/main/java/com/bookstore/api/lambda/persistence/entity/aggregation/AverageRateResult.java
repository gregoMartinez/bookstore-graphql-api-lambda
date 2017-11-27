package com.bookstore.api.lambda.persistence.entity.aggregation;

/**
 * Created by Gregorio on 15/11/17.
 */
public class AverageRateResult {

    private Double avg;
    private Long count;

    public AverageRateResult() {
    }

    public AverageRateResult(Double avg, Long count) {
        this.avg = avg;
        this.count = count;
    }

    public Double getAvg() {
        return avg;
    }

    public void setAvg(Double avg) {
        this.avg = avg;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
