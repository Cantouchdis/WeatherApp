package com.example.ben.weatherapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;

public class WeatherActivity extends SingleFragmentActivity {

    private static final String TAG="WeatherActivity";
    private static final String API_KEY="943d5d41bbcc6b434eaaef9c9baf94e7";
    private static final String FETCH_ONE_METHOD="api.openweathermap.org/data/2.5/weather?q=";
    @Override
    protected Fragment createFragment() {
        return WeatherFragment.newInstance();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}
