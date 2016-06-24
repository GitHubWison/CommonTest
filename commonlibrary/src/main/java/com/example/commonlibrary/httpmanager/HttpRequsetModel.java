package com.example.commonlibrary.httpmanager;

import android.content.Context;


import org.json.JSONObject;


/**
 * 网络请求需要用到的类
 */
public class HttpRequsetModel {
    /**
     * 头部信息
     */
    private JSONObject jsonObject;
    private boolean needHeader = true;
    /**
     * url
     */
    private String url;
    /**
     * 上下文
     */
    private Context context;
    /**
     * 是否为get请求
     */
    private  boolean isGet;

    public HttpRequsetModel(JSONObject jsonObject, String url, Context context, boolean isGet) {
//        this.headers = headers;
//        this.stringEntity = Tool.getEntityWithJsonObject(jsonObject);
        this.jsonObject = jsonObject;
        this.url = url;

        this.context = context;
        this.isGet = isGet;
    }

    public boolean isNeedHeader() {
        return needHeader;
    }

    public void setNeedHeader(boolean needHeader) {
        this.needHeader = needHeader;
    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }

    public void setJsonObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }



    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public boolean isGet() {
        return isGet;
    }

    public void setIsGet(boolean isGet) {
        this.isGet = isGet;
    }
}
