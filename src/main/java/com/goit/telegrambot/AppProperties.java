package com.goit.telegrambot;

import com.goit.api.TelegramApiController;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AppProperties {
    private static java.util.Properties appProperties;
    private static final String PROPERTIES_FILEPATH = "/application.properties"; //properties with sheets ID

    /**
     * Properties class that returns properties from /application.properties. User can access property by
     * getProperties().getProperty() method.
     * @return Properties
     * @throws IOException if there is no application.property file
     * @see java.util.Properties
     */
    public static java.util.Properties getProperties() throws IOException {
        if (appProperties != null){
            return appProperties;
        }
        InputStream in = TelegramApiController.class.getResourceAsStream(PROPERTIES_FILEPATH);
        if (in == null){
            throw new FileNotFoundException("No such resource: " + PROPERTIES_FILEPATH);
        }
        appProperties = new java.util.Properties();
        appProperties.load(in);
        return appProperties;
    }

}
