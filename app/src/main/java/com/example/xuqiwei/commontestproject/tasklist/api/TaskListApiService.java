package com.example.xuqiwei.commontestproject.tasklist.api;

import android.content.Context;

import org.json.JSONObject;

/**
 * Created by xuqiwei on 16-6-23.
 */
public interface TaskListApiService {
    public final static String getMissionbyvehicleid = "/api/mobilemission/getMissionbyvehicleid";
    void getMissionbyvehicleid(Context context);

    public final static String getpatientbymissionid = "/api/mobilepatient/getpatientbymissionid";
    void getPatientBymissionid(Context context,String missionID);
}
