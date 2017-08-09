package com.me.sfweather.utilities;

import com.jayway.jsonpath.JsonPath;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by erin.kelley on 8/8/17.
 */

public class JSONUtils {
    public static HashMap<String, List<String>> parseHourlyJSONData(String jsonString) {
        HashMap<String, List<String>> data = new HashMap<>();

        // Each of these are seperate reads. Doing it for sake of time, should do 1 read for efficiency.
        List<String> times = JsonPath.read(jsonString, "$.hourly_forecast[*].FCTTIME.civil");
        List<String> temps = JsonPath.read(jsonString, "$.hourly_forecast[*].temp.english");
        List<String> precip = JsonPath.read(jsonString, "$.hourly_forecast[*].pop");
        List<String> windSpeed = JsonPath.read(jsonString, "$.hourly_forecast[*].wspd.english");
        List<String> windDir = JsonPath.read(jsonString, "$.hourly_forecast[*].wdir.dir");
        List<String> condition = JsonPath.read(jsonString, "$.hourly_forecast[*].icon_url");

        data.put(WeatherUtils.TIMES, times);
        data.put(WeatherUtils.TEMPS, temps);
        data.put(WeatherUtils.PRECIPS, precip);
        data.put(WeatherUtils.WIND_SPEEDS, windSpeed);
        data.put(WeatherUtils.WIND_DIRS, windDir);
        data.put(WeatherUtils.CONDITIONS, condition);

        return data;
    }

    public static HashMap<String, List<String>> parseExtendedJSONData(String jsonString) {
        HashMap<String, List<String>> data = new HashMap<>();

        // Each of these are seperate reads. Doing it for sake of time, should do 1 read for efficiency.
        List<String> daysOfWeek = JsonPath.read(jsonString, "$.forecast.txt_forecast.forecastday[*].title");
        List<String> conds = JsonPath.read(jsonString, "$.forecast.txt_forecast.forecastday[*].icon");
        List<String> precips = JsonPath.read(jsonString, "$.forecast.txt_forecast.forecastday[*].pop");
        List<String> descs = JsonPath.read(jsonString, "$.forecast.txt_forecast.forecastday[*].fcttext");

        List<String> months = JsonPath.read(jsonString, "$.forecast.simpleforecast.forecastday[*].date.monthname");
        List<String> days = JsonPath.read(jsonString, "$.forecast.simpleforecast.forecastday[*].date.day");
        List<String> highTemps = JsonPath.read(jsonString, "$.forecast.simpleforecast.forecastday[*].high.fahrenheit");
        List<String> lowTemps = JsonPath.read(jsonString, "$.forecast.simpleforecast.forecastday[*].low.fahrenheit");
        List<String> dayWindMPHs = JsonPath.read(jsonString, "$.forecast.simpleforecast.forecastday[*].maxwind.mph");
        List<String> nightWindMPHs = JsonPath.read(jsonString, "$.forecast.simpleforecast.forecastday[*].avewind.mph");
        List<String> dayWindDirs = JsonPath.read(jsonString, "$.forecast.simpleforecast.forecastday[*].maxwind.dir");
        List<String> nightWindDirs = JsonPath.read(jsonString, "$.forecast.simpleforecast.forecastday[*].avewind.dir");

        List<String> dates = new ArrayList<>();
        for (int i = 0; i < months.size(); i++) {
            dates.add(months.get(i) + " " + days.get(i));
        }

        data.put(WeatherUtils.DAYS_OF_WEEK, daysOfWeek);
        data.put(WeatherUtils.DATES, dates);
        data.put(WeatherUtils.HIGH_TEMPS, highTemps);
        data.put(WeatherUtils.LOW_TEMPS, lowTemps);
        data.put(WeatherUtils.CONDITIONS, conds);
        data.put(WeatherUtils.DESCS, descs);
        data.put(WeatherUtils.PRECIPS, precips);
        data.put(WeatherUtils.DAY_WIND_MPHS, dayWindMPHs);
        data.put(WeatherUtils.NIGHT_WIND_MPHS, nightWindMPHs);
        data.put(WeatherUtils.DAY_WIND_DIRS, dayWindDirs);
        data.put(WeatherUtils.NIGHT_WIND_DIRS, nightWindDirs);

        return data;
    }
}
