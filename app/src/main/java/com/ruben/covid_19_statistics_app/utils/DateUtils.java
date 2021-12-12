package com.ruben.covid_19_statistics_app.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    public static String getParsedTodayDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-dd-MM");
        // return simpleDateFormat.format(new Date());
        return "2021-10-12";
    }

}
