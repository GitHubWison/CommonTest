package com.example.xuqiwei.commontestproject.loginmvp.view.fragments;


import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.commonlibrary.fragment.CommonAbstractFragment;
import com.example.xuqiwei.commontestproject.R;
import com.example.xuqiwei.commontestproject.loginmvp.present.LoginPresent;
import com.example.xuqiwei.commontestproject.loginmvp.present.LoginPresentImpl;
import com.example.xuqiwei.commontestproject.loginmvp.view.LoginView;

import butterknife.BindView;
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
    @BindView(R.id.showpopwin_button)
    Button showpopwinButton;
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
        return R.layout.fragment_common_login;
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

    @Override
    public void onDestroy() {
        super.onDestroy();

    }


    @OnClick(R.id.showpopwin_button)
    public void showPopWindow() {
        LayoutInflater inflater = (LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.popupwindow_layout, null);
        PopupWindow menuWindow = new PopupWindow(layout, 100, 100); //后两个参数是width和height

        //设置如下四条信息，当点击其他区域使其隐藏，要在show之前配置
                          menuWindow.setFocusable(true);
                          menuWindow.setOutsideTouchable(true);
                          menuWindow.update();
        menuWindow.setBackgroundDrawable(new BitmapDrawable());
        menuWindow.showAsDropDown(showpopwinButton,0,0);
    }
}
