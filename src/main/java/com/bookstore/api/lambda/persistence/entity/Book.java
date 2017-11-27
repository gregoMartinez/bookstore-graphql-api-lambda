package com.bookstore.api.lambda.persistence.entity;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.*;

import java.util.List;

/**
 * Created by Gregorio on 09/11/17.
 */
@Entity("book")
public class Book {

    @Id
    private ObjectId id;
    @Indexed(unique = true)
    private String isbn;
    private String title;
    private String authorsNames;
    private List<BookAuthor> authors;
    private Integer publicationYear;
    private BookGenre genre;
    private Rating rating;
    private Double price;
    private ImageUrl imageUrl;

    public Book() {
    }

    public Book(ObjectId id, String isbn, String title, String authorsNames,
        List<BookAuthor> authors, Integer publicationYear, BookGenre genre, Rating rating,
        Double price, ImageUrl imageUrl) {
        this.id = id;
        this.isbn = isbn;
        this.title = title;
        this.authorsNames = authorsNames;
        this.authors = authors;
        this.publicationYear = publicationYear;
        this.genre = genre;
        this.rating = rating;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public static class BookAuthor extends Catalog{
        public BookAuthor(){
        };
        public BookAuthor(String id, String name) {
            super(id, name);
        }
    }

    public static class BookGenre extends Catalog{
        public BookGenre() {
        };

        public BookGenre(String id, String name) {
            super(id, name);
        }
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(String id) {
        this.id = new ObjectId(id);
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthorsNames() {
        return authorsNames;
    }

    public void setAuthorsNames(String authorsNames) {
        this.authorsNames = authorsNames;
    }

    public List<BookAuthor> getAuthors() {
        return authors;
    }

    public void setAuthors(List<BookAuthor> authors) {
        this.authors = authors;
    }

    public Integer getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(Integer publicationYear) {
        this.publicationYear = publicationYear;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public BookGenre getGenre() {
        return genre;
    }

    public void setGenre(BookGenre genre) {
        this.genre = genre;
    }

    public ImageUrl getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(ImageUrl imageUrl) {
        this.imageUrl = imageUrl;
    }
}
