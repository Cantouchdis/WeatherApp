package com.example.ben.weatherapp;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ben.weatherapp.model.WeatherItem;

public class CityFragment extends Fragment {

    private static final String TAG="CityFragment";
    private WeatherItem mWeatherItem;
    private TextView mCityText;
    private ImageView mImageView;
    private TextView mFirstInfo;
    private TextView mSecondInfo;
    private TextView mWeatherTemp;

    public static CityFragment newInstance(){
        return new CityFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle=getActivity().getIntent().getExtras();
        mWeatherItem=new WeatherItem();
        if (bundle != null){

            mWeatherItem.setCityName(bundle.getString("cityName"));

            if(mWeatherItem.getCityName().equals("PRISHTINA")){
                mWeatherItem.setCityId("786714");
            } else if (mWeatherItem.getCityName().equals("LONDON")){
                mWeatherItem.setCityId("2643743");
            } else {
                mWeatherItem.setCityId("6454095");
            }
        }

        new FetchWeather().execute();


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.activity_single_weather,container,false);

        mCityText=v.findViewById(R.id.this_city);

        mWeatherTemp=v.findViewById(R.id.weather_temp);
        mFirstInfo=v.findViewById(R.id.first_info);
        mImageView=v.findViewById(R.id.weather_image);
        mSecondInfo=v.findViewById(R.id.second_info);

        mCityText.setText(mWeatherItem.getCityName());

        return v;
    }

    private class FetchWeather extends AsyncTask<Void,Void,Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            WeatherFetcher itemInfo=new WeatherFetcher();
            itemInfo.fetchWeather(mWeatherItem.getCityId());
            mWeatherItem=itemInfo.getWeatherItem();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

            mWeatherTemp.setText(""+mWeatherItem.getTemp()+"°C");
            mFirstInfo.setText(""+mWeatherItem.getWind());
            mSecondInfo.setText(""+mWeatherItem.getVisibility());
            mImageView.setImageBitmap(mWeatherItem.getBitmap());

        }
    }


}
