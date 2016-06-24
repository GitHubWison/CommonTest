package com.example.xuqiwei.commontestproject.launch;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.commonlibrary.fragment.CommonAbstractFragment;
import com.example.xuqiwei.commontestproject.R;
import com.example.xuqiwei.commontestproject.eventbus.HttpEvent;

import org.greenrobot.eventbus.Subscribe;

/**
 * A simple {@link Fragment} subclass.
 */
public class LaunchFragment extends CommonAbstractFragment {


    public LaunchFragment() {
        // Required empty public constructor
    }



    @Override
    public int getFragmentLayout() {
        return R.layout.fragment_launch;
    }

    @Override
    public void initDatas() {

    }
    @Subscribe
    public void onEventMainThread(HttpEvent event)
    {

    }

//    @Override
//    public void initDatas() {
//
//    }
//
//    @Override
//    public void initViews() {
//
//    }
//
//    @Override
//    public void initEvents() {
//
//    }

}
