package com.me.sfweather.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by erin.kelley on 8/8/17.
 */

public class HourlyForecast implements Parcelable {

    private int id;
    private String time;
    private String temp;
    private String precip;
    private String wind;
    private String condition;

    public HourlyForecast() {
        // Empty Constructor
    }

    protected HourlyForecast(Parcel in) {
        id = in.readInt();
        time = in.readString();
        temp = in.readString();
        precip = in.readString();
        wind = in.readString();
        condition = in.readString();
    }

    public static final Creator<HourlyForecast> CREATOR = new Creator<HourlyForecast>() {
        @Override
        public HourlyForecast createFromParcel(Parcel in) {
            return new HourlyForecast(in);
        }

        @Override
        public HourlyForecast[] newArray(int size) {
            return new HourlyForecast[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getPrecip() {
        return precip;
    }

    public void setPrecip(String precip) {
        this.precip = precip;
    }

    public String getWind() {
        return wind;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(time);
        dest.writeString(temp);
        dest.writeString(precip);
        dest.writeString(wind);
        dest.writeString(condition);
    }
}
