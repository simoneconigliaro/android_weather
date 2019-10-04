package com.project.simoneconigliaro.weatherapp.ui.forecastlist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.DialogTitle;
import androidx.recyclerview.widget.RecyclerView;

import com.project.simoneconigliaro.weatherapp.R;
import com.project.simoneconigliaro.weatherapp.models.Day;
import com.project.simoneconigliaro.weatherapp.util.Constants;
import com.project.simoneconigliaro.weatherapp.util.DateUtils;
import com.project.simoneconigliaro.weatherapp.util.WeatherIcons;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ForecastViewHolder> {

    private List<Day> forecast = new ArrayList<>();
    OnClickHandler onClickHandler;
    private static final String TAG = "ForecastAdapter";

    @Inject
    public ForecastAdapter(OnClickHandler onClickHandler) {
        this.onClickHandler = onClickHandler;
    }

    @NonNull
    @Override
    public ForecastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.weather_list_item, parent, false);
        return new ForecastViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ForecastViewHolder holder, int position) {

        Long day = forecast.get(position).getDate();
        String description = forecast.get(position).getWeathers().get(0).getDescription();
        int temperature = (int) forecast.get(position).getTemperatures().getTempDay();
        int tempMin = (int) forecast.get(position).getTemperatures().getTempMin();
        int tempMax = (int) forecast.get(position).getTemperatures().getTempMax();
        String icon = forecast.get(position).getWeathers().get(0).getIcon();

        String dayString = DateUtils.getDayOfTheWeek(day, true);
        String tempString = temperature + "°C";
        String tempMinMaxString = tempMin + Constants.CELSIUS_SYMBOL + " - " + tempMax + Constants.CELSIUS_SYMBOL;

        holder.dayTextView.setText(dayString);
        holder.descriptionTextView.setText(description);
        holder.temperatureTextView.setText(tempString);
        holder.tempMinMaxTextView.setText(tempMinMaxString);
        holder.weatherIconImageView.setImageResource(WeatherIcons.getIcon(icon));
    }

    @Override
    public int getItemCount() {
        return forecast.size();
    }

    public class ForecastViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView weatherIconImageView;
        private TextView dayTextView, descriptionTextView, temperatureTextView, tempMinMaxTextView;

        public ForecastViewHolder(@NonNull final View itemView) {
            super(itemView);
            weatherIconImageView = itemView.findViewById(R.id.iv_weather_icon);
            dayTextView = itemView.findViewById(R.id.tv_day);
            descriptionTextView = itemView.findViewById(R.id.tv_description);
            temperatureTextView = itemView.findViewById(R.id.tv_temperature);
            tempMinMaxTextView = itemView.findViewById(R.id.tv_temp_min_max);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onClickHandler.onItemClick(getAdapterPosition());
        }
    }

    public void setForecast(List<Day> forecast) {
        this.forecast = forecast;
        notifyDataSetChanged();
    }

    public interface OnClickHandler {
        void onItemClick(int position);
    }


}
