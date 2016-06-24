package com.example.xuqiwei.commontestproject.test.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.commonlibrary.activity.BaseActivity;
import com.example.xuqiwei.commontestproject.R;
import com.example.xuqiwei.commontestproject.test.fragments.TestFragment;

public class TestActivity extends BaseActivity {
    private TestFragment testFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (testFragment==null)
        {
            testFragment = new TestFragment();
        }
        initFragment(testFragment,testFragment.getClass());

    }
}
