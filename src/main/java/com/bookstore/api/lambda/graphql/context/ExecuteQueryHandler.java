package com.bookstore.api.lambda.graphql.context;

import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;

import java.util.Map;

/**
 * Created by Gregorio on 13/11/17.
 */
public class ExecuteQueryHandler {

    private GraphQLSchema graphQLSchema;

    public ExecuteQueryHandler(GraphQLSchema graphQLSchema) {
        this.graphQLSchema = graphQLSchema;
    }

    public ExecutionResult execute(String query, Map<String, Object> arguments){

        GraphQL graphQL = GraphQL.newGraphQL(this.graphQLSchema)
            .build();

        return graphQL.execute(query, new Object(), arguments);
    }
}
