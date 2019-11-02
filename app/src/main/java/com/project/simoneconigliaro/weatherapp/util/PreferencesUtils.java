package com.project.simoneconigliaro.weatherapp.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.project.simoneconigliaro.weatherapp.R;

public class PreferencesUtils {

    public static boolean isMetric(Context context) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);

        String prefUnitsKey = context.getString(R.string.pref_temp_units_key);
        String defaultUnits = context.getString(R.string.pref_temp_units_metric_value);
        String preferredUnits = sp.getString(prefUnitsKey, defaultUnits);
        String metric = context.getString(R.string.pref_temp_units_metric_value);

        boolean userPrefersMetric = false;
        if (metric.equals(preferredUnits)) {
            userPrefersMetric = true;
        }
        return userPrefersMetric;
    }
}
