package com.project.simoneconigliaro.weatherapp.ui.forecastlist;

import android.content.Context;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.project.simoneconigliaro.weatherapp.R;
import com.project.simoneconigliaro.weatherapp.models.Day;
import com.project.simoneconigliaro.weatherapp.util.DateUtils;
import com.project.simoneconigliaro.weatherapp.util.WeatherIcons;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ForecastViewHolder> {

    private List<Day> forecast = new ArrayList<>();

    @NonNull
    @Override
    public ForecastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.weather_list_item, parent, false);
        return new ForecastViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ForecastViewHolder holder, int position) {

        String icon = forecast.get(position).getWeathers().get(0).getIcon();
        Long day = forecast.get(position).getDate();
        String description = forecast.get(position).getWeathers().get(0).getDescription();
        Double temperature = forecast.get(position).getTemperatures().getTempDay();
        Double tempMin = forecast.get(position).getTemperatures().getTempMin();
        Double tempMax = forecast.get(position).getTemperatures().getTempMax();

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.ic_unknown_weather);
        Glide.with(holder.weatherImageView.getContext()).load(WeatherIcons.getIcon(icon)).into(holder.weatherImageView);

        holder.descriptionTextView.setText(description);
        holder.dayTextView.setText(String.valueOf(day));
        holder.temperatureTextView.setText(String.valueOf(temperature));
        holder.tempMinMaxTextView.setText(String.valueOf(tempMin) + " - " + String.valueOf(tempMax));
    }

    @Override
    public int getItemCount() {
        return forecast.size();
    }

    public class ForecastViewHolder extends RecyclerView.ViewHolder {

        private ImageView weatherImageView;
        private TextView dayTextView;
        private TextView descriptionTextView;
        private TextView temperatureTextView;
        private TextView tempMinMaxTextView;

        public ForecastViewHolder(@NonNull View itemView) {
            super(itemView);
            weatherImageView = itemView.findViewById(R.id.iv_weather);
            dayTextView = itemView.findViewById(R.id.tv_day);
            descriptionTextView = itemView.findViewById(R.id.tv_description);
            temperatureTextView = itemView.findViewById(R.id.tv_temperature);
            tempMinMaxTextView = itemView.findViewById(R.id.tv_temp_min_max);
        }
    }

    public void setForecast(List<Day> forecast) {
        this.forecast = forecast;
        notifyDataSetChanged();
    }


}
