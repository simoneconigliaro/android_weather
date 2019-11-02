package com.project.simoneconigliaro.weatherapp.ui.forecastlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.simoneconigliaro.weatherapp.R;
import com.project.simoneconigliaro.weatherapp.models.WeatherResponse;
import com.project.simoneconigliaro.weatherapp.util.Constants;
import com.project.simoneconigliaro.weatherapp.util.DateUtils;
import com.project.simoneconigliaro.weatherapp.util.TemperatureUtils;
import com.project.simoneconigliaro.weatherapp.util.WeatherIcons;

import javax.inject.Inject;


public class ForecastAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private WeatherResponse weatherResponse;
    OnClickHandler onClickHandler;
    Context context;
    private static final String TAG = "ForecastAdapter";

    private static final int TODAY_VIEW_TYPE = 0;
    private static final int FUTURE_DAY_VIEW_TYPE = 1;

    @Inject
    public ForecastAdapter(Context context, OnClickHandler onClickHandler) {
        this.context = context;
        this.onClickHandler = onClickHandler;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = null;
        switch (viewType) {

            case TODAY_VIEW_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.weather_today_list_item, parent, false);
                return new ForecastTodayViewHolder(view, onClickHandler);

            case FUTURE_DAY_VIEW_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.weather_list_item, parent, false);
                return new ForecastViewHolder(view, onClickHandler);

            default:
                throw new IllegalArgumentException("Invalid view type, value of " + viewType);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        String location = weatherResponse.getCity().getName();
        Long day = weatherResponse.getListDays().get(position).getDate();
        String description = weatherResponse.getListDays().get(position).getWeathers().get(0).getDescription();
        int temperature = (int) weatherResponse.getListDays().get(position).getTemperatures().getTempDay();
        int tempMin = (int) weatherResponse.getListDays().get(position).getTemperatures().getTempMin();
        int tempMax = (int) weatherResponse.getListDays().get(position).getTemperatures().getTempMax();
        String icon = weatherResponse.getListDays().get(position).getWeathers().get(0).getIcon();

        String dayString = DateUtils.getDayOfTheWeek(day, true);

        String temperatureString = TemperatureUtils.formatTemperature(context, temperature);
        String tempMinString = TemperatureUtils.formatTemperature(context, tempMin);
        String tempMaxString = TemperatureUtils.formatTemperature(context, tempMax);

        int viewType = getItemViewType(position);
        switch (viewType) {
            case TODAY_VIEW_TYPE:
                ((ForecastTodayViewHolder) holder).locationTextView.setText(location);
                ((ForecastTodayViewHolder) holder).dayTextView.setText(dayString);
                ((ForecastTodayViewHolder) holder).descriptionTextView.setText(description);
                ((ForecastTodayViewHolder) holder).temperatureTextView.setText(temperatureString);
                ((ForecastTodayViewHolder) holder).tempMinTextView.setText(tempMinString);
                ((ForecastTodayViewHolder) holder).tempMaxTextView.setText(tempMaxString);
                ((ForecastTodayViewHolder) holder).weatherIconImageView.setImageResource(WeatherIcons.getIcon(icon));
                break;
            case FUTURE_DAY_VIEW_TYPE:
                ((ForecastViewHolder) holder).dayTextView.setText(dayString);
                ((ForecastViewHolder) holder).descriptionTextView.setText(description);
                ((ForecastViewHolder) holder).temperatureTextView.setText(temperatureString);
                ((ForecastViewHolder) holder).tempMinTextView.setText(tempMinString);
                ((ForecastViewHolder) holder).tempMaxTextView.setText(tempMaxString);
                ((ForecastViewHolder) holder).weatherIconImageView.setImageResource(WeatherIcons.getIcon(icon));
                break;
            default:
                throw new IllegalArgumentException("Invalid view type, value of " + viewType);
        }


    }

    @Override
    public int getItemCount() {
        if (weatherResponse != null) {
            return weatherResponse.getListDays().size();
        } else return 0;
    }


    public void setForecast(WeatherResponse weatherResponse) {
        this.weatherResponse = weatherResponse;
        notifyDataSetChanged();
    }

    public interface OnClickHandler {
        void onItemClick(int position);
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TODAY_VIEW_TYPE;
        } else {
            return FUTURE_DAY_VIEW_TYPE;
        }
    }
}
