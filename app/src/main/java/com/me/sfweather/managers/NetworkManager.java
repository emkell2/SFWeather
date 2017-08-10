package com.me.sfweather.managers;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Log;

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

        new Thread() {
            @Override
            public void run() {
                HttpURLConnection urlConnection = null;
                URL url = null;
                try {
                    url = new URL("http://api.wunderground.com/api/26db30d44f3121e2/hourly/q/NC/Durham.json");
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }

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

                BufferedReader br = null;
                StringBuilder sb = new StringBuilder();
                try {
                    br = new BufferedReader(new InputStreamReader(url.openStream()));

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
                Log.d("Hourly JSON Data: ", "JSON: " + jsonString);

                Intent hourlyDataReceivedIntent = new Intent(WeatherUtils.HOURLY_DATA_RECEIVED_FILTER);
                hourlyDataReceivedIntent.putExtra(WeatherUtils.HOURLY_JSON_DATA, jsonString);
                LocalBroadcastManager.getInstance(mContext).sendBroadcast(hourlyDataReceivedIntent);
            }
        }.start();
    }

    public void getCurrentForecastData(String location) {
        new Thread() {
            @Override
            public void run() {
                HttpURLConnection urlConnection = null;
                URL url = null;
                try {
                    url = new URL("http://api.wunderground.com/api/26db30d44f3121e2/conditions/q/NC/Durham.json");
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }

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

                BufferedReader br = null;
                StringBuilder sb = new StringBuilder();
                try {
                    br = new BufferedReader(new InputStreamReader(url.openStream()));

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
                Log.d("Hourly JSON Data: ", "JSON: " + jsonString);

                Intent extendedDataReceivedIntent = new Intent(WeatherUtils.EXTENDED_DATA_RECEIVED_FILTER);
                extendedDataReceivedIntent.putExtra(WeatherUtils.EXTENDED_JSON_DATA, jsonString);
                LocalBroadcastManager.getInstance(mContext).sendBroadcast(extendedDataReceivedIntent);
            }
        }.start();
    }

    public void getExtendedForecastData(String location) {
        new Thread() {
            @Override
            public void run() {
                HttpURLConnection urlConnection = null;
                URL url = null;
                try {
                    url = new URL("http://api.wunderground.com/api/26db30d44f3121e2/forecast10day/q/NC/Durham.json");
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }

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

                BufferedReader br = null;
                StringBuilder sb = new StringBuilder();
                try {
                    br = new BufferedReader(new InputStreamReader(url.openStream()));

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
                Log.d("Hourly JSON Data: ", "JSON: " + jsonString);

                Intent extendedDataReceivedIntent = new Intent(WeatherUtils.EXTENDED_DATA_RECEIVED_FILTER);
                extendedDataReceivedIntent.putExtra(WeatherUtils.EXTENDED_JSON_DATA, jsonString);
                LocalBroadcastManager.getInstance(mContext).sendBroadcast(extendedDataReceivedIntent);
            }
        }.start();
    }
}
