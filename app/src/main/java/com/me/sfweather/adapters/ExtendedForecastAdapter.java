package com.me.sfweather.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.me.sfweather.R;
import com.me.sfweather.models.ExtendedForecast;
import com.me.sfweather.utilities.WeatherUtils;

import java.util.ArrayList;

/**
 * Created by erin.kelley on 8/9/17.
 */

public class ExtendedForecastAdapter extends RecyclerView.Adapter<ExtendedForecastAdapter.ViewHolder> {

    Context mContext;
    ArrayList<ExtendedForecast> extendedForecast;

    public ExtendedForecastAdapter(Context context, ArrayList<ExtendedForecast> extendedForecast){
        mContext = context;
        this.extendedForecast = extendedForecast;
    }

    @Override
    public ExtendedForecastAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_extended, parent, false);

        ExtendedForecastAdapter.ViewHolder vh = new ExtendedForecastAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ExtendedForecastAdapter.ViewHolder holder, int position) {
        if (mContext != null) {
            holder.dayOfWeek.setText(extendedForecast.get(position).getDayOfWeek());
            holder.date.setText(extendedForecast.get(position).getDate());
            holder.dayTitle.setText(extendedForecast.get(position).getDayOfWeek().toUpperCase());
            holder.nightTitle.setText(extendedForecast.get(position).getDayOfWeek().toUpperCase() + " NIGHT");
            holder.highTemp.setText(extendedForecast.get(position).getHighTemp() + WeatherUtils.DEGREE_SYMBOL);
            holder.lowTemp.setText(extendedForecast.get(position).getLowTemp() + WeatherUtils.DEGREE_SYMBOL);
            holder.windDayDesc.setText(
                    extendedForecast.get(position).getWindDirDay()
                            + " "
                            + extendedForecast.get(position).getWindMPHDay()
                            + " MPH");
            holder.windNightDesc.setText(
                    extendedForecast.get(position).getWindDirNight()
                            + " "
                            + extendedForecast.get(position).getWindMPHNight()
                            + " MPH");
            holder.precipDay.setText(extendedForecast.get(position).getPrecipDay() + WeatherUtils.PERCENT_SYMBOL);
            holder.precipNight.setText(extendedForecast.get(position).getPrecipNight() + WeatherUtils.PERCENT_SYMBOL);
            holder.descDay.setText(extendedForecast.get(position).getDescDay());
            holder.descNight.setText(extendedForecast.get(position).getDescNight());

            // Set condition data
            String condDay = extendedForecast.get(position).getCondDay();
            holder.condDay.setImageDrawable(WeatherUtils.getConditionDrawable(mContext, condDay));
            String condNight = extendedForecast.get(position).getCondNight();
            holder.condNight.setImageDrawable(WeatherUtils.getConditionDrawable(mContext, condNight));

            holder.condDayDesc.setText(extendedForecast.get(position).getCondDesc());
            holder.condNightDesc.setText(extendedForecast.get(position).getCondDesc());
        }
    }

    @Override
    public int getItemCount() {
        return extendedForecast.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView dayOfWeek;
        TextView date;
        TextView dayTitle;
        TextView nightTitle;
        TextView highTemp;
        TextView lowTemp;
        TextView windDayDesc;
        TextView windNightDesc;
        TextView precipDay;
        TextView precipNight;
        TextView descDay;
        TextView descNight;
        TextView condDayDesc;
        TextView condNightDesc;
        ImageView condDay;
        ImageView condNight;

        ViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cvExtendedForecast);
            dayOfWeek = (TextView) itemView.findViewById(R.id.tvDayOfWeek);
            date = (TextView) itemView.findViewById(R.id.tvDate);
            dayTitle = (TextView) itemView.findViewById(R.id.tvDayTitle);
            nightTitle = (TextView) itemView.findViewById(R.id.tvNightTitle);
            highTemp = (TextView) itemView.findViewById(R.id.tvHighTemp);
            lowTemp = (TextView) itemView.findViewById(R.id.tvLowTemp);
            precipDay = (TextView) itemView.findViewById(R.id.tvDayPrecip);
            precipNight = (TextView) itemView.findViewById(R.id.tvNightPrecip);
            windDayDesc = (TextView) itemView.findViewById(R.id.tvDayWindDesc);
            windNightDesc = (TextView) itemView.findViewById(R.id.tvDayWindDesc);
            descDay = (TextView) itemView.findViewById(R.id.tvDayDesc);
            descNight = (TextView) itemView.findViewById(R.id.tvNightDesc);
            condDayDesc = (TextView) itemView.findViewById(R.id.tvDayCondDesc);
            condNightDesc = (TextView) itemView.findViewById(R.id.tvNightCondDesc);
            condDay = (ImageView) itemView.findViewById(R.id.ivDayCond);
            condNight = (ImageView) itemView.findViewById(R.id.ivNightCond);
        }
    }
}
