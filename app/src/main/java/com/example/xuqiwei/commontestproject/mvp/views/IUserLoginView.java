package com.example.xuqiwei.commontestproject.mvp.views;

import com.example.xuqiwei.commontestproject.mvp.models.User;

/**
 * Created by xuqiwei on 16-6-24.
 */
public interface IUserLoginView {
//   登陆可能用到的方法
    String getUserName();

    String getPassword();

    void clearUserName();

    void clearPassword();

    void showLoading();

    void hideLoading();

    void toMainActivity(User user);

    void showFailedError();
}
