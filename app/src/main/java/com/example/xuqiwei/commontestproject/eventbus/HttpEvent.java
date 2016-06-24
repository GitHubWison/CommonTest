package com.example.xuqiwei.commontestproject.eventbus;

import org.json.JSONObject;

/**
 * Created by xuqiwei on 16-6-23.
 */
public class HttpEvent {
    public String methodString;
    public JSONObject jsonObject;

    public HttpEvent(String methodString, JSONObject jsonObject) {
        this.methodString = methodString;
        this.jsonObject = jsonObject;
    }

    public String getMethodString() {
        return methodString;
    }

    public void setMethodString(String methodString) {
        this.methodString = methodString;
    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }

    public void setJsonObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }
}
