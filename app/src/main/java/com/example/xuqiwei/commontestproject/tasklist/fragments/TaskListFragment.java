package com.example.xuqiwei.commontestproject.tasklist.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.alibaba.fastjson.JSON;
import com.example.commonlibrary.fragment.CommonAbstractFragment;
import com.example.xuqiwei.commontestproject.R;
import com.example.xuqiwei.commontestproject.eventbus.HttpEvent;
import com.example.xuqiwei.commontestproject.tasklist.adapter.TaskListAdapter;
import com.example.xuqiwei.commontestproject.tasklist.api.TaskListApiService;
import com.example.xuqiwei.commontestproject.tasklist.api.TaskListApiServiceImp;
import com.example.xuqiwei.commontestproject.tasklist.eventbus.MissionGotEvent;
import com.example.xuqiwei.commontestproject.tasklist.eventbus.TaskListErrorEvent;
import com.example.xuqiwei.commontestproject.tasklist.models.MissionModel;
import com.example.xuqiwei.commontestproject.tasklist.models.PatientModel;

import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class TaskListFragment extends CommonAbstractFragment {
    @BindView(R.id.gridView)
    GridView gridView;
    @BindView(R.id.pulltorefresh_swiperefreshlayout)
    SwipeRefreshLayout pulltorefreshSwiperefreshlayout;

//    private FragmentTaskListBinding activityMvvmtestBinding;

    private TaskListApiService taskListApiService;

    public TaskListFragment() {
        // Required empty public constructor
    }


    @Override
    public int getFragmentLayout() {
        return R.layout.fragment_task_list;
    }

    @Override
    public void initDatas() {
        super.initDatas();
        taskListApiService = new TaskListApiServiceImp();
        pullDatas();

    }

    @Override
    public void initEvents() {
        super.initEvents();
        pulltorefreshSwiperefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pullDatas();
            }
        });
    }


    private void pullDatas() {
        taskListApiService.getMissionbyvehicleid(getContext());

    }

    @Subscribe
    public void onEventMainThread(HttpEvent event) {
        MissionModel missionModel = JSON.parseObject(event.getJsonObject().toString(), MissionModel.class);
        String missionID = missionModel.getMISSION_ID();
//        toast(missionID);
        taskListApiService.getPatientBymissionid(getContext(), missionID);
    }

    //    得到患者信息
    @Subscribe
    public void onEventMainThread(MissionGotEvent event) {
        List<PatientModel> patientModels = JSON.parseArray(event.getJsonArray().toString(), PatientModel.class);
        gridView.setAdapter(new TaskListAdapter(patientModels, getContext()));
        pulltorefreshSwiperefreshlayout.setRefreshing(false);
    }
//    错误处理
    @Subscribe
    public void onEventMainThreadError(TaskListErrorEvent event)
    {
        pulltorefreshSwiperefreshlayout.setRefreshing(false);
    }



}
