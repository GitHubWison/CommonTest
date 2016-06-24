package com.example.commonlibrary.commoninterface;

import org.json.JSONObject;

/**
 * Created by xuqiwei on 16-6-22.
 */
public interface ServiceListener {
    void onSuccess( JSONObject responseString);
    void onFailure(JSONObject responseString);
}
