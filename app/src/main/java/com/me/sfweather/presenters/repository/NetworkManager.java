package com.me.sfweather.presenters.repository;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;

import com.me.sfweather.utilities.WeatherUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by erin.kelley on 8/8/17.
 */

public class NetworkManager {
    private static NetworkManager sInstance;
    private Context mContext;
    private static final String API_KEY = "26db30d44f3121e2";

    private static final String HOURLY = "hourly";
    private static final String CURRENT = "current";
    private static final String EXTENDED = "extended";

    public NetworkManager(Context context) {
        mContext = context;
    }

    public static NetworkManager getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new NetworkManager(context);
        }

        return sInstance;
    }

    public void getHourlyForecastData(String location) {
        String loc;
        if (!TextUtils.isEmpty(location)) {
            loc = location;
        } else {
            loc = "Durham, NC";
        }

        try {
            URL url = new URL("http://api.wunderground.com/api/26db30d44f3121e2/hourly/q/NC/Durham.json");
            getData(url, HOURLY);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void getCurrentForecastData(String location) {
        try {
            URL url = new URL("http://api.wunderground.com/api/26db30d44f3121e2/conditions/q/NC/Durham.json");
            getData(url, CURRENT);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void getExtendedForecastData(String location) {
        try {
            URL url = new URL("http://api.wunderground.com/api/26db30d44f3121e2/forecast10day/q/NC/Durham.json");
            getData(url, EXTENDED);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void getData(final URL url, final String forecastType) {
        new Thread() {
            @Override
            public void run() {
                HttpURLConnection urlConnection;

                try {
                    urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setRequestMethod("GET");
                    urlConnection.setReadTimeout(10000 /* milliseconds */ );
                    urlConnection.setConnectTimeout(15000 /* milliseconds */ );
                    urlConnection.setDoOutput(true);
                    urlConnection.connect();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                StringBuilder sb = new StringBuilder();
                try {
                    BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));

                    String line;
                    while ((line = br.readLine()) != null) {
                        sb.append(line + "\n");
                    }

                    if (br != null) {
                        br.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                String jsonString = sb.toString();

                String intentActionStr = "";
                String intentDataStr = "";

                switch (forecastType) {
                    case HOURLY:
                        intentActionStr = WeatherUtils.HOURLY_DATA_RECEIVED_FILTER;
                        intentDataStr = WeatherUtils.HOURLY_JSON_DATA;
                        break;
                    case CURRENT:
                        intentActionStr = WeatherUtils.CURRENT_DATA_RECEIVED_FILTER;
                        intentDataStr = WeatherUtils.CURRENT_JSON_DATA;
                        break;
                    case EXTENDED:
                        intentActionStr = WeatherUtils.EXTENDED_DATA_RECEIVED_FILTER;
                        intentDataStr = WeatherUtils.EXTENDED_JSON_DATA;
                        break;
                }
                Intent dataReceivedIntent = new Intent(intentActionStr);
                dataReceivedIntent.putExtra(intentDataStr, jsonString);
                LocalBroadcastManager.getInstance(mContext).sendBroadcast(dataReceivedIntent);
            }
        }.start();
    }
}
