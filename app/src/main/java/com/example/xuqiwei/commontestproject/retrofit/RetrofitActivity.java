package com.example.xuqiwei.commontestproject.retrofit;

import android.os.Bundle;

import com.example.commonlibrary.activity.BaseActivity;

/**
 * Created by xuqiwei on 16-6-19.
 */
public class RetrofitActivity extends BaseActivity {
    private  RetrofitFragment retrofitFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (retrofitFragment==null)
        {
            retrofitFragment = new RetrofitFragment();
        }
        initFragment(retrofitFragment,retrofitFragment.getClass());
    }
}
