package com.example.commonlibrary.httpmanager;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.IntentFilter;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;
import com.example.commonlibrary.R;
import com.example.commonlibrary.staticstring.CacheName;
import com.example.commonlibrary.staticstring.CommonNetMsg;
import com.example.commonlibrary.staticstring.MySingleton;
import com.example.commonlibrary.tools.SPreference;
import com.example.commonlibrary.tools.Tool;
import com.example.commonlibrary.tools.Utils;
import com.example.commonlibrary.views.LoadingDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by xuqiwei-Office on 2016/3/24.
 * http请求
 */
public class HttpRequest {
    private HttpRequsetModel httpRequsetModel;
    private GetResponseListener getResponseListener;
    private String userName;
    private String passWord;
    /*
    以后需要根据情况显示ProgressDialog，先不做处理
     */
    private ProgressDialog progressDialog;
    public HttpRequest(HttpRequsetModel httpRequsetModel) {
        this.httpRequsetModel = httpRequsetModel;
    }
    public HttpRequest(HttpRequsetModel httpRequsetModel, String userName,String passWord)
    {
        this.httpRequsetModel = httpRequsetModel;
        this.userName = userName;
        this.passWord = passWord;
        Tool.printDebugLog(httpRequsetModel.getContext(),"urlllmyecg",httpRequsetModel.getUrl());

    }

    public void setHttpRequsetModel(HttpRequsetModel httpRequsetModel) {
        this.httpRequsetModel = httpRequsetModel;
    }

    public void setGetResponseListener(GetResponseListener getResponseListener) {
        this.getResponseListener = getResponseListener;
    }

    //新建volley请求
    public void sendRequest(final GetResponseListener listener)
    {
//        获取userid   UserId
        String userid = null;
        try {
            JSONObject userInfoJson = Tool.getJsonCache(httpRequsetModel.getContext(), CacheName.userinfo_json);
//                    Tool.getSPInJsonObject(httpRequsetModel.getContext(), SPreference.Login.sp_name,SPreference.Login.userinfo);
            if (userInfoJson==null||userInfoJson.toString().equals(""))
            {
                userid = "";
            }
            else
            {
                userid = userInfoJson.getString("UserId");
            }
//            userid = "";



            if (!userid.equals(""))
            {
                httpRequsetModel.getJsonObject().put("CREATE_BY",userid);
                httpRequsetModel.getJsonObject().put("UPDATE_BY",userid);
            }

            httpRequsetModel.getJsonObject().put("AppId", "PreHospitalCare");
        } catch (JSONException e) {
            e.printStackTrace();
        }
//        header
        final HashMap<String, String> requestHeader = new HashMap<String, String>();

//
//        1.开始显示loading页面
        final LoadingDialog loadingDialog = new LoadingDialog(httpRequsetModel.getContext());
        loadingDialog.show();
//        2.开始网络请求
        Tool.checkNetWorkStatues(httpRequsetModel.getContext(),new Tool.CheckNetWorkListener(){

            @Override
            public void netOn() {
                //设置基础认证
                if (userName != null && passWord != null) {
                    Tool.printDebugLog(httpRequsetModel.getContext(),"setBasicAuth","setBasicAuthpassWord");
//                    client.setBasicAuth(userName, passWord);
                    requestHeader.put("Authorization",getBasicAuthString(userName,passWord));
                } else
                {
                    Tool.printDebugLog(httpRequsetModel.getContext(),"setBasicAuth","setBasicAuthnotpassword");
//                    client.setBasicAuth("Mdsd.Phep.Api", "mdsd.phep.api.2005$");
                    requestHeader.put("Authorization",getBasicAuthString("Mdsd.Phep.Api","mdsd.phep.api.2005$"));
                }
                //设置超时

//                设置超时结束
                JSONObject userInfoJson = Tool.getJsonCache(httpRequsetModel.getContext(), CacheName.userinfo_json);
//                        Tool.getSPInJsonObject(httpRequsetModel.getContext(), SPreference.Login.sp_name, SPreference.Login.userinfo);
                if (userInfoJson!=null&&!userInfoJson.equals("null")&&!userInfoJson.toString().equals("")&&httpRequsetModel.isNeedHeader())
                {
                    String username = "";
                    String loginname = "";
                    try {
                        username = userInfoJson.getString("UserName");
                        loginname = userInfoJson.getString("LoginName");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    //添加header开始
                    requestHeader.put("UserName",Tool.base64Encode(username).replace("\n",""));
                    requestHeader.put("LoginName",Tool.base64Encode(loginname).replace("\n",""));
                    requestHeader.put("RequestSource",Tool.base64Encode("VehicleMobileApp").replace("\n",""));
                    //添加header结束
                }
                    Tool.printDebugLog(httpRequsetModel.getContext(),"url连接",httpRequsetModel.getUrl()+httpRequsetModel.getJsonObject());

//                    开始网络请求,没有取消响应
//                    RequestQueue requestQueue = Volley.newRequestQueue(httpRequsetModel.getContext());
                RequestQueue requestQueue = MySingleton.getInstance(httpRequsetModel.getContext().getApplicationContext()).getRequestQueue();
//                    requestQueue.get
                    JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(httpRequsetModel.isGet() ? Request.Method.GET : Request.Method.POST, httpRequsetModel.getUrl(),
                            httpRequsetModel.getJsonObject(), new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Tool.printDebugLog(httpRequsetModel.getContext(),"响应成功url==", httpRequsetModel.getUrl());
                            Tool.printDebugLog(httpRequsetModel.getContext(),"响应成功json==", response.toString());
                            Tool.printJsonLog(httpRequsetModel.getContext(),response.toString());
                            loadingDialog.dismiss();
                            try {
                                if (response.getString(CommonNetMsg.resultCodeSignal).equals(CommonNetMsg.resultCodeSuccess))
                                {
    //                                            用户操作正确
                                    //abcLog.d("用户操作正确", "用户操作正确");


                                }else
                                {
    //                                            用户输入的信息无效
                                    Toast.makeText(httpRequsetModel.getContext(), response.getString("message"), Toast.LENGTH_SHORT).show();
                                }
                                listener.onSuccess(1000, response);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            loadingDialog.dismiss();
                            listener.onFailure(2000, null);
                            try {
                                Utils.saveToSDCard("medicalwrong.txt",error.getMessage());
                            } catch (Exception e) {
                                Toast.makeText(httpRequsetModel.getContext(), "*****", Toast.LENGTH_SHORT).show();
                                e.printStackTrace();
                            }
                            Toast.makeText(httpRequsetModel.getContext(), httpRequsetModel.getContext().getResources().getString(R.string.normal_wrong) , Toast.LENGTH_SHORT).show();
                        }
                    }){
                        @Override
                        public Map<String, String> getHeaders() throws AuthFailureError {
                            return requestHeader;
                        }
                    };
                    requestQueue.add(jsonRequest);
//                requestQueue

                listener.netOn();
            }

            @Override
            public void netOff() {
                loadingDialog.dismiss();
                listener.netOff();
            }
        }, Tool.getStringCache(httpRequsetModel.getContext(),CacheName.domain_String));
//        Tool.getSp(httpRequsetModel.getContext(), SPreference.Login.sp_name, SPreference.Login.domain)




    }
    public void sendRequest(final GetResponseListener listener, final boolean isLoading)
    {
//        获取userid   UserId
        String userid = null;
        try {
            JSONObject userInfoJson = Tool.getJsonCache(httpRequsetModel.getContext(), CacheName.userinfo_json);
//                    Tool.getSPInJsonObject(httpRequsetModel.getContext(), SPreference.Login.sp_name,SPreference.Login.userinfo);
            if (userInfoJson==null||userInfoJson.toString().equals(""))
            {
                userid = "";
            }
            else
            {
                userid = userInfoJson.getString("UserId");
            }
//            userid = "";



            if (!userid.equals(""))
            {
                httpRequsetModel.getJsonObject().put("CREATE_BY",userid);
                httpRequsetModel.getJsonObject().put("UPDATE_BY",userid);
            }

            httpRequsetModel.getJsonObject().put("AppId", "PreHospitalCare");
        } catch (JSONException e) {
            e.printStackTrace();
        }
//        header
        final HashMap<String, String> requestHeader = new HashMap<String, String>();

//
//        1.开始显示loading页面
         LoadingDialog loadingDialog = null ;
        if (isLoading)
        {
            loadingDialog = new LoadingDialog(httpRequsetModel.getContext());
        }
        if (isLoading)
        {
            loadingDialog.show();
        }
//        2.开始网络请求
        final LoadingDialog finalLoadingDialog = loadingDialog;
        final LoadingDialog finalLoadingDialog1 = loadingDialog;
        final LoadingDialog finalLoadingDialog2 = loadingDialog;
        Tool.checkNetWorkStatues(httpRequsetModel.getContext(),new Tool.CheckNetWorkListener(){

            @Override
            public void netOn() {
                //设置基础认证
                if (userName != null && passWord != null) {
                    Tool.printDebugLog(httpRequsetModel.getContext(),"setBasicAuth","setBasicAuthpassWord");
//                    client.setBasicAuth(userName, passWord);
                    requestHeader.put("Authorization",getBasicAuthString(userName,passWord));
                } else
                {
                    Tool.printDebugLog(httpRequsetModel.getContext(),"setBasicAuth","setBasicAuthnotpassword");
//                    client.setBasicAuth("Mdsd.Phep.Api", "mdsd.phep.api.2005$");
                    requestHeader.put("Authorization",getBasicAuthString("Mdsd.Phep.Api","mdsd.phep.api.2005$"));
                }
                //设置超时

//                设置超时结束
                JSONObject userInfoJson = Tool.getJsonCache(httpRequsetModel.getContext(), CacheName.userinfo_json);
//                        Tool.getSPInJsonObject(httpRequsetModel.getContext(), SPreference.Login.sp_name, SPreference.Login.userinfo);
                if (userInfoJson!=null&&!userInfoJson.equals("null")&&!userInfoJson.toString().equals("")&&httpRequsetModel.isNeedHeader())
                {
                    String username = "";
                    String loginname = "";
                    try {
                        username = userInfoJson.getString("UserName");
                        loginname = userInfoJson.getString("LoginName");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    //添加header开始
                    requestHeader.put("UserName",Tool.base64Encode(username).replace("\n",""));
                    requestHeader.put("LoginName",Tool.base64Encode(loginname).replace("\n",""));
                    requestHeader.put("RequestSource",Tool.base64Encode("VehicleMobileApp").replace("\n",""));
                    //添加header结束
                }
                    Tool.printDebugLog(httpRequsetModel.getContext(),"url连接",httpRequsetModel.getUrl()+httpRequsetModel.getJsonObject());

//                    开始网络请求,没有取消响应
//                    RequestQueue requestQueue = Volley.newRequestQueue(httpRequsetModel.getContext());
                RequestQueue requestQueue = MySingleton.getInstance(httpRequsetModel.getContext().getApplicationContext()).getRequestQueue();
//                    requestQueue.get
                    JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(httpRequsetModel.isGet() ? Request.Method.GET : Request.Method.POST, httpRequsetModel.getUrl(),
                            httpRequsetModel.getJsonObject(), new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
//                            Tool.printDebugLog(httpRequsetModel.getContext(),"响应成功"+httpRequsetModel.getUrl()+"==", response.toString());
                            Tool.printDebugLog(httpRequsetModel.getContext(),"响应成功url==", httpRequsetModel.getUrl());
                            Tool.printDebugLog(httpRequsetModel.getContext(),"响应成功json==", response.toString());
                            Tool.printJsonLog(httpRequsetModel.getContext(),response.toString());
                            if (isLoading) {
                                finalLoadingDialog1.dismiss();
                            }
                            try {
                                if (response.getString(CommonNetMsg.resultCodeSignal).equals(CommonNetMsg.resultCodeSuccess))
                                {
                                    //                                            用户操作正确
                                    Tool.printDebugLog(httpRequsetModel.getContext(),"httprequest","用户操作正确");
                                }else
                                {
                                    //                                            用户输入的信息无效
                                    Tool.printDebugLog(httpRequsetModel.getContext(),"httprequest","用户输入的信息无效");
                                    Toast.makeText(httpRequsetModel.getContext(), response.getString("message"), Toast.LENGTH_SHORT).show();
                                }
                                listener.onSuccess(1000, response);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            if (isLoading) {
                                finalLoadingDialog.dismiss();
                            }
                            listener.onFailure(2000, null);
                            try {
                                Utils.saveToSDCard("medicalwrong.txt",error.getMessage());
                            } catch (Exception e) {
                                Toast.makeText(httpRequsetModel.getContext(), "*****", Toast.LENGTH_SHORT).show();
                                e.printStackTrace();
                            }
                            Toast.makeText(httpRequsetModel.getContext(), httpRequsetModel.getContext().getResources().getString(R.string.normal_wrong) , Toast.LENGTH_SHORT).show();
                        }
                    }){
                        @Override
                        public Map<String, String> getHeaders() throws AuthFailureError {
                            return requestHeader;
                        }
                    };
                    requestQueue.add(jsonRequest);
//                requestQueue.start();

                listener.netOn();
            }

            @Override
            public void netOff() {
                if (isLoading) {
                    finalLoadingDialog2.dismiss();
                }

                listener.netOff();
            }
        },Tool.getStringCache(httpRequsetModel.getContext(),CacheName.domain_String));




    }
    //设置头部信息
    public String getBasicAuthString(String authName,String authPassWord)
    {
//        HashMap<String, String> header = new HashMap<String, String>();
//        String basicString = "Basic "+Tool.base64EncodeV2("Mdsd.Phep.Api:mdsd.phep.api.2005$");
        String basicString = "Basic "+Tool.base64EncodeV2(authName+":"+authPassWord);
//        header.put("Authorization", basicString);
        return basicString;
    }
/*
    public void sendRequestV0(final GetResponseListener listener)
    {
//        获取userid   UserId
        String userid = null;
        try {
            JSONObject userInfoJson = Tool.getSPInJsonObject(httpRequsetModel.getContext(), SPreference.Login.sp_name,SPreference.Login.userinfo);
            if (userInfoJson==null||userInfoJson.toString().equals(""))
            {
                userid = "";
            }
            else
            {
                userid = userInfoJson.getString("UserId");
            }
//            userid = "";



            if (!userid.equals(""))
            {
                httpRequsetModel.getJsonObject().put("CREATE_BY",userid);
                httpRequsetModel.getJsonObject().put("UPDATE_BY",userid);
            }

            httpRequsetModel.getJsonObject().put("AppId", "PreHospitalCare");
        } catch (JSONException e) {
            e.printStackTrace();
        }

//        检查是否联网
//        if (Tool.isNetWorkActive(httpRequsetModel.getContext()))
//        {
        final AsyncHttpClient client = new AsyncHttpClient();
        //abcLog.d("开始loading动画","开始loading动画");
//        开始loading动画
//        LoadingDialog.getInstance(httpRequsetModel.getContext(), client).show();
        final LoadingDialog loadingDialog = new LoadingDialog(httpRequsetModel.getContext(),client);
        loadingDialog.show();
            //        检查网路是否通畅
            Tool.checkNetWorkStatues(httpRequsetModel.getContext(), new Tool.CheckNetWorkListener() {
                @Override
                public void netOn() {
                    if (userName != null && passWord != null) {
                        Tool.printDebugLog(httpRequsetModel.getContext(),"setBasicAuth","setBasicAuthpassWord");
                        client.setBasicAuth(userName, passWord);
                    } else
                    {
                        Tool.printDebugLog(httpRequsetModel.getContext(),"setBasicAuth","setBasicAuthnotpassword");
                        client.setBasicAuth("Mdsd.Phep.Api", "mdsd.phep.api.2005$");
                    }


//                    client.setTimeout(8000);
                    client.setMaxRetriesAndTimeout(1,8000);
                    //设置超时
                    client.setResponseTimeout(8000);

                    //添加header开始
                    JSONObject userInfoJson = Tool.getSPInJsonObject(httpRequsetModel.getContext(), SPreference.Login.sp_name, SPreference.Login.userinfo);



                    if (userInfoJson!=null&&!userInfoJson.equals("null")&&!userInfoJson.toString().equals("")&&httpRequsetModel.isNeedHeader())
                    {
                        String username = "";
                        String loginname = "";
                        try {
                            username = userInfoJson.getString("UserName");
                            loginname = userInfoJson.getString("LoginName");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        client.addHeader("UserName",Tool.base64Encode(username).replace("\n",""));
                        client.addHeader("LoginName",Tool.base64Encode(loginname).replace("\n",""));
                        client.addHeader("RequestSource",Tool.base64Encode("VehicleMobileApp").replace("\n",""));

                    }


                    //添加header结束
                    Log.d("url连接",httpRequsetModel.getUrl()+httpRequsetModel.getJsonObject());
                    if (httpRequsetModel.isGet()) {
//            是get请求
                        JSONObject jsObject = httpRequsetModel.getJsonObject();
                        StringEntity ss = new StringEntity(jsObject.toString(),"utf-8");
                        if (!client.getThreadPool().isShutdown())
                        {
                            client.get(httpRequsetModel.getContext(), httpRequsetModel.getUrl(), ss, "application/json", new JsonHttpResponseHandler() {
                                @Override
                                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                                    super.onSuccess(statusCode, headers, response);
                                    Log.d("响应成功"+httpRequsetModel.getUrl()+"==", response.toString());
//                                    LoadingDialog.getInstance(httpRequsetModel.getContext(), client).dismiss();
                                    loadingDialog.dismiss();
//                                    判断响应结果
                                    try {
                                        if (response.getString("resultCode").equals("1000"))
                                        {
//                                            用户操作正确
                                            //abcLog.d("用户操作正确", "用户操作正确");


                                        }else
                                        {
//                                            用户输入的信息无效
                                            Toast.makeText(httpRequsetModel.getContext(), response.getString("message"), Toast.LENGTH_SHORT).show();
                                        }
                                        listener.onSuccess(statusCode, headers, response);


                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                }


                                @Override
                                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                                    super.onFailure(statusCode, headers, throwable, errorResponse);
                                    //abcLog.d("failure", "网络连接出错");
//                                    LoadingDialog.getInstance(httpRequsetModel.getContext(), client).dismiss();
                                    loadingDialog.dismiss();
                                    listener.onFailure(statusCode, headers, errorResponse);
                                    Toast.makeText(httpRequsetModel.getContext(), httpRequsetModel.getContext().getResources().getString(R.string.normal_wrong) , Toast.LENGTH_SHORT).show();

                                }

                            });
                        }else
                        {
                            //abcLog.d("线程池关闭了","线程池关闭了");
                            Toast.makeText(httpRequsetModel.getContext(), "系统繁忙，请稍后再试", Toast.LENGTH_SHORT).show();
                        }

                    } else {
//            是post请求
                        JSONObject jsObject = httpRequsetModel.getJsonObject();

//                            String utf8encode = URLEncoder.encode(jsObject.toString(), "utf-8");
                        StringEntity ss = new StringEntity(jsObject.toString(),"utf-8");
                        ss.setContentEncoding("UTF-8");
                        if (!client.getThreadPool().isShutdown())
                        {
                            client.post(httpRequsetModel.getContext(), httpRequsetModel.getUrl(), ss, "application/json", new JsonHttpResponseHandler() {
                                @Override
                                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                                    super.onSuccess(statusCode, headers, response);
                                    Log.d("响应成功", response.toString());
//                                    LoadingDialog.getInstance(httpRequsetModel.getContext(), client).dismiss();
                                    loadingDialog.dismiss();
//                                    判断响应结果
                                    try {
                                        if (response.getString("resultCode").equals("1000"))
                                        {
//                                            用户操作正确
//                                            listener.onSuccess(statusCode, headers, response);
                                        }else
                                        {
//                                            用户输入的信息无效
                                            Toast.makeText(httpRequsetModel.getContext(), response.getString("message"), Toast.LENGTH_SHORT).show();
                                        }
                                        listener.onSuccess(statusCode, headers, response);

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                }


                                @Override
                                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                                    super.onFailure(statusCode, headers, throwable, errorResponse);
                                    //abcLog.d("failure", "网络连接出错");
//                                    LoadingDialog.getInstance(httpRequsetModel.getContext(), client).dismiss();
                                    loadingDialog.dismiss();
                                    listener.onFailure(statusCode, headers, errorResponse);
                                    Toast.makeText(httpRequsetModel.getContext(), httpRequsetModel.getContext().getResources().getString(R.string.normal_wrong) , Toast.LENGTH_SHORT).show();

                                }

                            });
                        }
                        else
                        {
                            //abcLog.d("线程池关闭了pos", "线程池关闭了post");
                            Toast.makeText(httpRequsetModel.getContext(), "系统繁忙，请稍后再试", Toast.LENGTH_SHORT).show();
                        }

                    }
                    listener.netOn();
                }

                @Override
                public void netOff() {
//                    Toast.makeText(httpRequsetModel.getContext(), "网路连接断开", Toast.LENGTH_SHORT).show();
//                    LoadingDialog.getInstance(httpRequsetModel.getContext(), client).dismiss();
                    loadingDialog.dismiss();
                    listener.netOff();
                }
            }, Tool.getSp(httpRequsetModel.getContext(), SPreference.Login.sp_name, SPreference.Login.domain));
//        }else
//        {
//            Toast.makeText(httpRequsetModel.getContext(), "没有联网", Toast.LENGTH_SHORT).show();
//        }

    }
    */
    /*
    public void sendRequestV0(final GetResponseListener listener, final boolean isLoading)
    {
//        获取userid   UserId
        String userid = null;
        try {
            JSONObject userInfoJson = Tool.getSPInJsonObject(httpRequsetModel.getContext(), SPreference.Login.sp_name,SPreference.Login.userinfo);
            if (userInfoJson==null||userInfoJson.toString().equals(""))
            {
                userid = "";
            }
            else
            {
                userid = userInfoJson.getString("UserId");
            }
//            userid = "";


            if (!userid.equals(""))
            {
                httpRequsetModel.getJsonObject().put("CREATE_BY",userid);
                httpRequsetModel.getJsonObject().put("UPDATE_BY",userid);
            }

            httpRequsetModel.getJsonObject().put("AppId", "PreHospitalCare");
        } catch (JSONException e) {
            e.printStackTrace();
        }

//        检查是否联网
//        if (Tool.isNetWorkActive(httpRequsetModel.getContext()))
//        {
        final AsyncHttpClient client = new AsyncHttpClient();
        //abcLog.d("开始loading动画","开始loading动画");
//        开始loading动画
//        LoadingDialog.getInstance(httpRequsetModel.getContext(), client).show();
        final LoadingDialog loadingDialog = new LoadingDialog(httpRequsetModel.getContext(),client);
        if (isLoading)
        {
            loadingDialog.show();
        }

        //        检查网路是否通畅
        Tool.checkNetWorkStatues(httpRequsetModel.getContext(), new Tool.CheckNetWorkListener() {
            @Override
            public void netOn() {

                if (userName != null && passWord != null) {
                    Tool.printDebugLog(httpRequsetModel.getContext(),"setBasicAuth","setBasicAuthpassWord");
                    client.setBasicAuth(userName, passWord);
                } else
                {
                    Tool.printDebugLog(httpRequsetModel.getContext(),"setBasicAuth","setBasicAuthnotpassword");
                    client.setBasicAuth("Mdsd.Phep.Api", "mdsd.phep.api.2005$");
                }

//                client.setBasicAuth("Mdsd.Phep.Api", "mdsd.phep.api.2005$");
//                    client.setTimeout(8000);
                client.setMaxRetriesAndTimeout(1,8000);
                //设置超时
                client.setResponseTimeout(8000);
                //添加header开始
                JSONObject userInfoJson = Tool.getSPInJsonObject(httpRequsetModel.getContext(), SPreference.Login.sp_name, SPreference.Login.userinfo);
                String username = "";
                String loginname = "";
                //                    if (userInfoJson!=null)
//                    {
//                        if (userInfoJson.has("UserName"))
//                        {
//                            username = userInfoJson.getString("UserName");
//                        }
//                        if (userInfoJson.has("LoginName"))
//                        {
//                            loginname = userInfoJson.getString("LoginName");
//                        }
//                    }


                if (userInfoJson!=null&&!userInfoJson.equals("null")&&!userInfoJson.toString().equals("")&&httpRequsetModel.isNeedHeader())
                {
                    try {
                    if (userInfoJson.has("UserName"))
                    {
                        username = userInfoJson.getString("UserName");
                    }
                    if (userInfoJson.has("LoginName"))
                    {
                        loginname = userInfoJson.getString("LoginName");
                    }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

//                    client.addHeader("UserName",username);
//                    client.addHeader("LoginName",loginname);
//                    client.addHeader("RequestSource","VehicleMobileApp");

                    client.addHeader("UserName",Tool.base64Encode(username).replace("\n",""));
                    client.addHeader("LoginName",Tool.base64Encode(loginname).replace("\n",""));
                    client.addHeader("RequestSource",Tool.base64Encode("VehicleMobileApp").replace("\n",""));

                }

                //添加header结束
                Log.d("url连接",httpRequsetModel.getUrl()+httpRequsetModel.getJsonObject());
                if (httpRequsetModel.isGet()) {
//                    HttpGet httpGet = (HttpGet)(client.getHttpClient());
//            是get请求
                    JSONObject jsObject = httpRequsetModel.getJsonObject();
                    //                        StringEntity ss = new StringEntity(jsObject.toString());
                    StringEntity ss = new StringEntity(jsObject.toString(),"utf-8");
                    if (!client.getThreadPool().isShutdown())
                    {
                        client.get(httpRequsetModel.getContext(), httpRequsetModel.getUrl(), ss, "application/json", new JsonHttpResponseHandler() {
                            @Override
                            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                                super.onSuccess(statusCode, headers, response);
                                Log.d("响应成功"+httpRequsetModel.getUrl()+"==", response.toString());
//                                    LoadingDialog.getInstance(httpRequsetModel.getContext(), client).dismiss();
                                if (isLoading)
                                {
                                    loadingDialog.dismiss();
                                }

//                                    判断响应结果
                                try {
                                    if (response.getString("resultCode").equals("1000"))
                                    {
//                                            用户操作正确
                                        //abcLog.d("用户操作正确", "用户操作正确");


                                    }else
                                    {
//                                            用户输入的信息无效
                                        Toast.makeText(httpRequsetModel.getContext(), response.getString("message"), Toast.LENGTH_SHORT).show();
                                    }
                                    listener.onSuccess(statusCode, headers, response);


                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }


                            @Override
                            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                                super.onFailure(statusCode, headers, throwable, errorResponse);
                                //abcLog.d("failure", "网络连接出错");
//                                    LoadingDialog.getInstance(httpRequsetModel.getContext(), client).dismiss();
                                if (isLoading)
                                {
                                    loadingDialog.dismiss();
                                }
                                listener.onFailure(statusCode, headers, errorResponse);
                                Toast.makeText(httpRequsetModel.getContext(), httpRequsetModel.getContext().getResources().getString(R.string.normal_wrong) , Toast.LENGTH_SHORT).show();

                            }

                            @Override
                            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                                super.onFailure(statusCode, headers, responseString, throwable);
                                if (isLoading)
                                {
                                    loadingDialog.dismiss();
                                }
//                                    listener.onFailure(statusCode, headers, errorResponse);
                                Toast.makeText(httpRequsetModel.getContext(), httpRequsetModel.getContext().getResources().getString(R.string.normal_wrong) , Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                                super.onFailure(statusCode, headers, throwable, errorResponse);
                                if (isLoading)
                                {
                                    loadingDialog.dismiss();
                                }
//                                    listener.onFailure(statusCode, headers, errorResponse);
                                Toast.makeText(httpRequsetModel.getContext(), httpRequsetModel.getContext().getResources().getString(R.string.normal_wrong) , Toast.LENGTH_SHORT).show();
                            }
                        });
                    }else
                    {
                        //abcLog.d("线程池关闭了","线程池关闭了");
                        Toast.makeText(httpRequsetModel.getContext(), "系统繁忙，请稍后再试", Toast.LENGTH_SHORT).show();
                    }

                } else {
//            是post请求
                    JSONObject jsObject = httpRequsetModel.getJsonObject();

//                            String utf8encode = URLEncoder.encode(jsObject.toString(), "utf-8");
                    StringEntity ss = new StringEntity(jsObject.toString(),"utf-8");
//                    ss.setContentEncoding("GB2312");
//                    Log.d("start==",""+ss.toString());
                    if (!client.getThreadPool().isShutdown())
                    {
//                            client.
                        client.post(httpRequsetModel.getContext(), httpRequsetModel.getUrl(), ss, "application/json", new JsonHttpResponseHandler() {
                            @Override
                            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                                super.onSuccess(statusCode, headers, response);
                                Log.d("响应成功", response.toString());
//                                    LoadingDialog.getInstance(httpRequsetModel.getContext(), client).dismiss();
                                if (isLoading)
                                {
                                    loadingDialog.dismiss();
                                }
//                                    判断响应结果
                                try {
                                    if (response.getString("resultCode").equals("1000"))
                                    {
//                                            用户操作正确
//                                            listener.onSuccess(statusCode, headers, response);
                                    }else
                                    {
//                                            用户输入的信息无效
                                        Toast.makeText(httpRequsetModel.getContext(), response.getString("message"), Toast.LENGTH_SHORT).show();
                                    }
                                    listener.onSuccess(statusCode, headers, response);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }


                            @Override
                            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                                super.onFailure(statusCode, headers, throwable, errorResponse);
                                //abcLog.d("failure", "网络连接出错");
//                                    LoadingDialog.getInstance(httpRequsetModel.getContext(), client).dismiss();
                                if (isLoading)
                                {
                                    loadingDialog.dismiss();
                                }
                                listener.onFailure(statusCode, headers, errorResponse);
                                Toast.makeText(httpRequsetModel.getContext(), httpRequsetModel.getContext().getResources().getString(R.string.normal_wrong) , Toast.LENGTH_SHORT).show();

                            }

                        });
                    }
                    else
                    {
                        //abcLog.d("线程池关闭了pos", "线程池关闭了post");
                        Toast.makeText(httpRequsetModel.getContext(), "系统繁忙，请稍后再试", Toast.LENGTH_SHORT).show();
                    }

                }
                listener.netOn();
            }

            @Override
            public void netOff() {
//                    Toast.makeText(httpRequsetModel.getContext(), "网路连接断开", Toast.LENGTH_SHORT).show();
//                    LoadingDialog.getInstance(httpRequsetModel.getContext(), client).dismiss();
                if (isLoading)
                {
                    loadingDialog.dismiss();
                }
                listener.netOff();
            }
        }, Tool.getSp(httpRequsetModel.getContext(), SPreference.Login.sp_name, SPreference.Login.domain));
//        }else
//        {
//            Toast.makeText(httpRequsetModel.getContext(), "没有联网", Toast.LENGTH_SHORT).show();
//        }

    }
    */

    /**
     * 检查更新
     * @return
     */
//    public static boolean checkUpdate(Context context)
//    {
//        AsyncHttpClient client = new AsyncHttpClient();
//        client.setBasicAuth("Mdsd.Phep.Api", "mdsd.phep.api.2005$");
//        client.setTimeout(8000);
//        //设置超时
//        client.setResponseTimeout(8000);
////        client.get(MethodModel.checkForUpdate, )
//    }
    public interface GetResponseListener{
        public void onSuccess(int statusCode, JSONObject responseString);
        public void onFailure(int statusCode, JSONObject responseString);
        public void netOn();
        public void netOff();
}


}
