package com.bookstore.api.lambda.graphql.resolver;

import com.bookstore.api.lambda.persistence.dao.AuthorDAO;
import com.bookstore.api.lambda.persistence.dao.BookDAO;
import com.bookstore.api.lambda.persistence.dao.impl.BookDAOImpl;
import com.bookstore.api.lambda.persistence.entity.Author;
import com.bookstore.api.lambda.persistence.entity.Book;
import com.coxautodev.graphql.tools.GraphQLResolver;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gregorio on 10/11/17.
 */
public class BookResolver implements GraphQLResolver<Book> {

    private final BookDAO bookDAO;
    private final AuthorDAO authorDAO;

    public BookResolver(BookDAO bookDAO, AuthorDAO authorDAO) {
        this.bookDAO = bookDAO;
        this.authorDAO = authorDAO;
    }

    public List<Author> authorList(Book book){

        List<Author> authorList = new ArrayList<Author>();

        for (Book.BookAuthor bookAuthor:
            book.getAuthors()) {
            Author author = authorDAO.get(new ObjectId(bookAuthor.getId()));
            if(author != null){
                authorList.add(author);
            }
        }
        return authorList;
    }

}
