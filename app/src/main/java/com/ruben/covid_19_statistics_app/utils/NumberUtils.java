package com.ruben.covid_19_statistics_app.utils;

public class NumberUtils {

    public static String makeThePercentage(String value) {
        try {
            Double parsedValue = Double.parseDouble(value);
            parsedValue = parsedValue * 100;
            return "( " + Math.round(parsedValue) + "% )";
        } catch (Exception e) {
            return "( 0.00% )";
        }
    }
}
