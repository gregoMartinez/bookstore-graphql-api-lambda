package com.bookstore.api.graphql;

import com.bookstore.api.lambda.graphql.context.ExecuteQueryHandler;
import com.bookstore.api.lambda.util.GraphQLResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import graphql.ExecutionResult;
import graphql.GraphQLError;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created by Gregorio on 13/11/17.
 */
public class TestQuery extends BaseTest{

    @Test
    public void TestAllBooks() throws IOException, JSONException {

        String query = ""
            + "query testSchema {"
            + "  __schema {"
            + "    types {"
            + "      name"
            + "      fields {"
            + "        name"
            + "        type {"
            + "          name"
            + "        }"
            + "      }"
            + "    }"
            + "  }"
            + "}";
        String query2 = ""
            + "query allBooks(\\$first: Int, \\$skip: Int){"
            + "  allBooks(first: \\$first, skip: \\$skip) { "
            + "title,"
            + "rating{ averageRating },"
            + "imageUrl{ normalSize },"
            + "authors{ name },"
            + "genre{ name },"
            + "imageUrl{ normalSize },"
            + "authorList{"
            + " id "
            + " name "
            + "}"
            + "      }"
            + "      }";
        String query3 = ""
            + "query allBooks(\\$first: Int, \\$skip: Int){"
            + "  allBooks(first: \\$first, skip: \\$skip) { "
            + "     title"
            + "   }"
            + " }";
        String variables = "{ \"first\": 10, \"skip\": 10 }";
        HashMap<String,Object> map = new ObjectMapper().readValue(variables, HashMap.class);
        ExecutionResult result = new ExecuteQueryHandler(graphQLSchema).execute(query2,map);
        JSONObject jsonObject = new JSONObject(new GraphQLResponse(result.getData(), result.getErrors()));

        ObjectMapper mapper = new ObjectMapper();

        System.out.println(new JSONObject(result).toString());
        System.out.println(result.getData().toString());
        System.out.println(jsonObject.toString());
        System.out.println(mapper.writeValueAsString(result));

    }

}
