package com.me.sfweather.ui.fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.me.sfweather.R;
import com.me.sfweather.adapters.ExtendedForecastAdapter;
import com.me.sfweather.managers.NetworkManager;
import com.me.sfweather.models.HourlyForecast;
import com.me.sfweather.ui.activities.WeatherActivity;
import com.me.sfweather.utilities.JSONUtils;
import com.me.sfweather.utilities.WeatherUtils;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ExtendedFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ExtendedFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ExtendedFragment extends Fragment {

    // List of all hourly forecast data
    private ArrayList<HourlyForecast> mExtendedForecastList;
    private ExtendedForecastAdapter mExtendedForecastAdapter;

    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(WeatherUtils.HOURLY_DATA_RECEIVED_FILTER)) {
                WeatherActivity.setProgressBarVisibility(View.GONE);
                String jsonString = intent.getExtras().getString(WeatherUtils.HOURLY_JSON_DATA);

                // Parse json String for the hourly data
                HashMap hourlyData = JSONUtils.parseHourlyJSONData(jsonString);

                // Create hourly forecast models and store the data in the list
                WeatherUtils.createHourlyForecast(mExtendedForecastList, hourlyData);
                mExtendedForecastAdapter.notifyDataSetChanged();
            }
        }
    };

    private OnFragmentInteractionListener mListener;

    public ExtendedFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ExtendedFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ExtendedFragment newInstance(String param1, String param2) {
        ExtendedFragment fragment = new ExtendedFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Register broadcast receiver
        LocalBroadcastManager.getInstance(getContext()).registerReceiver(mMessageReceiver,
                new IntentFilter(WeatherUtils.EXTENDED_DATA_RECEIVED_FILTER));

        WeatherActivity.setProgressBarVisibility(View.VISIBLE);
        NetworkManager.getInstance(getContext()).getHourlyForecastData("");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_extended, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDestroy() {
        LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(mMessageReceiver);
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
