package com.example.xuqiwei.commontestproject.tasklist.eventbus;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by xuqiwei on 16-6-23.
 */
public class MissionGotEvent{
    public String methodString;
    public JSONArray jsonArray;

    public MissionGotEvent(String methodString, JSONArray jsonArray) {
        this.methodString = methodString;
        this.jsonArray = jsonArray;
    }

    public String getMethodString() {
        return methodString;
    }

    public void setMethodString(String methodString) {
        this.methodString = methodString;
    }

    public JSONArray getJsonArray() {
        return jsonArray;
    }

    public void setJsonArray(JSONArray jsonArray) {
        this.jsonArray = jsonArray;
    }
}
