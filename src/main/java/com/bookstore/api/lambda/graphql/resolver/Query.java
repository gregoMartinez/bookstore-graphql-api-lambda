package com.bookstore.api.lambda.graphql.resolver;

import com.bookstore.api.lambda.persistence.dao.BookDAO;
import com.bookstore.api.lambda.persistence.entity.Book;
import com.coxautodev.graphql.tools.GraphQLRootResolver;

import java.util.List;

/**
 * Created by Gregorio on 12/11/17.
 */
public class Query implements GraphQLRootResolver{

    private final BookDAO bookDAO;

    public Query(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    public List<Book> allBooks( Number skip, Number first ){
        return bookDAO.findAll(skip, first);
    }
}
