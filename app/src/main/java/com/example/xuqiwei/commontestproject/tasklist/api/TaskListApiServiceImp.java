package com.example.xuqiwei.commontestproject.tasklist.api;

import android.content.Context;

import com.example.commonlibrary.api.BaseApiService;
import com.example.commonlibrary.commoninterface.NetWorkListenerV2;
import com.example.commonlibrary.httpmanager.RemoteHandler;
import com.example.commonlibrary.staticstring.CacheName;
import com.example.commonlibrary.tools.Tool;
import com.example.xuqiwei.commontestproject.eventbus.HttpEvent;
import com.example.xuqiwei.commontestproject.tasklist.eventbus.MissionGotEvent;
import com.example.xuqiwei.commontestproject.tasklist.eventbus.TaskListErrorEvent;
import com.example.xuqiwei.commontestproject.xt.model.UserInfo;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by xuqiwei on 16-6-23.
 */
public class TaskListApiServiceImp extends BaseApiService implements TaskListApiService {
    @Override
    public void getMissionbyvehicleid(Context context) {
        String carName = "苏E 0A591";
        String base64String = Tool.base64EncodeV2(carName);

        String methodString = TaskListApiService.getMissionbyvehicleid
                + "?vehicleId="+base64String;
        RemoteHandler remoteHandler = new RemoteHandler(new JSONObject(),methodString,context);
        remoteHandler.setNetWorkListenerV2(new NetWorkListenerV2() {
            @Override
            public void onSuccess(int statusCode, JSONObject responseString) {
                try {
                    if (isResultCodeSucess(responseString))
                    {
                        JSONObject resultJsonObject = getDataJSONArray(responseString).getJSONObject(0);
                        EventBus.getDefault().post(new HttpEvent(TaskListApiService.getMissionbyvehicleid,resultJsonObject));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, JSONObject responseString) {
                EventBus.getDefault().post(new TaskListErrorEvent());
            }

            @Override
            public void onNetOFF() {
                super.onNetOFF();
                EventBus.getDefault().post(new TaskListErrorEvent());
            }
        }).getExecute();
    }

    @Override
    public void getPatientBymissionid(Context context,String missionID) {
        String methodString = TaskListApiService.getpatientbymissionid+"/"+missionID;
        RemoteHandler remoteHandler = new RemoteHandler(new JSONObject(),methodString,context);
        remoteHandler.setNetWorkListenerV2(new NetWorkListenerV2() {
            @Override
            public void onSuccess(int statusCode, JSONObject responseString) {
                try {
                    if (isResultCodeSucess(responseString))
                    {
                        EventBus.getDefault().post(new MissionGotEvent(TaskListApiService.getpatientbymissionid,getDataJSONArray(responseString)));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int statusCode, JSONObject responseString) {
                EventBus.getDefault().post(new TaskListErrorEvent());
            }

            @Override
            public void onNetOFF() {
                super.onNetOFF();
                EventBus.getDefault().post(new TaskListErrorEvent());
            }
        }).getExecute();
    }
}
