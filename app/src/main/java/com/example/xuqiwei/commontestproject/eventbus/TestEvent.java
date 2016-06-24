package com.example.xuqiwei.commontestproject.eventbus;

import org.json.JSONObject;

/**
 * Created by xuqiwei on 16-6-22.
 */
public class TestEvent {
    private JSONObject mMsg;
    public TestEvent(JSONObject msg) {
        mMsg = msg;
    }
    public JSONObject getMsg(){
        return mMsg;
    }
}
