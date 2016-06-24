package com.example.xuqiwei.commontestproject.http;

import android.util.Log;

import com.example.commonlibrary.tools.Tool;
import com.example.commonlibrary.views.LoadingDialog;
import com.example.xuqiwei.commontestproject.test.model.TestHttpInterface;

import java.lang.reflect.Method;

/**
 * Created by xuqiwei on 16-6-21.
 */
public class HttpManager {
    private String baseUrl;

    public HttpManager() {
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public HttpManager baseUrl(String baseUrl) {
        this.baseUrl = baseUrl;

        return this;
    }
//    public TestHttpInterface create()
//    {
//        //        转化
//        for (Method method:TestHttpInterface.class.getMethods())
//        {
//            GETMETHOD getmethod = method.getAnnotation(GETMETHOD.class);
//            if (getmethod!=null)
//            {
//
//                Log.d("getmethod",getmethod.httpMethod());
//            }
//        }
//
//    }


}
