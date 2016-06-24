package com.example.xuqiwei.commontestproject.xt.activity;

import android.os.Bundle;

import com.example.commonlibrary.activity.BaseActivity;
import com.example.xuqiwei.commontestproject.xt.fragments.XTFragment;

public class XTActivity extends BaseActivity {
    private XTFragment xtFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (xtFragment==null)
        {
            xtFragment = new XTFragment();
        }
        initFragment(xtFragment,xtFragment.getClass());

    }
}
