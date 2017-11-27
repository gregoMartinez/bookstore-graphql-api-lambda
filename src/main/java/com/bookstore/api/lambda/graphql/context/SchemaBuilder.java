package com.bookstore.api.lambda.graphql.context;

import com.bookstore.api.lambda.graphql.resolver.*;
import com.bookstore.api.lambda.persistence.dao.AuthorDAO;
import com.bookstore.api.lambda.persistence.dao.BookDAO;
import com.bookstore.api.lambda.persistence.dao.GenreDAO;
import com.coxautodev.graphql.tools.GraphQLResolver;
import com.coxautodev.graphql.tools.SchemaParser;
import com.coxautodev.graphql.tools.SchemaParserBuilder;
import graphql.schema.GraphQLScalarType;
import graphql.schema.GraphQLSchema;

/**
 * Created by Gregorio on 12/11/17.
 */
public class SchemaBuilder {

    private String schemaFilePath;
    private GraphQLScalarType[] scalars;
    private GraphQLResolver[] resolvers;
    private Class[] dictionaries;
    private GraphQLSchema graphQLSchema;

    public SchemaBuilder(String schemaFilePath) {
        this.schemaFilePath = schemaFilePath;
    }

    public void buildSchema() {
        this.graphQLSchema = build(this.schemaFilePath, this.scalars, this.resolvers, this.dictionaries);
    }

    private static GraphQLSchema build( String schemaFilePath, GraphQLScalarType[] scalars, GraphQLResolver[] resolvers, Class[] dictionaries){

        GraphQLSchema schema;

        SchemaParserBuilder builder = SchemaParser.newParser()
            .file(schemaFilePath);

        if(resolvers != null){
            builder.resolvers(resolvers);
        }

        if(scalars != null){
            builder.scalars(scalars);
        }

        if(dictionaries != null) {
            builder.dictionary(dictionaries);
        }

        return builder.build()
            .makeExecutableSchema();
    }

    public void setScalars(GraphQLScalarType  ... scalars) {
        this.scalars = scalars;
    }

    public void setResolvers(GraphQLResolver ... resolvers) {
        this.resolvers = resolvers;
    }

    public void setDictionaries(Class ... dictionaries) {
        this.dictionaries = dictionaries;
    }

    public GraphQLSchema getGraphQLSchema() {
        return graphQLSchema;
    }
}

