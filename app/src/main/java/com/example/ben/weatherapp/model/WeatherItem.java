package com.example.ben.weatherapp.model;

import android.graphics.Bitmap;
import android.net.Uri;

public class WeatherItem {

    private String mCityId;
    private String mCityName;
    private int mTemp;
    private String mLogo;
    private Bitmap mBitmap;

    public Bitmap getBitmap() {
        return mBitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        mBitmap = bitmap;
    }

    private double mWind;
    private int mVisibility;

    public int getVisibility() {
        return mVisibility;
    }

    public void setVisibility(int visibility) {
        mVisibility = visibility;
    }

    public String getCityId() {
        return mCityId;
    }

    public void setCityId(String cityId) {
        mCityId = cityId;
    }
    public String getCityName() {
        return mCityName;
    }

    public void setCityName(String cityName) {
        mCityName = cityName;
    }

    public int getTemp() {
        return mTemp;
    }

    public void setTemp(int temp) {
        mTemp = temp;
    }

    public String getLogo() {
        return mLogo;
    }
    public void setLogo(String logo) {
        mLogo=logo;
    }



    public void setLogoUri(String logo) {
        Uri mLogoUri = Uri.parse(logo);
    }

    public double getWind() {
        return mWind;
    }

    public void setWind(double wind) {
        mWind = wind;
    }

    @Override
    public String toString() {
        return "WeatherItem{" +
                "mCityId='" + mCityId + '\'' +
                ", mCityName='" + mCityName + '\'' +
                ", mTemp='" + mTemp + '\'' +
                ", mLogo='" + mLogo + '\'' +
                ", mWind='" + mWind + '\'' +
                '}';
    }
}
