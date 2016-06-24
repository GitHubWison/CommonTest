package com.example.xuqiwei.commontestproject.mvp.biz;

import com.example.xuqiwei.commontestproject.mvp.models.User;

/**
 * Created by xuqiwei on 16-6-24.
 */
public interface OnLoginListener {
    void loginSuccess(User user);

    void loginFailed();
}
