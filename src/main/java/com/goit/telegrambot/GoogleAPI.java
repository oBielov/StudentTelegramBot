package com.goit.telegrambot;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.Sheet;
import com.google.api.services.sheets.v4.model.SheetProperties;
import com.google.api.services.sheets.v4.model.Spreadsheet;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.common.collect.Lists;
import lombok.SneakyThrows;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

public class GoogleAPI {

    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final String APP_NAME = "Google Sheets API";
    private static final String CREDENTIALS_FILEPATH = "/credentials.json";
    private static final String PROPERTIES_FILEPATH = "/application.properties";
    private static final List<String> SCOPES = Collections.singletonList(SheetsScopes.SPREADSHEETS_READONLY);

    private static Properties appProperties;

    @SneakyThrows
    private static InputStream getStream(String resource){
        try (InputStream in = GoogleAPI.class.getResourceAsStream(resource)) {
            return in;
        }
    }

    @SneakyThrows
    private static HttpRequestInitializer getCredentials(){
        GoogleCredential credentials = GoogleCredential.fromStream(getStream(CREDENTIALS_FILEPATH))
                .createScoped(Lists.newArrayList(SCOPES));
        return new HttpCredentialsAdapter(credentials);

    }

}
