package com.example.xuqiwei.commontestproject.retrofit.model;

import com.example.xuqiwei.commontestproject.retrofit.model.WeatherData;
import com.example.xuqiwei.commontestproject.retrofit.model.WeatherIndexModel;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by xuqiwei on 16-6-19.
 */
public class WeatherModel {
    private String currentCity;
    private String pm25;
    private List<WeatherIndexModel> index;
    private List<WeatherData>weather_data;

    public WeatherModel(String currentCity, String pm25, List<WeatherIndexModel> index, List<WeatherData> weather_data) {
        this.currentCity = currentCity;
        this.pm25 = pm25;
        this.index = index;
        this.weather_data = weather_data;
    }

    public String getCurrentCity() {
        return currentCity;
    }

    public void setCurrentCity(String currentCity) {
        this.currentCity = currentCity;
    }

    public String getPm25() {
        return pm25;
    }

    public void setPm25(String pm25) {
        this.pm25 = pm25;
    }

    public List<WeatherData> getWeather_data() {
        return weather_data;
    }

    public void setWeather_data(List<WeatherData> weather_data) {
        this.weather_data = weather_data;
    }

    public List<WeatherIndexModel> getIndex() {
        return index;
    }

    public void setIndex(List<WeatherIndexModel> index) {
        this.index = index;
    }

}
