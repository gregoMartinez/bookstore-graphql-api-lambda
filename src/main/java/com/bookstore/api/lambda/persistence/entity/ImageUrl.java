package com.bookstore.api.lambda.persistence.entity;

import org.mongodb.morphia.annotations.Embedded;

/**
 * Created by Gregorio on 13/11/17.
 */
@Embedded
public class ImageUrl {

    private String normalSize;
    private String smallSize;

    public ImageUrl() {
    }

    public ImageUrl(String normaSize, String smallSize) {
        this.normalSize = normaSize;
        this.smallSize = smallSize;
    }

    public String getNormalSize() {
        return normalSize;
    }

    public void setNormalSize(String normaSize) {
        this.normalSize = normaSize;
    }

    public String getSmallSize() {
        return smallSize;
    }

    public void setSmallSize(String smallSize) {
        this.smallSize = smallSize;
    }
}
