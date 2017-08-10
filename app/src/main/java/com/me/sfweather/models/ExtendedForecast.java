package com.me.sfweather.models;

/**
 * Created by erin.kelley on 8/9/17.
 */

public class ExtendedForecast {
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
}
