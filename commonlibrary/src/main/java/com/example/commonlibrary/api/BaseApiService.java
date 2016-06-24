package com.example.commonlibrary.api;

import com.example.commonlibrary.staticstring.CommonNetMsg;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by xuqiwei on 16-6-23.
 */
public class BaseApiService {

    public boolean isResultCodeSucess(JSONObject jsonObject) throws JSONException,NullPointerException {

        if (jsonObject.getString(CommonNetMsg.resultCodeSignal).equals(CommonNetMsg.resultCodeSuccess))
        {
            return true;
        }
        return false;
    }
    public JSONObject getDataJSONObject(JSONObject jsonObject) throws JSONException {
        return jsonObject.getJSONObject(CommonNetMsg.resultDataSignal);
    }
    public JSONArray getDataJSONArray(JSONObject jsonObject) throws JSONException {
        return jsonObject.getJSONArray(CommonNetMsg.resultDataSignal);
    }
}
