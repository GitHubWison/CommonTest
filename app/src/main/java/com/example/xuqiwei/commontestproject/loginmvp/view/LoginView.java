package com.example.xuqiwei.commontestproject.loginmvp.view;

/**
 * Created by xuqiwei on 16-6-28.
 */
public interface LoginView {
    void setUserNameError();
    void setPassWordError();
    void loginSuccessTips();
    void showLoading();
    void hideLoading();
}
