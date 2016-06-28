package com.example.xuqiwei.commontestproject.loginmvp.model;

import android.os.Handler;
import android.text.TextUtils;

import java.util.concurrent.Delayed;

/**
 * Created by xuqiwei on 16-6-28.
 */
public class LoginInteractorImpl implements LoginInteractor {
    @Override
    public void login(final String userName, final String password, final LoginListener loginListener) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!userName.equals("admin"))
                {
                    loginListener.loginUserNameFailure();
                    return;
                }
                if (!password.equals("123"))
                {
                    loginListener.loginPassWordFailure();
                    return;
                }
                loginListener.loginSuccess();

            }
        },3000);
    }
}
