package com.bookstore.api.lambda.graphql.resolver;

import com.bookstore.api.lambda.persistence.dao.AuthorDAO;
import com.bookstore.api.lambda.persistence.dao.BookDAO;
import com.bookstore.api.lambda.persistence.entity.Author;
import com.bookstore.api.lambda.persistence.entity.Book;
import com.coxautodev.graphql.tools.GraphQLResolver;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gregorio on 10/11/17.
 */
public class AuthorResolver implements GraphQLResolver<Author>{

    private final BookDAO bookDAO;
    private final AuthorDAO authorDAO;

    public AuthorResolver(BookDAO bookDAO, AuthorDAO authorDAO) {
        this.bookDAO = bookDAO;
        this.authorDAO = authorDAO;
    }


    public List<Book> books(Author author){
        return bookDAO.findBookByAuthor(author.getId().toString());
    }

}
