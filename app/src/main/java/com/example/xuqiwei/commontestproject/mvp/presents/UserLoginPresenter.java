package com.example.xuqiwei.commontestproject.mvp.presents;

import android.os.Handler;

import com.example.xuqiwei.commontestproject.mvp.biz.IUserBiz;
import com.example.xuqiwei.commontestproject.mvp.biz.OnLoginListener;
import com.example.xuqiwei.commontestproject.mvp.biz.UserBiz;
import com.example.xuqiwei.commontestproject.mvp.fragments.MVPFragment;
import com.example.xuqiwei.commontestproject.mvp.models.User;
import com.example.xuqiwei.commontestproject.mvp.views.IUserLoginView;

/**
 * Created by xuqiwei on 16-6-24.
 */
public class UserLoginPresenter {
    private IUserBiz userBiz;
    private IUserLoginView userLoginView;
    private Handler mHandler = new Handler();

    public UserLoginPresenter( IUserLoginView userLoginView) {
        this.userLoginView = userLoginView;
        this.userBiz = new UserBiz();
    }


    public void login()
    {
        userLoginView.showLoading();
        userBiz.login(userLoginView.getUserName(), userLoginView.getPassword(), new OnLoginListener()
        {

            @Override
            public void loginSuccess(User user) {
                userLoginView.toMainActivity(user);
                userLoginView.hideLoading();
            }

            @Override
            public void loginFailed() {
                userLoginView.showFailedError();
                userLoginView.hideLoading();
            }
        });
    }
}
