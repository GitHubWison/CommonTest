package com.example.xuqiwei.commontestproject.http;

import android.content.Context;

import com.example.commonlibrary.commoninterface.NetWorkListenerV2;
import com.example.commonlibrary.commoninterface.ServiceListener;

import org.json.JSONObject;

/**
 * Created by xuqiwei on 16-6-22.
 */
public interface APIService {
    public static final String connectTest="/api/TestNet/Get";
    void connectTest(Context context);

    public static final String login="/api/Public/CheckUserIsValid";
    void login(Context context, JSONObject jsonObject);
}
