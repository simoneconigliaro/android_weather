package com.project.simoneconigliaro.weatherapp.util;

import android.text.format.Time;

import java.text.SimpleDateFormat;
import java.util.Date;


public class DateUtils {

    public static String getDayOfTheWeek(long unixTime, boolean isForecastList) {

        Long currentTime = System.currentTimeMillis();

        Time time = new Time();
        time.setToNow();

        int julianDay = time.getJulianDay(unixTime * 1000, time.gmtoff);
        int currentJulianDay = time.getJulianDay(currentTime, time.gmtoff);
        int difference = julianDay - currentJulianDay;

        // we want to show today and tomorrow only in the forecast list
        if (isForecastList) {
            if (difference == 0) {
                return "Today";
            } else if (difference == 1) {
                return "Tomorrow";
            }
        }
        Date date = new Date(unixTime * 1000);
        SimpleDateFormat formatter = new SimpleDateFormat("E, d MMM");
        return formatter.format(date);
    }

    public static String getHour(long unixTime) {
        Date date = new Date(unixTime * 1000);
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        return formatter.format(date);
    }
}
