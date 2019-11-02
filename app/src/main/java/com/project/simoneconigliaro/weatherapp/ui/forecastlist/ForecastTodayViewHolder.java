package com.project.simoneconigliaro.weatherapp.ui.forecastlist;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.simoneconigliaro.weatherapp.R;

public class ForecastTodayViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public ImageView weatherIconImageView;
    public TextView locationTextView, dayTextView, descriptionTextView, temperatureTextView, tempMinTextView, tempMaxTextView;
    ForecastAdapter.OnClickHandler onClickHandler;

    public ForecastTodayViewHolder(@NonNull final View itemView, ForecastAdapter.OnClickHandler onClickHandler) {
        super(itemView);
        this.onClickHandler = onClickHandler;
        locationTextView = itemView.findViewById(R.id.tv_today_location);
        weatherIconImageView = itemView.findViewById(R.id.iv_today_weather_icon);
        dayTextView = itemView.findViewById(R.id.tv_today);
        descriptionTextView = itemView.findViewById(R.id.tv_today_description);
        temperatureTextView = itemView.findViewById(R.id.tv_today_temperature);
        tempMinTextView = itemView.findViewById(R.id.tv_today_temp_min);
        tempMaxTextView = itemView.findViewById(R.id.tv_today_temp_max);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        onClickHandler.onItemClick(getAdapterPosition());
    }

}
