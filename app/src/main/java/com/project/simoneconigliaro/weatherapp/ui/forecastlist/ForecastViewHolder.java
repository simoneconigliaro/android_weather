package com.project.simoneconigliaro.weatherapp.ui.forecastlist;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.simoneconigliaro.weatherapp.R;

public class ForecastViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public ImageView weatherIconImageView;
    public TextView dayTextView, temperatureTextView;
    ForecastAdapter.OnClickHandler onClickHandler;

    public ForecastViewHolder(@NonNull final View itemView, ForecastAdapter.OnClickHandler onClickHandler) {
        super(itemView);
        this.onClickHandler = onClickHandler;
        weatherIconImageView = itemView.findViewById(R.id.iv_weather_icon);
        dayTextView = itemView.findViewById(R.id.tv_day);
        temperatureTextView = itemView.findViewById(R.id.tv_temperature);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        onClickHandler.onItemClick(getAdapterPosition());
    }
}
