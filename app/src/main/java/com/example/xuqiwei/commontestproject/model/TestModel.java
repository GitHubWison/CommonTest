package com.example.xuqiwei.commontestproject.model;

/**
 * Created by xuqiwei on 16-6-22.
 */
public class TestModel {

    /**
     * data : success
     * message :
     * resultCode : 1000
     * IsSuccess : true
     */

    private String data;
    private String message;
    private String resultCode;
    private boolean IsSuccess;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public boolean isIsSuccess() {
        return IsSuccess;
    }

    public void setIsSuccess(boolean IsSuccess) {
        this.IsSuccess = IsSuccess;
    }
}
