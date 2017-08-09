package com.me.sfweather.utilities;

import com.me.sfweather.models.HourlyForecast;

import java.util.HashMap;
import java.util.List;

/**
 * Created by erin.kelley on 8/8/17.
 */

public class WeatherUtils {
    // Intent Strings
    public static String HOURLY_DATA_RECEIVED_FILTER = "Hourly Data Received";
    public static String HOURLY_JSON_DATA = "Hourly JSON Data";

    // Hourly Strings
    public static String TIMES = "times";
    public static String TEMPS = "temps";
    public static String PRECIPS = "precips";
    public static String WIND_SPEEDS = "wind speeds";
    public static String WIND_DIRS = "wind directions";
    public static String CONDITIONS = "conditions";

    public static void createHourlyForecast(List<HourlyForecast> hourlyForecast,
                                            HashMap<String, List<String>> hourlyData) {
        for (int i = 0; i < hourlyData.size(); i++) {
            HourlyForecast hourlyForecastItem = new HourlyForecast();

            hourlyForecastItem.setId(i);
            hourlyForecastItem.setTime(hourlyData.get(WeatherUtils.TIMES).get(i));
            hourlyForecastItem.setTemp(hourlyData.get(WeatherUtils.TEMPS).get(i));
            hourlyForecastItem.setPrecip(hourlyData.get(WeatherUtils.PRECIPS).get(i));
            hourlyForecastItem.setCondition(hourlyData.get(WeatherUtils.CONDITIONS).get(i));
            hourlyForecastItem.setWind(
                    hourlyData.get(WeatherUtils.WIND_DIRS).get(i)
                            + " "
                            + hourlyData.get(WeatherUtils.WIND_SPEEDS).get(i));

            hourlyForecast.add(hourlyForecastItem);
        }
    }
}
