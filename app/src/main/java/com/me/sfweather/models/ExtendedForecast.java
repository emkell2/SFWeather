package com.me.sfweather.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by erin.kelley on 8/9/17.
 */

public class ExtendedForecast implements Parcelable {
    private int id;
    private String dayOfWeek;
    private String date;
    private String condDay;
    private String condNight;
    private String condDesc;
    private String HighTemp;
    private String LowTemp;
    private String windMPHDay;
    private String windMPHNight;
    private String windDirDay;
    private String windDirNight;
    private String precipDay;
    private String precipNight;
    private String descDay;
    private String descNight;

    public ExtendedForecast() {
        // Empty Constructor
    }

    protected ExtendedForecast(Parcel in) {
        id = in.readInt();
        dayOfWeek = in.readString();
        date = in.readString();
        condDay = in.readString();
        condNight = in.readString();
        condDesc = in.readString();
        HighTemp = in.readString();
        LowTemp = in.readString();
        windMPHDay = in.readString();
        windMPHNight = in.readString();
        windDirDay = in.readString();
        windDirNight = in.readString();
        precipDay = in.readString();
        precipNight = in.readString();
        descDay = in.readString();
        descNight = in.readString();
    }

    public static final Creator<ExtendedForecast> CREATOR = new Creator<ExtendedForecast>() {
        @Override
        public ExtendedForecast createFromParcel(Parcel in) {
            return new ExtendedForecast(in);
        }

        @Override
        public ExtendedForecast[] newArray(int size) {
            return new ExtendedForecast[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCondDay() {
        return condDay;
    }

    public void setCondDay(String condDay) {
        this.condDay = condDay;
    }

    public String getCondDesc() {
        return condDesc;
    }

    public void setCondDesc(String condDesc) {
        this.condDesc = condDesc;
    }

    public String getCondNight() {
        return condNight;
    }

    public void setCondNight(String condNight) {
        this.condNight = condNight;
    }

    public String getHighTemp() {
        return HighTemp;
    }

    public void setHighTemp(String highTemp) {
        HighTemp = highTemp;
    }

    public String getLowTemp() {
        return LowTemp;
    }

    public void setLowTemp(String lowTemp) {
        LowTemp = lowTemp;
    }

    public String getWindMPHDay() {
        return windMPHDay;
    }

    public void setWindMPHDay(String windMPHDay) {
        this.windMPHDay = windMPHDay;
    }

    public String getWindMPHNight() {
        return windMPHNight;
    }

    public void setWindMPHNight(String windMPHNight) {
        this.windMPHNight = windMPHNight;
    }

    public String getWindDirDay() {
        return windDirDay;
    }

    public void setWindDirDay(String windDirDay) {
        this.windDirDay = windDirDay;
    }

    public String getWindDirNight() {
        return windDirNight;
    }

    public void setWindDirNight(String windDirNight) {
        this.windDirNight = windDirNight;
    }

    public String getPrecipDay() {
        return precipDay;
    }

    public void setPrecipDay(String precipDay) {
        this.precipDay = precipDay;
    }

    public String getPrecipNight() {
        return precipNight;
    }

    public void setPrecipNight(String precipNight) {
        this.precipNight = precipNight;
    }

    public String getDescDay() {
        return descDay;
    }

    public void setDescDay(String descDay) {
        this.descDay = descDay;
    }

    public String getDescNight() {
        return descNight;
    }

    public void setDescNight(String descNight) {
        this.descNight = descNight;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(dayOfWeek);
        dest.writeString(date);
        dest.writeString(condDay);
        dest.writeString(condNight);
        dest.writeString(condDesc);
        dest.writeString(HighTemp);
        dest.writeString(LowTemp);
        dest.writeString(windMPHDay);
        dest.writeString(windMPHNight);
        dest.writeString(windDirDay);
        dest.writeString(windDirNight);
        dest.writeString(precipDay);
        dest.writeString(precipNight);
        dest.writeString(descDay);
        dest.writeString(descNight);
    }
}
