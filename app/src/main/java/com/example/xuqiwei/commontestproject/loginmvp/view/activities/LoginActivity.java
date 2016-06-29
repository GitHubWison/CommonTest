package com.example.xuqiwei.commontestproject.loginmvp.view.activities;

import android.os.Bundle;

import com.example.commonlibrary.activity.BaseActivity;
import com.example.xuqiwei.commontestproject.loginmvp.view.fragments.LoginFragment;
import com.example.xuqiwei.commontestproject.loginmvp.view.fragments.TestLoginFragment;

/**
 * Created by xuqiwei on 16-6-28.
 */
public class LoginActivity extends BaseActivity {
    private TestLoginFragment loginFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hiddenActionBar();
        if(loginFragment==null)
        {
            loginFragment = new TestLoginFragment();
        }
        initFragment(loginFragment,loginFragment.getClass());

    }
}
