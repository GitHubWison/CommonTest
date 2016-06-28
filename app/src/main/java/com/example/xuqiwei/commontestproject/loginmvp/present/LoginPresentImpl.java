package com.example.xuqiwei.commontestproject.loginmvp.present;

import com.example.xuqiwei.commontestproject.loginmvp.model.LoginInteractor;
import com.example.xuqiwei.commontestproject.loginmvp.model.LoginInteractorImpl;
import com.example.xuqiwei.commontestproject.loginmvp.view.LoginView;

/**
 * Created by xuqiwei on 16-6-28.
 */
public class LoginPresentImpl implements LoginPresent{
    private LoginInteractor loginInteractor;
    private LoginView loginView;

    public LoginPresentImpl(LoginView loginView) {
        this.loginView = loginView;
        this.loginInteractor = new LoginInteractorImpl();
    }

    @Override
    public void checkUserNamePassWord(String userName, String passWord) {
        loginView.showLoading();
        loginInteractor.login(userName, passWord, new LoginInteractor.LoginListener() {
            @Override
            public void loginSuccess() {
                loginView.loginSuccessTips();
                loginView.hideLoading();
            }

            @Override
            public void loginUserNameFailure() {
                loginView.setUserNameError();
                loginView.hideLoading();
            }

            @Override
            public void loginPassWordFailure() {
                loginView.setPassWordError();
                loginView.hideLoading();
            }
        });
    }

}
