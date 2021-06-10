package com.goit.api;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.common.collect.Lists;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.Properties;

/**
 * Configuration class for Google spreadsheet API. Gets <a href = https://developers.google.com/workspace/guides/create-credentials>
 * credentials<a/> for API from /credentials.json file. Spreadsheet ID stored in a /application.properties file. Builds an instance
 * of Sheets class.
 * @see Sheets
 */
public class GoogleApiController {

    //main fields
    private static final String CREDENTIALS_FILEPATH = "/credentials.json"; //credentials from Google Cloud Console
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance(); //JsonFactory for Sheets.Builder
    private static final String APP_NAME = "Student Telegram Bot"; //custom application name
    private static GoogleApiController instance;

    private GoogleApiController(){}

    /**
     * Singletone init of class. Lazy init with Double check blocking, to avoid overhead problem.
     * @return GoogleApiConfig instance
     */
    public static GoogleApiController getInstance(){
        if (instance == null){
            synchronized (GoogleApiController.class){
                if (instance == null){
                    instance = new GoogleApiController();
                }
            }
        }
        return instance;
    }


    /**
     * Authorization class that returns authorized Credential object.
     * @return HttpRequestInitializer with credentials
     * @throws IOException if there is no credentials.json file
     */
    private static HttpRequestInitializer authorize() throws IOException {

        InputStream in = GoogleApiController.class.getResourceAsStream(CREDENTIALS_FILEPATH);
        if (in == null){
            throw new FileNotFoundException("No such resource: " + CREDENTIALS_FILEPATH);
        }

        GoogleCredentials credentials = GoogleCredentials.fromStream(in)
                .createScoped(Lists.newArrayList(Collections.singletonList(SheetsScopes.SPREADSHEETS_READONLY)));

        return new HttpCredentialsAdapter(credentials);
    }

    /**
     * Properties class that returns properties from /application.properties. User can access property by
     * getProperties().getProperty() method.
     * @return Properties
     * @throws IOException if there is no application.property file
     * @see Properties
     */

    /**
     * Main service method. Returns an instance of Sheets class to access all of Google Spreadsheet API methods.
     * @return Sheets
     * @throws IOException from incorrect {@link #authorize()} execution.
     * @throws GeneralSecurityException if {@link #authorize()} provides wrong credentials
     */
    public static Sheets service() throws IOException, GeneralSecurityException {

        return new Sheets.Builder(GoogleNetHttpTransport.newTrustedTransport(), JSON_FACTORY, authorize())
                .setApplicationName(APP_NAME)
                .build();
    }

}
