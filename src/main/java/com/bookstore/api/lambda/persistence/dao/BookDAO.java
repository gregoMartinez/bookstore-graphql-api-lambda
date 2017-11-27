package com.bookstore.api.lambda.persistence.dao;

import com.bookstore.api.lambda.persistence.entity.Book;
import org.bson.types.ObjectId;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.dao.DAO;

import java.util.List;

/**
 * Created by Gregorio on 09/11/17.
 */
public interface BookDAO extends DAO<Book, ObjectId> {

    List<Book> findBookByAuthor(String authorId);

    public List<Book> findBookByGenre(String genreId);

    public List<Book> findAll(Number skip, Number first);
}
