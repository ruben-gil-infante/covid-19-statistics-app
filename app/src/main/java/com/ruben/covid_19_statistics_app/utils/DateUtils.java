package com.ruben.covid_19_statistics_app.utils;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    public static String getParsedTodayDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-dd-MM");
        Calendar c = Calendar.getInstance();
        return simpleDateFormat.format(c.getTime());
    }

    public static final String parseDate(int year, int month, int day) {
        return formatNumber(year) + "-" + formatNumber(month + 1) + "-" + formatNumber(day);
    }

    private static final String formatNumber(int number) {
        if(number < 10) {
            return String.format("0%d", number);
        } else {
            return String.valueOf(number);
        }
    }

}
