package com.example.ben.weatherapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ben.weatherapp.model.WeatherItem;

import java.io.IOException;

public class WeatherFragment extends Fragment {

    private static final String TAG="WeatherFragment";
    private TextView mFirstCity;
    private TextView mSecondCity;
    private TextView mThirdCity;

    private WeatherItem mFirstItem;
    private WeatherItem mSecondItem;
    private WeatherItem mThirdItem;

    public static WeatherFragment newInstance(){
        return new WeatherFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.activity_main_weather,container,false);

        mFirstCity=view.findViewById(R.id.first_info);
        mSecondCity=view.findViewById(R.id.second_info);
        mThirdCity=view.findViewById(R.id.third_city);

        mFirstCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFirstItem=new WeatherItem();
                mFirstItem.setCityName(mFirstCity.getText().toString());

                Intent i=new Intent(getActivity(),CityActivity.class);
                i.putExtra("cityName",mFirstItem.getCityName());
                startActivity(i);
            }
        });

        mSecondCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSecondItem=new WeatherItem();
                mSecondItem.setCityName(mSecondCity.getText().toString());

                Intent i=new Intent(getActivity(),CityActivity.class);
                i.putExtra("cityName",mSecondItem.getCityName());
                startActivity(i);
            }
        });

        mThirdCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mThirdItem=new WeatherItem();
                mThirdItem.setCityName(mThirdCity.getText().toString());

                Intent i=new Intent(getActivity(),CityActivity.class);
                i.putExtra("cityName",mThirdItem.getCityName());
                startActivity(i);
            }
        });

        return view;
    }
}
