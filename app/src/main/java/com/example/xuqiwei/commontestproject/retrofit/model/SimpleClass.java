package com.example.xuqiwei.commontestproject.retrofit.model;

import com.example.xuqiwei.commontestproject.retrofit.SimpleService;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;


import rx.Observable;

/**
 * Created by xuqiwei on 16-6-19.
 */
public class SimpleClass {
    private String login;
    private  int contributions;

    public SimpleClass(String login, int contributions) {
        this.login = login;
        this.contributions = contributions;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getContributions() {
        return contributions;
    }

    public void setContributions(int contributions) {
        this.contributions = contributions;
    }
//    public interface SimpleService {
//        @GET("/repos/{owner}/{repo}/contributors")
//        Observable<JSONArray> contributors(
//                @Path("owner") String owner,
//                @Path("repo") String repo);
//    }
}
