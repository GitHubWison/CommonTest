package com.example.xuqiwei.commontestproject.test.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.commonlibrary.commoninterface.NetWorkListenerV2;
import com.example.commonlibrary.fragment.CommonAbstractFragment;
import com.example.commonlibrary.httpmanager.RemoteHandler;
import com.example.xuqiwei.commontestproject.R;
import com.example.xuqiwei.commontestproject.http.HttpManager;
import com.example.xuqiwei.commontestproject.http.TestAnno;
import com.example.xuqiwei.commontestproject.test.model.TestHttpInterface;
import com.example.xuqiwei.commontestproject.test.model.TestModel;

import org.apache.http.protocol.HTTP;
import org.json.JSONObject;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * A simple {@link Fragment} subclass.
 */
public class TestFragment extends CommonAbstractFragment {
    private TextView username_textview;

    @TestAnno(username = "helloname")
    private String testString;

    public TestFragment() {
        // Required empty public constructor
    }

    @Override
    public int getFragmentLayout() {
        return R.layout.fragment_test;
    }
//
//    @Override
//    public void initDatas() {
//
//    }
//
//    @Override
//    public void initViews() {
//        username_textview = (TextView)getView().findViewById(R.id.username_textview);
//
//    }
      /*
    @Override
    public void initEvents() {
        username_textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                printLog("testanno_count="+TestModel.class.getFields().length);
                for (Field field:TestModel.class.getDeclaredFields())
                {
                    printLog("method_testann0o="+field.getName());
//                    printLog("method_anno=="+annotation.toString());
                    TestAnno testAnno = field.getAnnotation(TestAnno.class);
                    if (testAnno!=null)
                    {
                        printLog("testAnnousername="+testAnno.username());
                    }
                }

                HttpManager httpManager = new HttpManager().baseUrl("http://192.168.191.1:4500");
                httpManager.create().startRequest(new JSONObject()).setNetWorkListenerV2(new NetWorkListenerV2() {
                    @Override
                    public void onSuccess(int statusCode, JSONObject responseString) {

                    }

                    @Override
                    public void onFailure(int statusCode, JSONObject responseString) {

                    }
                })




            }
        });

    }
    */

}
