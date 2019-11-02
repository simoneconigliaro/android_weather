package com.project.simoneconigliaro.weatherapp.ui.settings;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.project.simoneconigliaro.weatherapp.R;

import dagger.android.support.DaggerAppCompatActivity;

public class SettingsActivity extends DaggerAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }
}
