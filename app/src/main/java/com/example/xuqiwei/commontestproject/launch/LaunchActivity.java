package com.example.xuqiwei.commontestproject.launch;

import com.example.xuqiwei.commontestproject.MainActivity;

/**
 * Created by xuqiwei on 16-6-19.
 */
public class LaunchActivity extends com.example.commonlibrary.activity.LaunchActivity {
    private LaunchFragment launchFragment;
    @Override
    public void initDatas() {
        super.initDatas();
        setMainActivityClass(MainActivity.class);

    }

    @Override
    public void initViews() {
        super.initViews();
        if (launchFragment==null)
        {
            launchFragment = new LaunchFragment();
        }
        initFragment(launchFragment,launchFragment.getClass());

    }
}
