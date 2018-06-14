package com.example.ben.weatherapp;

import android.support.v4.app.Fragment;

public class CityActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return CityFragment.newInstance();
    }
}
