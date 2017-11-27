package com.bookstore.api.lambda.function;

import com.amazonaws.services.lambda.runtime.Context;
import com.bookstore.api.lambda.graphql.context.ExecuteQueryHandler;
import com.bookstore.api.lambda.graphql.context.SchemaBuilder;
import com.bookstore.api.lambda.graphql.resolver.*;
import com.bookstore.api.lambda.graphql.type.input.RateInput;
import com.bookstore.api.lambda.graphql.type.response.MutationResponse;
import com.bookstore.api.lambda.persistence.MongoDBConnectionService;
import com.bookstore.api.lambda.persistence.dao.*;
import com.bookstore.api.lambda.persistence.dao.impl.*;
import com.bookstore.api.lambda.persistence.entity.*;
import com.bookstore.api.lambda.util.APIResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import graphql.ExecutionResult;
import graphql.schema.GraphQLSchema;
import org.json.JSONObject;
import org.mongodb.morphia.Datastore;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Gregorio on 13/11/17.
 */
public class BookstoreAPIFunctionHandler {

    private static GraphQLSchema graphQLSchema;

    public Object handleRequest(Map<String,Object> input, Context context) throws Exception {

        HashMap<String,Object> variables = null;
        HashMap<String,Object> headers = new HashMap<String ,Object>();
        context.getLogger().log("Input: ${input}");
        JSONObject requestBody = new JSONObject((String)input.get("body"));

        if(requestBody.has("variables")){
            variables = new ObjectMapper().readValue(requestBody.get("variables").toString(), HashMap.class);
        }else{
            variables = new HashMap<>();
        }

        initAppContext();

        ExecutionResult result = new ExecuteQueryHandler(graphQLSchema).execute(
            requestBody.get("query").toString(), variables);

        ObjectMapper mapper = new ObjectMapper();

        headers.put("Access-Control-Allow-Origin","*");
        headers.put("Access-Control-Allow-Credentials", false);
        headers.put("Content-Type","application/json");

        return new APIResponse(200, headers, mapper.writeValueAsString(result));
    }

    private static void initAppContext() throws Exception{

        Datastore ds = new MongoDBConnectionService().getDatastore();
        BookDAO bookDAO = new BookDAOImpl(Book.class, ds);
        AuthorDAO authorDAO = new AuthorDAOImpl(Author.class, ds);
        GenreDAO genreDAO = new GenreDAOImpl(Genre.class, ds);
        BookRateDAO bookRateDAO = new BookRateDAOImpl(BookRate.class, ds);
        AuthorRateDAO authorRateDAO = new AuthorRateDAOImpl(AuthorRate.class, ds);
        SchemaBuilder schemaBuilder = new SchemaBuilder("schema.graphqls");
        schemaBuilder.setResolvers(new Query(bookDAO), new Mutation(bookDAO, authorDAO,
                bookRateDAO,authorRateDAO),
            new BookResolver(bookDAO, authorDAO),
            new AuthorResolver(bookDAO, authorDAO), new GenreResolver(genreDAO, bookDAO));
        schemaBuilder.setDictionaries(Rating.class, ImageUrl.class, Catalog.class,
            Book.BookAuthor.class, Book.BookGenre.class,
            RateInput.class, MutationResponse.class);
        schemaBuilder.buildSchema();
        graphQLSchema = schemaBuilder.getGraphQLSchema();

    }
}
