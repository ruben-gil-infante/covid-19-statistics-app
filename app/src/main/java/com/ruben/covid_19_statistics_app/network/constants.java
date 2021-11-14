package com.ruben.covid_19_statistics_app.network;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class constants {

    public static final String BASE_URL = "https://covid-19-statistics.p.rapidapi.com/";

    private static final String API_KEY = "apiKey";
    private static final String API_HOST = "apiHost";

    // Headers
    private static final String API_KEY_HEADER = "x-rapidapi-key: ";
    private static final String API_HOST_HEADER = "x-rapidapi-host: ";

    public static final String generateHeaders() {
        String apiKey = getPropertyValue(API_KEY);
        String apiHost = getPropertyValue(API_HOST);
        String generatedHeader = "{" + API_KEY_HEADER + apiKey + ","
                + API_HOST_HEADER + apiHost + "}";
        return generatedHeader;
    }


    // TODO: Create and move to config properties utils class and chage local.properties file for config.properties
    private static String getPropertyValue(String key){
        Properties properties = new Properties();
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream("local.properties");
            properties.load(inputStream);
            return properties.getProperty(key);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}

