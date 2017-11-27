package com.bookstore.api.graphql;

import com.bookstore.api.lambda.graphql.context.SchemaBuilder;
import com.bookstore.api.lambda.graphql.resolver.*;
import com.bookstore.api.lambda.graphql.type.input.RateInput;
import com.bookstore.api.lambda.graphql.type.response.MutationResponse;
import com.bookstore.api.lambda.persistence.MongoDBConnectionService;
import com.bookstore.api.lambda.persistence.dao.*;
import com.bookstore.api.lambda.persistence.dao.impl.*;
import com.bookstore.api.lambda.persistence.entity.*;
import graphql.schema.GraphQLSchema;
import graphql.schema.GraphQLType;
import org.junit.BeforeClass;
import org.mongodb.morphia.Datastore;

import java.util.HashMap;

/**
 * Created by Gregorio on 13/11/17.
 */
public class BaseTest {

    protected static BookDAO bookDAO;
    protected static BookRateDAO bookRateDAO;
    protected static AuthorDAO authorDAO;
    protected static AuthorRateDAO authorRateDAO;
    protected static GenreDAO genreDAO;
    protected static GraphQLSchema graphQLSchema;


    @BeforeClass
    public static void init() throws Exception{
        Datastore ds = new MongoDBConnectionService().getDatastore();
        bookDAO = new BookDAOImpl(Book.class, ds);
        authorDAO = new AuthorDAOImpl(Author.class, ds);
        genreDAO = new GenreDAOImpl(Genre.class, ds);
        bookRateDAO = new BookRateDAOImpl(BookRate.class, ds);
        authorRateDAO = new AuthorRateDAOImpl(AuthorRate.class, ds);
        SchemaBuilder schemaBuilder = new SchemaBuilder("schema.graphqls");
        schemaBuilder.setResolvers(new Query(bookDAO), new Mutation(bookDAO, authorDAO, bookRateDAO,authorRateDAO),
            new BookResolver(bookDAO, authorDAO),
            new AuthorResolver(bookDAO, authorDAO), new GenreResolver(genreDAO, bookDAO));
        schemaBuilder.setDictionaries(Rating.class, ImageUrl.class, Catalog.class, Book.BookAuthor.class, Book.BookGenre.class,
            RateInput.class, MutationResponse.class);
        schemaBuilder.buildSchema();
        graphQLSchema = schemaBuilder.getGraphQLSchema();
    }
}
