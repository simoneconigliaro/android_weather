package com.project.simoneconigliaro.weatherapp.ui.detail;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.project.simoneconigliaro.weatherapp.R;
import com.project.simoneconigliaro.weatherapp.ui.forecastlist.ForecastListActivity;

import dagger.android.support.DaggerAppCompatActivity;

public class DetailActivity extends DaggerAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        int position = getIntent().getIntExtra(ForecastListActivity.POSITION_KEY, 0);
        Toast.makeText(this, "" + position, Toast.LENGTH_SHORT).show();
    }
}
