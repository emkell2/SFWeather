package com.me.sfweather.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by erin.kelley on 8/9/17.
 */

public class CurrentForecast implements Parcelable {
    private String id;
    private String feelsLike;
    private String condition;
    private String conditionDesc;
    private String wind;
    private String humidity;
    private String dewPoint;
    private String pressure;
    private String uvIndex;

    public CurrentForecast() {
        // Empty Constructor
    }

    protected CurrentForecast(Parcel in) {
        id = in.readString();
        feelsLike = in.readString();
        condition = in.readString();
        conditionDesc = in.readString();
        wind = in.readString();
        humidity = in.readString();
        dewPoint = in.readString();
        pressure = in.readString();
        uvIndex = in.readString();
    }

    public static final Creator<CurrentForecast> CREATOR = new Creator<CurrentForecast>() {
        @Override
        public CurrentForecast createFromParcel(Parcel in) {
            return new CurrentForecast(in);
        }

        @Override
        public CurrentForecast[] newArray(int size) {
            return new CurrentForecast[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFeelsLike() {
        return feelsLike;
    }

    public void setFeelsLike(String feelsLike) {
        this.feelsLike = feelsLike;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getConditionDesc() {
        return conditionDesc;
    }

    public void setConditionDesc(String conditionDesc) {
        this.conditionDesc = conditionDesc;
    }

    public String getWind() {
        return wind;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getDewPoint() {
        return dewPoint;
    }

    public void setDewPoint(String dewPoint) {
        this.dewPoint = dewPoint;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getUvIndex() {
        return uvIndex;
    }

    public void setUvIndex(String uvIndex) {
        this.uvIndex = uvIndex;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(id);
        dest.writeString(feelsLike);
        dest.writeString(condition);
        dest.writeString(conditionDesc);
        dest.writeString(wind);
        dest.writeString(humidity);
        dest.writeString(dewPoint);
        dest.writeString(pressure);
        dest.writeString(uvIndex);
    }
}
