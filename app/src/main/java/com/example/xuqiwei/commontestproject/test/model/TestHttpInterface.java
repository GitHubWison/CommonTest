package com.example.xuqiwei.commontestproject.test.model;

import android.content.Context;

import com.example.commonlibrary.httpmanager.RemoteHandler;
import com.example.xuqiwei.commontestproject.http.GETMETHOD;

import org.json.JSONObject;

/**
 * Created by xuqiwei on 16-6-21.
 */
public interface TestHttpInterface {
    @GETMETHOD(httpMethod = "/api/public/test")
    void startRequest(JSONObject jsonObject, Context context);
}
