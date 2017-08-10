package com.me.sfweather.presenters;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;

import com.me.sfweather.models.CurrentForecast;
import com.me.sfweather.models.ExtendedForecast;
import com.me.sfweather.models.HourlyForecast;
import com.me.sfweather.presenters.repository.NetworkManager;
import com.me.sfweather.utilities.JSONUtils;
import com.me.sfweather.utilities.WeatherUtils;
import com.me.sfweather.views.activities.WeatherActivity;
import com.me.sfweather.views.interfaces.ViewInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.me.sfweather.utilities.WeatherUtils.TIMES;

/**
 * Created by erin.kelley on 8/10/17.
 */

public class WeatherPresenter {
    private Context mContext;
    private ViewInterface mView;
    // 7 day forecast
    private static final int numDays = 7;

    public WeatherPresenter(Context context, ViewInterface view) {
        mContext = context;
        mView = view;
    }

    public void getHourlyForecastData() {
        WeatherActivity.setProgressBarVisibility(View.VISIBLE);
        NetworkManager.getInstance(mContext).getHourlyForecastData("");
    }

    public void getCurrentForecastData() {
        WeatherActivity.setProgressBarVisibility(View.VISIBLE);
        NetworkManager.getInstance(mContext).getCurrentForecastData("");
    }

    public void getExtendedForecastData() {
        WeatherActivity.setProgressBarVisibility(View.VISIBLE);
        NetworkManager.getInstance(mContext).getExtendedForecastData("");
    }

    public void handleData(Intent intent) {
        String action = intent.getAction();
        WeatherActivity.setProgressBarVisibility(View.GONE);

        Intent updateViewData;
        if (action.equals(WeatherUtils.CURRENT_DATA_RECEIVED_FILTER)) {
            String jsonString = intent.getExtras().getString(WeatherUtils.CURRENT_JSON_DATA);

            // Parse json String for the current data
            HashMap currentData = JSONUtils.parseCurrentJSONData(jsonString);

            // Create current forecast model and store the data in it
            CurrentForecast mCurrentForecast = createCurrentForecast(currentData);

            updateViewData = new Intent(WeatherUtils.ACTION_UPDATE_CURRENT_VIEWS);
            updateViewData.putExtra(WeatherUtils.CURRENT_DATA, mCurrentForecast);
            LocalBroadcastManager.getInstance(mContext).sendBroadcast(updateViewData);

            // Populate views with model data
            mView.onNewData();
        } else if (action.equals(WeatherUtils.EXTENDED_DATA_RECEIVED_FILTER)) {
            String jsonString = intent.getExtras().getString(WeatherUtils.EXTENDED_JSON_DATA);

            HashMap extendedData = JSONUtils.parseExtendedJSONData(jsonString);

            ArrayList<ExtendedForecast> mExtendedForecastList = createExtendedForecast(extendedData);

            updateViewData = new Intent(WeatherUtils.ACTION_UPDATE_EXTENDED_VIEWS);
            updateViewData.putExtra(WeatherUtils.EXTENDED_DATA, mExtendedForecastList);
            LocalBroadcastManager.getInstance(mContext).sendBroadcast(updateViewData);

            mView.onNewData();

        } else if (action.equals(WeatherUtils.HOURLY_DATA_RECEIVED_FILTER)) {
            String jsonString = intent.getExtras().getString(WeatherUtils.HOURLY_JSON_DATA);

            HashMap hourlyData = JSONUtils.parseHourlyJSONData(jsonString);

            ArrayList<HourlyForecast> mHourlyForecastList = createHourlyForecast(hourlyData);

            updateViewData = new Intent(WeatherUtils.ACTION_UPDATE_HOURLY_VIEWS);
            updateViewData.putExtra(WeatherUtils.HOURLY_DATA, mHourlyForecastList);
            LocalBroadcastManager.getInstance(mContext).sendBroadcast(updateViewData);

            mView.onNewData();
        }
    }

    public ArrayList<HourlyForecast> createHourlyForecast(HashMap<String, List<String>> hourlyData) {
        ArrayList<HourlyForecast> hourlyForecast = new ArrayList<>();
        int numHours = hourlyData.get(TIMES).size();

        for (int i = 0; i < numHours; i++) {
            HourlyForecast hourlyForecastItem = new HourlyForecast();

            hourlyForecastItem.setId(i);
            hourlyForecastItem.setTime(hourlyData.get(TIMES).get(i));
            hourlyForecastItem.setTemp(hourlyData.get(WeatherUtils.TEMPS).get(i));
            hourlyForecastItem.setPrecip(hourlyData.get(WeatherUtils.PRECIPS).get(i));
            hourlyForecastItem.setCondition(hourlyData.get(WeatherUtils.CONDITIONS).get(i));
            hourlyForecastItem.setWind(
                    hourlyData.get(WeatherUtils.WIND_DIRS).get(i)
                            + " "
                            + hourlyData.get(WeatherUtils.WIND_SPEEDS).get(i));

            hourlyForecast.add(hourlyForecastItem);
        }

        return hourlyForecast;
    }

    public CurrentForecast createCurrentForecast(HashMap<String, String> currentData) {
        CurrentForecast currentForecastItem = new CurrentForecast();

        currentForecastItem.setId("0");
        currentForecastItem.setFeelsLike(currentData.get(WeatherUtils.FEELS_LIKE));
        currentForecastItem.setCondition(currentData.get(WeatherUtils.CONDITION));
        currentForecastItem.setConditionDesc(currentData.get(WeatherUtils.CONDITION_DESC));
        currentForecastItem.setWind(currentData.get(WeatherUtils.WIND));
        currentForecastItem.setHumidity(currentData.get(WeatherUtils.HUMIDITY));
        currentForecastItem.setDewPoint(currentData.get(WeatherUtils.DEW_POINT));
        currentForecastItem.setPressure(currentData.get(WeatherUtils.PRESSURE));
        currentForecastItem.setUvIndex(currentData.get(WeatherUtils.UV_INDEX));

        return currentForecastItem;
    }

    public ArrayList<ExtendedForecast> createExtendedForecast(HashMap<String, List<String>> extendedData) {
        ArrayList<ExtendedForecast> extendedForecast = new ArrayList<>();
        int dayIndex;
        int nightIndex;

        for (int i = 0; i < numDays; i++) {
            ExtendedForecast extendedForecastItem = new ExtendedForecast();

            extendedForecastItem.setId(i);
            extendedForecastItem.setDate(extendedData.get(WeatherUtils.DATES).get(i));
            extendedForecastItem.setHighTemp(extendedData.get(WeatherUtils.HIGH_TEMPS).get(i));
            extendedForecastItem.setLowTemp(extendedData.get(WeatherUtils.LOW_TEMPS).get(i));
            extendedForecastItem.setWindMPHDay(String.valueOf(extendedData.get(WeatherUtils.DAY_WIND_MPHS).get(i)));
            extendedForecastItem.setWindMPHNight(String.valueOf(extendedData.get(WeatherUtils.NIGHT_WIND_MPHS).get(i)));
            extendedForecastItem.setWindDirDay(extendedData.get(WeatherUtils.DAY_WIND_DIRS).get(i));
            extendedForecastItem.setWindDirNight(extendedData.get(WeatherUtils.NIGHT_WIND_DIRS).get(i));
            extendedForecastItem.setCondDesc(extendedData.get(WeatherUtils.COND_DESCS).get(i));

            // Get every other item data since it is sent back from the JSON this way
            dayIndex = i * 2;
            nightIndex = ((i + 1) * 2) - 1;

            extendedForecastItem.setDayOfWeek(extendedData.get(WeatherUtils.DAYS_OF_WEEK).get(dayIndex));
            extendedForecastItem.setCondDay(extendedData.get(WeatherUtils.CONDITIONS).get(dayIndex));
            extendedForecastItem.setCondNight(extendedData.get(WeatherUtils.CONDITIONS).get(nightIndex));
            extendedForecastItem.setPrecipDay(extendedData.get(WeatherUtils.PRECIPS).get(dayIndex));
            extendedForecastItem.setPrecipNight(extendedData.get(WeatherUtils.PRECIPS).get(nightIndex));
            extendedForecastItem.setDescDay(extendedData.get(WeatherUtils.DESCS).get(dayIndex));
            extendedForecastItem.setDescNight(extendedData.get(WeatherUtils.DESCS).get(nightIndex));

            extendedForecast.add(extendedForecastItem);
        }

        return extendedForecast;
    }
}
