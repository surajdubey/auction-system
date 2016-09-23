package com.crossover.auctionsystem.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateTimeUtil {
    private static String TIME_FORMAT = "";
    private static String DISPLAY_TIME_FORMAT = "";

    public String getCurrentDateTime() {
        //get current time
        SimpleDateFormat dateFormat = new SimpleDateFormat(TIME_FORMAT, Locale.getDefault());
        return dateFormat.format(new Date());

    }

    public String getDateTime(Calendar calendar) {
        //get current time
        SimpleDateFormat dateFormat = new SimpleDateFormat(TIME_FORMAT, Locale.getDefault());
        return dateFormat.format(calendar.getTime());

    }

    public Calendar getCalendar(String dateTime) {
        Calendar calendar = Calendar.getInstance();
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(TIME_FORMAT, Locale.getDefault());
            Date date = dateFormat.parse(dateTime);
            calendar.setTime(date);
        } catch (ParseException e) {
//            L.d("Parse Error");
        }
        return calendar;
    }

    public String getDisplayTime(String dateTime) {
        String result = "";
        try {
            SimpleDateFormat inputDateFormat = new SimpleDateFormat(TIME_FORMAT, Locale.getDefault());
            SimpleDateFormat outputDateFormat = new SimpleDateFormat(DISPLAY_TIME_FORMAT, Locale.getDefault());
            Date date = inputDateFormat.parse(dateTime);
            result = outputDateFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return result;
    }

    public String getDisplayTime(Calendar calendar) {
        //get current time
        SimpleDateFormat dateFormat = new SimpleDateFormat(DISPLAY_TIME_FORMAT, Locale.getDefault());
        return dateFormat.format(calendar.getTime());

    }
}
