package com.project.simoneconigliaro.weatherapp.ui.settings;

import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;

import com.project.simoneconigliaro.weatherapp.R;

public class SettingsFragment extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.pref_forecast);

    }
}
