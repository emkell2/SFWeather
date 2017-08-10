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
                holder.condition.setImageDrawable(WeatherUtils.getConditionDrawable(mContext, condition));
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