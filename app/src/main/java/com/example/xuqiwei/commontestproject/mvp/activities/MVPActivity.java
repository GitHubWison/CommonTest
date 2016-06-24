package com.example.xuqiwei.commontestproject.mvp.activities;

import android.os.Bundle;

import com.example.commonlibrary.activity.BaseActivity;
import com.example.xuqiwei.commontestproject.mvp.fragments.MVPFragment;

/**
 * Created by xuqiwei on 16-6-24.
 */
public class MVPActivity extends BaseActivity {
    private MVPFragment mvpFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (mvpFragment==null)
        {
            mvpFragment = new MVPFragment();
        }
        initFragment(mvpFragment,mvpFragment.getClass());

    }
}
