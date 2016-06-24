package com.example.commonlibrary.commoninterface;

import org.json.JSONObject;


/**
 * Created by Nangy-Office on 2016/4/22.
 */
public abstract class NetWorkListenerV2 {
    public abstract void onSuccess(int statusCode, JSONObject responseString);
    public abstract void onFailure(int statusCode, JSONObject responseString);
    public void onNetON(){

    }
    public void onNetOFF(){

    }
}
