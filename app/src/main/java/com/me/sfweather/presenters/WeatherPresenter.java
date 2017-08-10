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
import com.me.sfweather.utilities.WeatherConst;
import com.me.sfweather.views.activities.WeatherActivity;
import com.me.sfweather.views.interfaces.ViewInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.me.sfweather.utilities.WeatherConst.TIMES;

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
        if (action.equals(WeatherConst.CURRENT_DATA_RECEIVED_FILTER)) {
            String jsonString = intent.getExtras().getString(WeatherConst.CURRENT_JSON_DATA);

            // Parse json String for the current data
            HashMap currentData = JSONUtils.parseCurrentJSONData(jsonString);

            // Create current forecast model and store the data in it
            CurrentForecast mCurrentForecast = createCurrentForecast(currentData);

            updateViewData = new Intent(WeatherConst.ACTION_UPDATE_CURRENT_VIEWS);
            updateViewData.putExtra(WeatherConst.CURRENT_DATA, mCurrentForecast);
            LocalBroadcastManager.getInstance(mContext).sendBroadcast(updateViewData);

            // Populate views with model data
            mView.onNewData();
        } else if (action.equals(WeatherConst.EXTENDED_DATA_RECEIVED_FILTER)) {
            String jsonString = intent.getExtras().getString(WeatherConst.EXTENDED_JSON_DATA);

            HashMap extendedData = JSONUtils.parseExtendedJSONData(jsonString);

            ArrayList<ExtendedForecast> mExtendedForecastList = createExtendedForecast(extendedData);

            updateViewData = new Intent(WeatherConst.ACTION_UPDATE_EXTENDED_VIEWS);
            updateViewData.putExtra(WeatherConst.EXTENDED_DATA, mExtendedForecastList);
            LocalBroadcastManager.getInstance(mContext).sendBroadcast(updateViewData);

            mView.onNewData();

        } else if (action.equals(WeatherConst.HOURLY_DATA_RECEIVED_FILTER)) {
            String jsonString = intent.getExtras().getString(WeatherConst.HOURLY_JSON_DATA);

            HashMap hourlyData = JSONUtils.parseHourlyJSONData(jsonString);

            ArrayList<HourlyForecast> mHourlyForecastList = createHourlyForecast(hourlyData);

            updateViewData = new Intent(WeatherConst.ACTION_UPDATE_HOURLY_VIEWS);
            updateViewData.putExtra(WeatherConst.HOURLY_DATA, mHourlyForecastList);
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
            hourlyForecastItem.setTemp(hourlyData.get(WeatherConst.TEMPS).get(i));
            hourlyForecastItem.setPrecip(hourlyData.get(WeatherConst.PRECIPS).get(i));
            hourlyForecastItem.setCondition(hourlyData.get(WeatherConst.CONDITIONS).get(i));
            hourlyForecastItem.setWind(
                    hourlyData.get(WeatherConst.WIND_DIRS).get(i)
                            + " "
                            + hourlyData.get(WeatherConst.WIND_SPEEDS).get(i));

            hourlyForecast.add(hourlyForecastItem);
        }

        return hourlyForecast;
    }

    public CurrentForecast createCurrentForecast(HashMap<String, String> currentData) {
        CurrentForecast currentForecastItem = new CurrentForecast();

        currentForecastItem.setId("0");
        currentForecastItem.setFeelsLike(currentData.get(WeatherConst.FEELS_LIKE));
        currentForecastItem.setCondition(currentData.get(WeatherConst.CONDITION));
        currentForecastItem.setConditionDesc(currentData.get(WeatherConst.CONDITION_DESC));
        currentForecastItem.setWind(currentData.get(WeatherConst.WIND));
        currentForecastItem.setHumidity(currentData.get(WeatherConst.HUMIDITY));
        currentForecastItem.setDewPoint(currentData.get(WeatherConst.DEW_POINT));
        currentForecastItem.setPressure(currentData.get(WeatherConst.PRESSURE));
        currentForecastItem.setUvIndex(currentData.get(WeatherConst.UV_INDEX));

        return currentForecastItem;
    }

    public ArrayList<ExtendedForecast> createExtendedForecast(HashMap<String, List<String>> extendedData) {
        ArrayList<ExtendedForecast> extendedForecast = new ArrayList<>();
        int dayIndex;
        int nightIndex;

        for (int i = 0; i < numDays; i++) {
            ExtendedForecast extendedForecastItem = new ExtendedForecast();

            extendedForecastItem.setId(i);
            extendedForecastItem.setDate(extendedData.get(WeatherConst.DATES).get(i));
            extendedForecastItem.setHighTemp(extendedData.get(WeatherConst.HIGH_TEMPS).get(i));
            extendedForecastItem.setLowTemp(extendedData.get(WeatherConst.LOW_TEMPS).get(i));
            extendedForecastItem.setWindMPHDay(String.valueOf(extendedData.get(WeatherConst.DAY_WIND_MPHS).get(i)));
            extendedForecastItem.setWindMPHNight(String.valueOf(extendedData.get(WeatherConst.NIGHT_WIND_MPHS).get(i)));
            extendedForecastItem.setWindDirDay(extendedData.get(WeatherConst.DAY_WIND_DIRS).get(i));
            extendedForecastItem.setWindDirNight(extendedData.get(WeatherConst.NIGHT_WIND_DIRS).get(i));
            extendedForecastItem.setCondDesc(extendedData.get(WeatherConst.COND_DESCS).get(i));

            // Get every other item data since it is sent back from the JSON this way
            dayIndex = i * 2;
            nightIndex = ((i + 1) * 2) - 1;

            extendedForecastItem.setDayOfWeek(extendedData.get(WeatherConst.DAYS_OF_WEEK).get(dayIndex));
            extendedForecastItem.setCondDay(extendedData.get(WeatherConst.CONDITIONS).get(dayIndex));
            extendedForecastItem.setCondNight(extendedData.get(WeatherConst.CONDITIONS).get(nightIndex));
            extendedForecastItem.setPrecipDay(extendedData.get(WeatherConst.PRECIPS).get(dayIndex));
            extendedForecastItem.setPrecipNight(extendedData.get(WeatherConst.PRECIPS).get(nightIndex));
            extendedForecastItem.setDescDay(extendedData.get(WeatherConst.DESCS).get(dayIndex));
            extendedForecastItem.setDescNight(extendedData.get(WeatherConst.DESCS).get(nightIndex));

            extendedForecast.add(extendedForecastItem);
        }

        return extendedForecast;
    }
}
