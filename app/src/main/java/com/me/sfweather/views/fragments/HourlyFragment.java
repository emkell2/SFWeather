package com.me.sfweather.views.fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.me.sfweather.R;
import com.me.sfweather.models.HourlyForecast;
import com.me.sfweather.presenters.WeatherPresenter;
import com.me.sfweather.presenters.adapters.HourlyForecastAdapter;
import com.me.sfweather.presenters.repository.NetworkManager;
import com.me.sfweather.utilities.WeatherConst;
import com.me.sfweather.views.interfaces.ViewInterface;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HourlyFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HourlyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HourlyFragment extends Fragment implements ViewInterface {
    private WeatherPresenter mPresenter;

    // List of all hourly forecast data
    private ArrayList<HourlyForecast> mHourlyForecastList;
    private HourlyForecastAdapter mHourlyForecastAdapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(WeatherConst.ACTION_UPDATE_HOURLY_VIEWS)) {
                ArrayList<HourlyForecast> data = intent.getParcelableArrayListExtra(WeatherConst.HOURLY_DATA);
                mHourlyForecastList.addAll(data);
            } else {
                mPresenter.handleData(intent);
            }

            if ((mSwipeRefreshLayout != null) && (mSwipeRefreshLayout.isRefreshing())) {
                mSwipeRefreshLayout.setRefreshing(false);
            }
        }
    };

    private OnFragmentInteractionListener mListener;

    public HourlyFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HourlyFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HourlyFragment newInstance(String param1, String param2) {
        HourlyFragment fragment = new HourlyFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        IntentFilter filter = new IntentFilter();
        filter.addAction(WeatherConst.HOURLY_DATA_RECEIVED_FILTER);
        filter.addAction(WeatherConst.ACTION_UPDATE_HOURLY_VIEWS);

        // Register broadcast receiver
        LocalBroadcastManager.getInstance(getContext()).registerReceiver(mMessageReceiver, filter);

        mPresenter = new WeatherPresenter(getContext(), this);
        mPresenter.getHourlyForecastData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_hourly, container, false);

        // Setup RecyclerView
        RecyclerView mRecyclerView = (RecyclerView) v.findViewById(R.id.rvHourlyForecast);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Create hourlyForecastList and Adapter
        mHourlyForecastList = new ArrayList<>();
        mHourlyForecastAdapter = new HourlyForecastAdapter(getContext(), mHourlyForecastList);

        mRecyclerView.setAdapter(mHourlyForecastAdapter);

        mSwipeRefreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.swipeRefreshLayout);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.Blue_700);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshItems();
            }
        });

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
    public void onNewData() {
        mHourlyForecastAdapter.notifyDataSetChanged();
    }

    public void populateViews(ArrayList<HourlyForecast> hourlyForecastList) {
        mHourlyForecastList = hourlyForecastList;
        mHourlyForecastAdapter.notifyDataSetChanged();
    }

    private void refreshItems() {
        NetworkManager.getInstance(getContext()).getHourlyForecastData("");
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
