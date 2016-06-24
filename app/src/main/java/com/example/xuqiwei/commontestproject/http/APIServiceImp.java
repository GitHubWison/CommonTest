package com.example.xuqiwei.commontestproject.http;

import android.content.Context;
import android.widget.Toast;

import com.example.commonlibrary.commoninterface.NetWorkListenerV2;
import com.example.commonlibrary.commoninterface.ServiceListener;
import com.example.commonlibrary.httpmanager.RemoteHandler;
import com.example.commonlibrary.staticstring.CommonNetMsg;
import com.example.xuqiwei.commontestproject.eventbus.HttpEvent;
import com.example.xuqiwei.commontestproject.eventbus.TestEvent;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;



/**
 * Created by xuqiwei on 16-6-22.
 */
public class APIServiceImp implements APIService{
    @Override
    public void connectTest(final Context context) {

//                请求网络
                RemoteHandler remoteHandler = new RemoteHandler(new JSONObject(),APIService.connectTest,context);
                remoteHandler.setNetWorkListenerV2(new NetWorkListenerV2() {
                    @Override
                    public void onSuccess(int statusCode, JSONObject responseString) {
                        try {
                            if (responseString.getString(CommonNetMsg.resultCodeSignal).equals(CommonNetMsg.resultCodeSuccess))
                            {
                                EventBus.getDefault().post(new TestEvent(responseString));

                            }else
                            {
//                        serviceListener.onFailure(responseString);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, JSONObject responseString) {
//                serviceListener.onFailure(responseString);
                    }
                }).postExecute();

            }

    @Override
    public void login(final Context context, final JSONObject jsonObject) {
        RemoteHandler remoteHandler = new RemoteHandler(jsonObject,APIService.login,context);
        remoteHandler.setNetWorkListenerV2(new NetWorkListenerV2() {
            @Override
            public void onSuccess(int statusCode, JSONObject responseString) {
                try {
                    if (responseString.getString(CommonNetMsg.resultCodeSignal).equals(CommonNetMsg.resultCodeSuccess))
                    {
                        EventBus.getDefault().post(new HttpEvent(APIService.login,responseString.getJSONObject(CommonNetMsg.resultDataSignal)));

                    }
                    else
                    {
                        Toast.makeText(context, "登陆失败", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, JSONObject responseString) {

            }
        }).postExecute();
    }


}
