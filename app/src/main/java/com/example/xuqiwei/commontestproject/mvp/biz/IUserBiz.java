package com.example.xuqiwei.commontestproject.mvp.biz;

/**
 * Created by xuqiwei on 16-6-24.
 */
public interface IUserBiz {
    public void login(String username, String password, OnLoginListener loginListener);
}
