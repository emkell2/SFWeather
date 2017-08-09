package com.me.sfweather.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.me.sfweather.R;
import com.me.sfweather.models.HourlyForecast;
import com.me.sfweather.utilities.WeatherUtils;

import java.util.ArrayList;

/**
 * Created by erin.kelley on 8/8/17.
 */

public class HourlyForecastAdapter extends RecyclerView.Adapter<HourlyForecastAdapter.ViewHolder> {

    Context mContext;
    ArrayList<HourlyForecast> hourlyForecast;

    public HourlyForecastAdapter(Context context, ArrayList<HourlyForecast> hourlyForecast){
        mContext = context;
        this.hourlyForecast = hourlyForecast;
    }

    @Override
    public HourlyForecastAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_hourly, parent, false);

        HourlyForecastAdapter.ViewHolder vh = new HourlyForecastAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(HourlyForecastAdapter.ViewHolder holder, int position) {
        if (mContext != null) {
            String time = formatTime(hourlyForecast.get(position).getTime());
            holder.time.setText(time);
            holder.temp.setText(hourlyForecast.get(position).getTemp() + WeatherUtils.DEGREE_SYMBOL);
            holder.precip.setText(hourlyForecast.get(position).getPrecip() + WeatherUtils.PERCENT_SYMBOL);
            holder.wind.setText(hourlyForecast.get(position).getWind() + " MPH");

            // Set condition data
            String condition = hourlyForecast.get(position).getCondition();
            condition = condition.replace(".gif", "");
            condition = condition.substring(28);
            if (!TextUtils.isEmpty(condition)) {
                switch (condition.toLowerCase()) {
                    case WeatherUtils.CLEAR_DAY:
                        holder.condition.setImageDrawable(mContext.getResources().getDrawable(R.drawable.clear));
                        break;
                    case WeatherUtils.CLEAR_NIGHT:
                        holder.condition.setImageDrawable(mContext.getResources().getDrawable(R.drawable.nt_clear));
                        break;
                    case WeatherUtils.PARTLY_CLOUDY_DAY:
                    case WeatherUtils.MOSTLY_CLOUDY_DAY:
                        holder.condition.setImageDrawable(mContext.getResources().getDrawable(R.drawable.cloudy));
                        break;
                    case WeatherUtils.PARTLY_CLOUDY_NIGHT:
                    case WeatherUtils.MOSTLY_CLOUDY_NIGHT:
                        holder.condition.setImageDrawable(mContext.getResources().getDrawable(R.drawable.nt_cloudy));
                        break;
                    case WeatherUtils.CHANCE_RAIN_DAY:
                    case WeatherUtils.CHANCE_RAIN_NIGHT:
                        holder.condition.setImageDrawable(mContext.getResources().getDrawable(R.drawable.chancerain));
                        break;
                    case WeatherUtils.CHANCE_T_STORMS_DAY:
                    case WeatherUtils.CHANCE_T_STORMS_NIGHT:
                        holder.condition.setImageDrawable(mContext.getResources().getDrawable(R.drawable.chancetstorms));
                        break;
                    default:
                        holder.condition.setImageDrawable(mContext.getResources().getDrawable(R.drawable.clear));
                        break;
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return hourlyForecast.size();
    }

    private String formatTime(String rawTime) {
        String result = rawTime.replace(":00", "");

        return result;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView time;
        TextView temp;
        TextView precip;
        TextView wind;
        ImageView condition;

        ViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cvHourlyForecast);
            time = (TextView) itemView.findViewById(R.id.tvTime);
            temp = (TextView) itemView.findViewById(R.id.tvTemp);
            precip = (TextView) itemView.findViewById(R.id.tvPrecip);
            wind = (TextView) itemView.findViewById(R.id.tvWind);
            condition = (ImageView) itemView.findViewById(R.id.ivCondition);
        }
    }
}