package com.me.sfweather.utilities;

import com.me.sfweather.models.ExtendedForecast;
import com.me.sfweather.models.HourlyForecast;

import java.util.HashMap;
import java.util.List;

/**
 * Created by erin.kelley on 8/8/17.
 */

public class WeatherUtils {
    // Intent Strings
    public static String HOURLY_DATA_RECEIVED_FILTER = "Hourly Data Received";
    public static String EXTENDED_DATA_RECEIVED_FILTER = "Extended Data Received";
    public static String HOURLY_JSON_DATA = "Hourly JSON Data";
    public static String EXTENDED_JSON_DATA = "Extended JSON Data";

    // Hourly Strings
    public static String TIMES = "times";
    public static String TEMPS = "temps";
    public static String PRECIPS = "precips";
    public static String WIND_SPEEDS = "wind speeds";
    public static String WIND_DIRS = "wind directions";
    public static String CONDITIONS = "conditions";

    // Extended Strings
    public static String DAYS_OF_WEEK = "days of week";
    public static String DESCS = "descriptions";
    public static String DATES = "dates";
    public static String HIGH_TEMPS = "high temps";
    public static String LOW_TEMPS = "low temps";
    public static String DAY_WIND_MPHS = "day wind mphs";
    public static String NIGHT_WIND_MPHS = "night wind mphs";
    public static String DAY_WIND_DIRS = "day wind dirs";
    public static String NIGHT_WIND_DIRS = "night wind dirs";

    // Conditions
    public static final String CLEAR_DAY = "clear";
    public static final String PARTLY_CLOUDY_DAY = "partlycloudy";
    public static final String MOSTLY_CLOUDY_DAY = "mostlycloudy";
    public static final String CHANCE_T_STORMS_DAY = "chancetstorms";
    public static final String CHANCE_RAIN_DAY = "chancerain";
    public static final String CLEAR_NIGHT = "nt_clear";
    public static final String PARTLY_CLOUDY_NIGHT = "nt_partlycloudy";
    public static final String MOSTLY_CLOUDY_NIGHT= "nt_mostlycloudy";
    public static final String CHANCE_T_STORMS_NIGHT = "nt_chancetstorms";
    public static final String CHANCE_RAIN_NIGHT = "nt_chancerain";

    // Degrees Symbol
    public static final String DEGREE_SYMBOL = " \u2109";
    public static final String PERCENT_SYMBOL = " %";

    // 7 day forecast
    private static final int numDays = 7;

    public static void createHourlyForecast(List<HourlyForecast> hourlyForecast,
                                            HashMap<String, List<String>> hourlyData) {
        int numHours = hourlyData.get(TIMES).size();

        for (int i = 0; i < numHours; i++) {
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

    public static void createExtendedForecast(List<ExtendedForecast> extendedForecast,
                                            HashMap<String, List<String>> extendedData) {
        int dayIndex;
        int nightIndex;

        for (int i = 0; i < numDays; i++) {
            ExtendedForecast extendedForecastItem = new ExtendedForecast();

            extendedForecastItem.setId(i);

            extendedForecastItem.setDate(extendedData.get(WeatherUtils.DATES).get(i));
            extendedForecastItem.setHighTemp(extendedData.get(WeatherUtils.HIGH_TEMPS).get(i));
            extendedForecastItem.setLowTemp(extendedData.get(WeatherUtils.LOW_TEMPS).get(i));
            extendedForecastItem.setWindMPHDay(extendedData.get(WeatherUtils.DAY_WIND_MPHS).get(i));
            extendedForecastItem.setWindMPHNight(extendedData.get(WeatherUtils.NIGHT_WIND_MPHS).get(i));
            extendedForecastItem.setWindDirDay(extendedData.get(WeatherUtils.DAY_WIND_DIRS).get(i));
            extendedForecastItem.setWindDirNight(extendedData.get(WeatherUtils.NIGHT_WIND_DIRS).get(i));

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
    }
}
