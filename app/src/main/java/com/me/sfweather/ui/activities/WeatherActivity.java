package com.me.sfweather.ui.activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;

import com.me.sfweather.R;
import com.me.sfweather.ui.fragments.DetailsFragment;
import com.me.sfweather.ui.fragments.ExtendedFragment;
import com.me.sfweather.ui.fragments.HourlyFragment;

public class WeatherActivity extends AppCompatActivity {

    public static final String HOURLY = "HOURLY";
    public static final String DETAILED = "DETAILED";
    public static final String EXTENDED = "EXTENDED";

    private static ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        // Setup Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Setup ViewPager and PagerAdapter
        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        ViewPagerAdapter pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(pagerAdapter);

        // Setup TabLayout
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.addTab(tabLayout.newTab().setText(HOURLY));
        tabLayout.addTab(tabLayout.newTab().setText(DETAILED));
        tabLayout.addTab(tabLayout.newTab().setText(EXTENDED));

        // Setup ProgressBar
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_weather, menu);

        // Setup SearchView
        SearchView searchView = (SearchView) menu.findItem(R.id.menu_search).getActionView();

        searchView.setIconified(true);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
//                mItemListAdapter.filter(newText);
                return true;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public static void setProgressBarVisibility(int visibility) {
        progressBar.setVisibility(visibility);
    }

    private class ViewPagerAdapter extends FragmentStatePagerAdapter {
        private int NUM_ITEMS = 3;

        public ViewPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        // Returns total number of pages
        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        // Returns the fragment to display for that page
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0: // Fragment # 0 - This will show FirstFragment
                    return HourlyFragment.newInstance("", "Page # 1");
                case 1: // Fragment # 0 - This will show FirstFragment different title
                    return DetailsFragment.newInstance("", "Page # 2");
                case 2: // Fragment # 1 - This will show SecondFragment
                    return ExtendedFragment.newInstance("", "Page # 3");
                default:
                    return null;
            }
        }

        // Returns the page title for the top indicator
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return HOURLY;
                case 1:
                    return DETAILED;
                case 2:
                    return EXTENDED;
                default:
                    return "";
            }
        }

    }
}
