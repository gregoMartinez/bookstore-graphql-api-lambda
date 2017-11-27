package com.bookstore.api.graphql;

import com.bookstore.api.lambda.graphql.context.ExecuteQueryHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import graphql.ExecutionResult;
import graphql.GraphQLError;
import org.json.JSONObject;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created by Gregorio on 15/11/17.
 */
public class TestMutation

    extends BaseTest{

    @Test
    public void TestBookRate() throws IOException {
        String query = ""
            + "mutation rateBook(\\$rateInput: RateInput!){"
            + "  rateBook(rateInput: \\$rateInput) { "
            + "    ok"
            + "  }"
            + " }";
        String variables = "{ \"rateInput\": { \"id\": \"5a0a3257dbec66fa15e61525\", \"rate\": 5} }";
        HashMap<String,Object> map = new ObjectMapper().readValue(variables, HashMap.class);
        ExecutionResult result = new ExecuteQueryHandler(graphQLSchema).execute(query,map);
        System.out.println(new JSONObject(result).toString());
    }
}
