package com.example.xuqiwei.commontestproject.loginmvp.model;

/**
 * Created by xuqiwei on 16-6-28.
 */
public interface LoginInteractor {
    interface LoginListener{
        void loginSuccess();
        void loginUserNameFailure();
        void loginPassWordFailure();
    }
    void login(String userName,String password,LoginListener loginListener);
}
