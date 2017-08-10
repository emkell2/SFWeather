package com.me.sfweather.views.fragments;

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
import android.widget.ImageView;
import android.widget.TextView;

import com.me.sfweather.R;
import com.me.sfweather.models.CurrentForecast;
import com.me.sfweather.presenters.WeatherPresenter;
import com.me.sfweather.utilities.WeatherUtils;
import com.me.sfweather.views.interfaces.ViewInterface;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CurrentFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CurrentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CurrentFragment extends Fragment implements ViewInterface {
    private TextView feelsLike;
    private TextView conditionDesc;
    private TextView wind;
    private TextView humidity;
    private TextView dewPoint;
    private TextView pressure;
    private TextView uvIndex;
    private ImageView condition;

    private CurrentForecast mCurrentForecast;

    private WeatherPresenter mPresenter;

    private OnFragmentInteractionListener mListener;

    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(WeatherUtils.ACTION_UPDATE_CURRENT_VIEWS)) {
                mCurrentForecast = intent.getParcelableExtra(WeatherUtils.CURRENT_DATA);
                populateViews(mCurrentForecast);
            } else {
                mPresenter.handleData(intent);
            }
        }
    };

    public CurrentFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CurrentFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CurrentFragment newInstance(String param1, String param2) {
        CurrentFragment fragment = new CurrentFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        IntentFilter filter = new IntentFilter();
        filter.addAction(WeatherUtils.CURRENT_DATA_RECEIVED_FILTER);
        filter.addAction(WeatherUtils.ACTION_UPDATE_CURRENT_VIEWS);

        // Register broadcast receiver
        LocalBroadcastManager.getInstance(getContext()).registerReceiver(mMessageReceiver, filter);

        mPresenter = new WeatherPresenter(getContext(), this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_details, container, false);

        feelsLike = (TextView) v.findViewById(R.id.tvCurrTemp);
        conditionDesc = (TextView) v.findViewById(R.id.tvConditionDesc);
        wind = (TextView) v.findViewById(R.id.currWind);
        humidity = (TextView) v.findViewById(R.id.currHumidity);
        dewPoint = (TextView) v.findViewById(R.id.currDewPoint);
        pressure = (TextView) v.findViewById(R.id.currPressure);
        uvIndex = (TextView) v.findViewById(R.id.currUVIndex);
        condition = (ImageView) v.findViewById(R.id.ivCurrWeather);

        return v;
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
    public void onResume() {
        super.onResume();

        if (mPresenter != null) {
            mPresenter.getCurrentForecastData();
        }
    }

    @Override
    public void onNewData() {
//        populateViews(mCurrentForecast);
    }

    public void populateViews(CurrentForecast currentForecast) {
        feelsLike.setText(currentForecast.getFeelsLike() + WeatherUtils.DEGREE_SYMBOL);
        conditionDesc.setText(currentForecast.getConditionDesc());
        wind.setText(currentForecast.getWind());
        humidity.setText(currentForecast.getHumidity());
        dewPoint.setText(currentForecast.getDewPoint());
        pressure.setText(currentForecast.getPressure());
        uvIndex.setText(currentForecast.getUvIndex());

        condition.setImageDrawable(WeatherUtils.getConditionDrawable(getContext(),
                currentForecast.getCondition()));
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
