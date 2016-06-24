package com.example.xuqiwei.commontestproject.zuhe.activities;

import android.os.Bundle;

import com.example.commonlibrary.activity.BaseActivity;
import com.example.xuqiwei.commontestproject.zuhe.fragments.ZuHeFragment;

/**
 * Created by xuqiwei on 16-6-24.
 */
public class ZuHeActivity extends BaseActivity{
    private ZuHeFragment zuHeFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (zuHeFragment==null)
        {
            zuHeFragment = new ZuHeFragment();
        }
        initFragment(zuHeFragment,zuHeFragment.getClass());

    }
}
