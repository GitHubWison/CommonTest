package com.example.commonlibrary.httpmanager;

import android.content.Context;

import com.example.commonlibrary.commoninterface.NetWorkListenerV2;
import com.example.commonlibrary.views.LoadingDialog;

import org.json.JSONObject;

import java.util.List;

/**
 * Created by xuqiwei on 16-6-28.
 * 处理多个网络请求
 */
public class MutableRemoteHandler {
    private List<RemoteHandler> remoteHandlers;
    private Context context;
//    LoadingDialog loadingDialog;
    private int count=0;

    public MutableRemoteHandler(List<RemoteHandler> remoteHandlers,Context context) {
        this.remoteHandlers = remoteHandlers;
        this.context = context;
    }
    public void postMutableExecute(final MutableRemoteListener mutableRemoteListener, final MutableRemoteAllEndListener mutableRemoteAllEndListener)
    {
//        loadingDialog = new LoadingDialog(context);
//        loadingDialog.show();
        for (int i=0;i<remoteHandlers.size();i++)
        {
            final int finalI = i;
            remoteHandlers.get(i).setLoading(false);
            remoteHandlers.get(i).setNetWorkListenerV2(new NetWorkListenerV2() {
                @Override
                public void onSuccess(int statusCode, JSONObject responseString) {
                    count++;
                    if (count==remoteHandlers.size())
                    {
//                        loadingDialog.dismiss();
                        mutableRemoteAllEndListener.onAllResponseEnd();
                    }
                    mutableRemoteListener.onMutableSuccess(finalI,responseString);
                }

                @Override
                public void onFailure(int statusCode, JSONObject responseString) {
                    count++;
                    if (count==remoteHandlers.size())
                    {
//                        loadingDialog.dismiss();
                        mutableRemoteAllEndListener.onAllResponseEnd();
                    }
                    mutableRemoteListener.onMutableFailure(finalI,responseString);
                }

                @Override
                public void onNetOFF() {
                    super.onNetOFF();
                    count++;
                    if (count==remoteHandlers.size())
                    {
//                        loadingDialog.dismiss();
                        mutableRemoteAllEndListener.onAllResponseEnd();
                    }
                    mutableRemoteListener.onMutableNetOff();
                }
            }).postExecute();
        }
    }

    interface MutableRemoteListener
    {
        void onMutableSuccess(int tag,JSONObject responseString);
        void onMutableFailure(int tag,JSONObject responseString);
        void onMutableNetOff();
    }
    interface MutableRemoteAllEndListener
    {
        void onAllResponseEnd();
    }
}
