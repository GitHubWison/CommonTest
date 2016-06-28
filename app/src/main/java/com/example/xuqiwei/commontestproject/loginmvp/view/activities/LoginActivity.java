package com.example.xuqiwei.commontestproject.loginmvp.view.activities;

import android.os.Bundle;

import com.example.commonlibrary.activity.BaseActivity;
import com.example.xuqiwei.commontestproject.loginmvp.view.fragments.LoginFragment;

/**
 * Created by xuqiwei on 16-6-28.
 */
public class LoginActivity extends BaseActivity {
    private LoginFragment loginFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(loginFragment==null)
        {
            loginFragment = new LoginFragment();
        }
        initFragment(loginFragment,loginFragment.getClass());

    }
}
