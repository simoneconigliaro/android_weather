package com.project.simoneconigliaro.weatherapp.util;

import android.content.Context;
import android.text.format.Time;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DateUtils {

    public static String getDayOfTheWeek(long unixTime) {

        Long currentTime = System.currentTimeMillis();

        Time time = new Time();
        time.setToNow();

        int julianDay = time.getJulianDay(unixTime * 1000, time.gmtoff);
        int currentJulianDay = time.getJulianDay(currentTime, time.gmtoff);
        int difference = julianDay - currentJulianDay;

        Context context;

        if (difference == 0) {
            return "Today";
        } else if (difference == 1) {
            return "Tomorrow";
        } else {
            Date date = new Date(unixTime * 1000);
            SimpleDateFormat formatter = new SimpleDateFormat("E, d MMM");
            return formatter.format(date);
        }
    }
}
