package com.example.xuqiwei.commontestproject.retrofit.model;

/**
 * Created by xuqiwei on 16-6-20.
 */
public class FailureModel {
    private String status;
    private String message;

    public FailureModel(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
