package com.bookstore.api.lambda.graphql.resolver;

import com.bookstore.api.lambda.persistence.dao.BookDAO;
import com.bookstore.api.lambda.persistence.dao.GenreDAO;
import com.bookstore.api.lambda.persistence.entity.Book;
import com.bookstore.api.lambda.persistence.entity.Genre;
import com.coxautodev.graphql.tools.GraphQLResolver;

import java.util.List;

/**
 * Created by Gregorio on 10/11/17.
 */
public class GenreResolver implements GraphQLResolver<Genre> {

    private final GenreDAO genreDAO;
    private final BookDAO bookDAO;

    public GenreResolver(GenreDAO genreDAO, BookDAO bookDAO) {
        this.genreDAO = genreDAO;
        this.bookDAO = bookDAO;
    }

    List<Book> books(Genre genre){
        return bookDAO.findBookByGenre(genre.getId().toString());
    }
}
