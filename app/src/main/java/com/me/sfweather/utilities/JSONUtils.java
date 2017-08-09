package com.me.sfweather.utilities;

import com.jayway.jsonpath.JsonPath;

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
}
