package com.me.sfweather.utilities;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;

import com.me.sfweather.R;

/**
 * Created by erin.kelley on 8/8/17.
 */

public class WeatherUtils {

    public static Drawable getConditionDrawable(Context context, String condition) {
        if (!TextUtils.isEmpty(condition)) {
            switch (condition.toLowerCase()) {
                case WeatherConst.CLEAR_DAY:
                   return context.getResources().getDrawable(R.drawable.clear);
                case WeatherConst.CLEAR_NIGHT:
                    return context.getResources().getDrawable(R.drawable.nt_clear);
                case WeatherConst.PARTLY_CLOUDY_DAY:
                case WeatherConst.MOSTLY_CLOUDY_DAY:
                    return context.getResources().getDrawable(R.drawable.cloudy);
                case WeatherConst.PARTLY_CLOUDY_NIGHT:
                case WeatherConst.MOSTLY_CLOUDY_NIGHT:
                    return context.getResources().getDrawable(R.drawable.nt_cloudy);
                case WeatherConst.CHANCE_RAIN_DAY:
                case WeatherConst.CHANCE_RAIN_NIGHT:
                     return context.getResources().getDrawable(R.drawable.chancerain);
                case WeatherConst.CHANCE_T_STORMS_DAY:
                case WeatherConst.CHANCE_T_STORMS_NIGHT:
                    return context.getResources().getDrawable(R.drawable.chancetstorms);
                default:
                    return context.getResources().getDrawable(R.drawable.clear);
            }
        }
        return null;
    }
}
