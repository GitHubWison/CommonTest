package com.example.xuqiwei.commontestproject.mvp.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.commonlibrary.fragment.CommonAbstractFragment;
import com.example.xuqiwei.commontestproject.R;
import com.example.xuqiwei.commontestproject.mvp.models.User;
import com.example.xuqiwei.commontestproject.mvp.presents.UserLoginPresenter;
import com.example.xuqiwei.commontestproject.mvp.views.IUserLoginView;
import com.example.xuqiwei.commontestproject.tasklist.eventbus.TaskListErrorEvent;

import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class MVPFragment extends CommonAbstractFragment implements IUserLoginView {


    @BindView(R.id.username_edittext)
    EditText usernameEdittext;
    @BindView(R.id.password_edittext)
    EditText passwordEdittext;
    @BindView(R.id.submit_button)
    Button submitButton;

    private UserLoginPresenter mUserLoginPresenter = new UserLoginPresenter(this);

    public MVPFragment() {
        // Required empty public constructor
    }
    @Subscribe
    public void onEventMainThreadError(TaskListErrorEvent event)
    {
    }


    @Override
    public int getFragmentLayout() {
        return R.layout.fragment_mv;
    }


    @Override
    public String getUserName() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public void clearUserName() {

    }

    @Override
    public void clearPassword() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void toMainActivity(User user) {

    }

    @Override
    public void showFailedError() {

    }


    @OnClick(R.id.submit_button)
    public void onSubmitClick() {
        mUserLoginPresenter.login();
    }
}
