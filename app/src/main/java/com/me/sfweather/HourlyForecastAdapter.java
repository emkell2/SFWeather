package com.me.sfweather;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.me.sfweather.models.HourlyForecast;

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
            holder.time.setText(hourlyForecast.get(position).getTime());
            holder.temp.setText(hourlyForecast.get(position).getTemp());
            holder.precip.setText(hourlyForecast.get(position).getPrecip());
            holder.wind.setText(hourlyForecast.get(position).getWind());

            // Set condition data
            String condition = hourlyForecast.get(position).getCondition();
            if (!TextUtils.isEmpty(condition)) {
                switch (condition) {
//                    case WorkoutUtilities.AMAZING:
//                    case WorkoutUtilities.GREAT:
//                        holder.faceImage.setImageDrawable(mContext.getResources()
//                                .getDrawable(R.drawable.ic_smilie_happy));
//                        break;
//                    case WorkoutUtilities.GOOD:
//                        holder.faceImage.setImageDrawable(mContext.getResources()
//                                .getDrawable(R.drawable.ic_smilie_satisfied));
//                        break;
//                    case WorkoutUtilities.BAD:
//                    case WorkoutUtilities.AWFUL:
//                        holder.faceImage.setImageDrawable(mContext.getResources()
//                                .getDrawable(R.drawable.ic_smilie_sad));
//                        break;
//                    default:
//                        holder.faceImage.setImageDrawable(mContext.getResources()
//                                .getDrawable(R.drawable.ic_smilie_happy));
//                        break;
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return hourlyForecast.size();
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