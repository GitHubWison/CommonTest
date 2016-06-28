package com.example.xuqiwei.commontestproject.loginmvp.view.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.commonlibrary.fragment.CommonAbstractFragment;
import com.example.xuqiwei.commontestproject.R;
import com.example.xuqiwei.commontestproject.loginmvp.present.LoginPresent;
import com.example.xuqiwei.commontestproject.loginmvp.present.LoginPresentImpl;
import com.example.xuqiwei.commontestproject.loginmvp.view.LoginView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends CommonAbstractFragment implements LoginView {


    @BindView(R.id.uname_edittext)
    EditText unameEdittext;
    @BindView(R.id.pword_edittext)
    EditText pwordEdittext;
    @BindView(R.id.sub_button)
    Button subButton;
    @BindView(R.id.loading_progressbar)
    ProgressBar loadingProgressbar;
    private LoginPresent loginPresent;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void initDatas() {
        super.initDatas();
        loginPresent = new LoginPresentImpl(this);

    }

    @Override
    public int getFragmentLayout() {
        return R.layout.fragment_login;
    }

    @OnClick(R.id.sub_button)
    public void loginSubmit() {
        loginPresent.checkUserNamePassWord(unameEdittext.getText().toString(), pwordEdittext.getText().toString());
    }


    @Override
    public void setUserNameError() {
        unameEdittext.setError("用户名错误");
    }

    @Override
    public void setPassWordError() {
        pwordEdittext.setError("密码错误");
    }

    @Override
    public void loginSuccessTips() {
        Toast.makeText(getContext(), "登录成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {
        loadingProgressbar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        loadingProgressbar.setVisibility(View.INVISIBLE);

    }

}
