package com.example.ben.weatherapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;

import com.example.ben.weatherapp.model.WeatherItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class WeatherFetcher {

    private static final String TAG="WeatherFetcher";
    private static final String API_KEY="943d5d41bbcc6b434eaaef9c9baf94e7";
    public WeatherItem mWeatherItem;


    public byte[] getUrlBytes(String urlSpec)throws IOException{
        URL url=new URL(urlSpec);
        HttpURLConnection connection=(HttpURLConnection)url.openConnection();
        try{
            ByteArrayOutputStream out=new ByteArrayOutputStream();
            InputStream in=connection.getInputStream();
            if(connection.getResponseCode() != connection.HTTP_OK){
                throw new IOException(connection.getResponseMessage()+": with "+urlSpec);
            }

            int bytesRead=0;
            byte[] buffer=new byte[1024];
            while((bytesRead=in.read(buffer))>0){
                out.write(buffer,0,bytesRead);
            }
            out.close();
            return out.toByteArray();
        } finally {
            connection.disconnect();
        }
    }

    public String getUrlString(String urlSpec) throws IOException{
        return new String(getUrlBytes(urlSpec));
    }

    public void fetchWeather(String id){


        try{
            String url= Uri.parse("https://api.openweathermap.org/data/2.5/weather?")
                    .buildUpon()
                    .appendQueryParameter("id", id)
                    .appendQueryParameter("appid",API_KEY).build().toString();
            String jsonString=getUrlString(url);
            Log.i(TAG,"Received JSON: "+jsonString);
            JSONObject jsonBody=new JSONObject(jsonString);
            showForecast(mWeatherItem,jsonBody);
        } catch (IOException ioe){
            Log.e(TAG,"Failed to receive JSON: ",ioe);
        }catch (JSONException je){
            Log.e(TAG,"Failed to parse JSON: ",je);
        }
    }

    public void showForecast(WeatherItem item,JSONObject jsonBody) throws IOException,JSONException{
        mWeatherItem=new WeatherItem();
        JSONArray oneDayWeather=jsonBody.getJSONArray("weather");
        String weatherLogo=oneDayWeather.getJSONObject(0).getString("icon");
        mWeatherItem.setLogo("http://openweathermap.org/img/w/"+weatherLogo+".png");
        mWeatherItem.setLogoUri(mWeatherItem.getLogo());

        URL url=new URL(mWeatherItem.getLogo());
        mWeatherItem.setBitmap(BitmapFactory.decodeStream(url.openConnection().getInputStream()));
        JSONObject oneDayMain=jsonBody.getJSONObject("main");
        double oneDayMainTemp=oneDayMain.getDouble("temp");
        mWeatherItem.setTemp((int)(oneDayMainTemp-273.15));
        int oneDayVisibility=jsonBody.getInt("visibility");

        mWeatherItem.setVisibility(oneDayVisibility);
        JSONObject oneDayWind=jsonBody.getJSONObject("wind");
        double oneDayWindSpeed=oneDayWind.getDouble("speed");
        mWeatherItem.setWind(oneDayWindSpeed);

    }

    public List<WeatherItem> getWeeklyItems(String id){

        List<WeatherItem> items=new ArrayList<>();
        try{
            String url= Uri.parse("https://openweathermap.org/data/2.5/forecast/daily?")
                    .buildUpon()
                    .appendQueryParameter("id", id)
                    .appendQueryParameter("cnt", "7")
                    .appendQueryParameter("appid",API_KEY).build().toString();
            String jsonString=getUrlString(url);
            Log.i(TAG,"Received JSON: "+jsonString);
            JSONObject jsonBody=new JSONObject(jsonString);

            // get all items - 7 days

            JSONArray listArray=jsonBody.getJSONArray("list");
            for (int i=0; i<listArray.length();i++){
                String logo=listArray.getJSONObject(4).getString("icon");
                mWeatherItem.setLogo("http://openweathermap.org/img/w/"+logo+".png");
                mWeatherItem.setLogoUri(mWeatherItem.getLogo());
                URL url1=new URL(mWeatherItem.getLogo());
                mWeatherItem.setBitmap(BitmapFactory.decodeStream(url1.openConnection()
                        .getInputStream()));

                double temp=listArray.getJSONObject(1).getDouble("day");
                mWeatherItem.setTemp((int)(temp-273.15));
                items.add(mWeatherItem);

            }


        } catch (IOException ioe){
            Log.e(TAG,"Failed to receive JSON: ",ioe);
        }catch (JSONException je){
            Log.e(TAG,"Failed to parse JSON: ",je);
        }

        return items;
    }

    public WeatherItem getWeatherItem() {
        return mWeatherItem;
    }


}
