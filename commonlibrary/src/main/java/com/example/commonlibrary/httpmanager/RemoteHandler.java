package com.example.commonlibrary.httpmanager;

import android.content.Context;


import com.example.commonlibrary.commoninterface.NetWorkListenerV2;


import org.json.JSONException;
import org.json.JSONObject;



/**
 * Created by xuqiwei-Office on 2016/4/22.
 * 对httprequest重新封装，
 */
public class RemoteHandler {
    /**
     * 需要上传的json字符串
     */
    private JSONObject paramJSONObject;
    /*
    url的方法
     */
    private String urlMethod;
    /*
    上下文
     */
    private Context context;
    /*
    是否需要loading
     */
    private boolean isLoading = true;
    private String userName;
    private String passWord;

    /*
        监听网络返回的结果
         */
    private NetWorkListenerV2 netWorkListenerV2;
    public NetWorkListenerV2 getNetWorkListenerV2() {
        return netWorkListenerV2;
    }

    public RemoteHandler setNetWorkListenerV2(NetWorkListenerV2 netWorkListenerV2) {
        this.netWorkListenerV2 = netWorkListenerV2;
        return this;
    }





    public RemoteHandler(JSONObject paramJSONObject, String urlMethod, Context context, String userName, String passWord) {
        try {
            paramJSONObject.put("AppId", "PreHospitalCare");
        } catch (JSONException e) {
            e.printStackTrace();
        }
//        paramJSONObject.put()
        this.paramJSONObject = paramJSONObject;


        this.urlMethod = urlMethod;
        this.context = context;

        this.userName = userName;
        this.passWord = passWord;
    }

    public RemoteHandler(JSONObject paramJSONObject, String urlMethod, Context context) {
        try {
            paramJSONObject.put("AppId", "PreHospitalCare");
        } catch (JSONException e) {
            e.printStackTrace();
        }
//        paramJSONObject.put()
        this.paramJSONObject = paramJSONObject;


        this.urlMethod = urlMethod;
        this.context = context;
    }
    public RemoteHandler(JSONObject paramJSONObject, String urlMethod, Context context,boolean isLoading) {
        try {
            paramJSONObject.put("AppId", "PreHospitalCare");
        } catch (JSONException e) {
            e.printStackTrace();
        }
//        paramJSONObject.put()
        this.paramJSONObject = paramJSONObject;


        this.urlMethod = urlMethod;
        this.context = context;
        this.isLoading = isLoading;
    }

    public void setLoading(boolean loading) {
        isLoading = loading;
    }

    /**
     * 执行post方法
     */
    public void postExecute()
    {

        HttpRequsetModel httpRequsetModel;
//        HttpRequsetModel httpRequsetModel = new HttpRequsetModel(paramJSONObject, MethodModel.spellURLWithOutDomain(context, urlMethod),null,context,false);
        HttpRequest httpRequest;
        if (userName!=null&&passWord!=null)
        {
            httpRequsetModel = new HttpRequsetModel(paramJSONObject, urlMethod,context,false);
            httpRequest = new HttpRequest(httpRequsetModel, userName,  passWord);
        }else
        {
            httpRequsetModel = new HttpRequsetModel(paramJSONObject, MethodModel.spellURLWithOutDomain(context, urlMethod),context,false);
            httpRequest = new HttpRequest(httpRequsetModel);

        }
//        HttpRequest


        httpRequest.sendRequest(new HttpRequest.GetResponseListener() {
            @Override
            public void onSuccess(int statusCode , JSONObject responseString) {
                netWorkListenerV2.onSuccess(statusCode, responseString);
            }

            @Override
            public void onFailure(int statusCode , JSONObject responseString) {
                netWorkListenerV2.onFailure(statusCode, responseString);
            }

            @Override
            public void netOn() {
                netWorkListenerV2.onNetON();
            }

            @Override
            public void netOff() {
                netWorkListenerV2.onNetOFF();
            }
        },isLoading);
    }
    /**
     * 执行get方法
     */
    public void getExecute()
    {

        HttpRequsetModel httpRequsetModel;
//        HttpRequsetModel httpRequsetModel = new HttpRequsetModel(paramJSONObject, MethodModel.spellURLWithOutDomain(context, urlMethod),null,context,false);
        HttpRequest httpRequest;
        if (userName!=null&&passWord!=null)
        {
            httpRequsetModel = new HttpRequsetModel(paramJSONObject, urlMethod,context,false);
            httpRequest = new HttpRequest(httpRequsetModel, userName,  passWord);
        }else
        {
            httpRequsetModel = new HttpRequsetModel(paramJSONObject,  MethodModel.spellURLWithOutDomain(context, urlMethod),context,true);
            httpRequest = new HttpRequest(httpRequsetModel);

        }

        httpRequest.sendRequest(new HttpRequest.GetResponseListener() {
            @Override
            public void onSuccess(int statusCode, JSONObject responseString) {
                netWorkListenerV2.onSuccess(statusCode, responseString);
            }

            @Override
            public void onFailure(int statusCode, JSONObject responseString) {
                netWorkListenerV2.onFailure(statusCode, responseString);
            }

            @Override
            public void netOn() {
                netWorkListenerV2.onNetON();
            }

            @Override
            public void netOff() {
                netWorkListenerV2.onNetOFF();
            }
        },isLoading);

    }

    public JSONObject getParamJSONObject() {
        return paramJSONObject;
    }

    public void setParamJSONObject(JSONObject paramJSONObject) {
        this.paramJSONObject = paramJSONObject;
    }
}
