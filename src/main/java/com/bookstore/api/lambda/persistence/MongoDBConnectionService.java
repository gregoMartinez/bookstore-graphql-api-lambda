package com.bookstore.api.lambda.persistence;

import com.mongodb.MongoClient;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import com.bookstore.api.lambda.util.PropertiesReader;

import java.util.Properties;

/**
 * Created by Gregorio on 09/11/17.
 */
public class MongoDBConnectionService {

    private Morphia morphia;
    private Datastore datastore;

    public MongoDBConnectionService() throws Exception{

        String filePath = "/mongodb_{env}.properties";

        String environment =  (System.getenv("STAGE") != null) ? System.getenv("STAGE") : "dev";

        PropertiesReader propertiesReader = new PropertiesReader();
        Properties prop = propertiesReader.getPropertyFile(filePath.replace("{env}", environment));

        MongoClient mongoClient = new MongoClient(prop.getProperty("host"));
        this.morphia = new Morphia();
        String databaseName = prop.getProperty("db_name");
        this.datastore = this.morphia.mapPackage("com.bookstore.api.lambda.persistence.entity")
            .createDatastore(mongoClient, databaseName);
        this.datastore.ensureIndexes();
    }


    public Morphia getMorphia() {
        return morphia;
    }

    public void setMorphia(Morphia morphia) {
        this.morphia = morphia;
    }

    public Datastore getDatastore() {
        return datastore;
    }

    public void setDatastore(Datastore datastore) {
        this.datastore = datastore;
    }
}
