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

        }
    }

    @Override
    public int getItemCount() {
        return extendedForecast.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView dayOfWeek;
        TextView highTemp;
        TextView lowTemp;
        TextView windDayDesc;
        TextView windNightDesc;
        TextView precipDay;
        TextView precipNight;
        TextView descDay;
        TextView descNight;
        ImageView condDay;
        ImageView condNight;

        ViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cvExtendedForecast);
            dayOfWeek = (TextView) itemView.findViewById(R.id.tvDayOfWeek);
            highTemp = (TextView) itemView.findViewById(R.id.tvHighTemp);
            lowTemp = (TextView) itemView.findViewById(R.id.tvLowTemp);
            precipDay = (TextView) itemView.findViewById(R.id.tvDayPrecip);
            precipNight = (TextView) itemView.findViewById(R.id.tvNightPrecip);
            windDayDesc = (TextView) itemView.findViewById(R.id.tvDayWindDesc);
            windNightDesc = (TextView) itemView.findViewById(R.id.tvDayWindDesc);
            descDay = (TextView) itemView.findViewById(R.id.tvDayDesc);
            descNight = (TextView) itemView.findViewById(R.id.tvNightDesc);
            condDay = (ImageView) itemView.findViewById(R.id.ivDayCond);
            condNight = (ImageView) itemView.findViewById(R.id.ivNightCond);
        }
    }
}
