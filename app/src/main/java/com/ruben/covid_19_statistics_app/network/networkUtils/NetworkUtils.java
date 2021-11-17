package com.ruben.covid_19_statistics_app.network.networkUtils;

import com.ruben.covid_19_statistics_app.BuildConfig;

import java.util.HashMap;
import java.util.Map;

public class NetworkUtils {

    private static Map<String, String> headers;

    private static final String HOST = "x-rapidapi-host";
    private static final String KEY = "x-rapidapi-key";

    public static Map<String, String> getHeaders() {
        if(headers == null) {
            headers = new HashMap<>();
            headers.put(HOST, BuildConfig.API_HOST);
            headers.put(KEY, BuildConfig.API_KEY);
        }
        return headers;
    }

}
