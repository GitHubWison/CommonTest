package com.example.xuqiwei.commontestproject.test.model;

import android.util.Log;

import com.example.commonlibrary.httpmanager.RemoteHandler;
import com.example.xuqiwei.commontestproject.http.GETMETHOD;
import com.example.xuqiwei.commontestproject.http.TestAnno;

/**
 * Created by xuqiwei on 16-6-21.
 */
public class TestModel {

    @TestAnno(username = "hello")
    private String userTestName;

    public TestModel() {
    }


}
