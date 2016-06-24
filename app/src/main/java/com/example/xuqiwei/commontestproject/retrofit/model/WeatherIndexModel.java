package com.example.xuqiwei.commontestproject.retrofit.model;

/**
 * Created by xuqiwei on 16-6-19.
 */
public class WeatherIndexModel {
    private String title;
    private String zs;
    private String tipt;
    private String des;

    public WeatherIndexModel(String title, String zs, String tipt, String des) {
        this.title = title;
        this.zs = zs;
        this.tipt = tipt;
        this.des = des;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getTipt() {
        return tipt;
    }

    public void setTipt(String tipt) {
        this.tipt = tipt;
    }

    public String getZs() {
        return zs;
    }

    public void setZs(String zs) {
        this.zs = zs;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
