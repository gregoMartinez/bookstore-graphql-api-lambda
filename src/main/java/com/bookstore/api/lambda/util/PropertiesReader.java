package com.bookstore.api.lambda.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

/**
 * Created by Gregorio on 09/11/17.
 */
public class PropertiesReader {

    private Properties prop;

     public Properties getPropertyFile(String path) throws Exception{
        try{
            prop = new Properties();
            prop.load(getClass().getResourceAsStream(path));
            return prop;
        }catch (Exception ex){
            throw new FileNotFoundException(ex.getMessage());
        }
    }
}
