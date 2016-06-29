package com.example.commonlibrary.fragment;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.commonlibrary.R;
import com.example.commonlibrary.commoninterface.ComponentOnClickListener;
import com.example.commonlibrary.components.CheckBoxButton;
import com.example.commonlibrary.tools.Tool;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xuqiwei on 16-6-29.
 */
public class LoginFragment extends CommonAbstractFragment implements View.OnClickListener{

    private LinearLayout common_loginmain_linearlayout;
    private CheckBoxButton rememberusername_checkboxbutton;
    private EditText username_edittext;
    private EditText password_edittext;
    private TextView setting_textview;
    private TextView login_textview;
    private TextView logintitle_textview;

    public static final String isRememberUserName = "REMEMBER_USERNAME";
    public static final String userNameStore = "USERNAME_STORE";
//    public static final String passwordStore = "PASSWORD_STORE";
    @Override
    public int getFragmentLayout() {
        return R.layout.fragment_common_login;
    }

    @Override
    public void initCreatedView() {
        super.initDatas();
        common_loginmain_linearlayout = (LinearLayout)getView().findViewById(R.id.common_loginmain_linearlayout);
        rememberusername_checkboxbutton = (CheckBoxButton)getView().findViewById(R.id.rememberusername_checkboxbutton);
        username_edittext = (EditText)getView().findViewById(R.id.username_edittext);
        password_edittext = (EditText)getView().findViewById(R.id.password_edittext);
        setting_textview = (TextView)getView().findViewById(R.id.setting_textview);
        login_textview = (TextView)getView().findViewById(R.id.login_textview);
        logintitle_textview = (TextView)getView().findViewById(R.id.logintitle_textview);

        String rem = getStringCache(isRememberUserName);
//        printLog(rem);

//        password_edittext.setText(getStringCache(passwordStore));
        if (rem!=null)
        {
            if (rem.equals("false"))
            {
                rememberusername_checkboxbutton.setChecked(false);
            }else
            {
                username_edittext.setText(getStringCache(userNameStore));
                rememberusername_checkboxbutton.setChecked(true);
            }
//            rememberusername_checkboxbutton.setChecked(rem.equals("false")?false:true);
        }
//
//        String username_store = getStringCache(userNameStore);
//        String


        rememberusername_checkboxbutton.setComponentOnClickListener(new ComponentOnClickListener() {
            @Override
            public void componentClicked(View view) {

            }
        });
        login_textview.setOnClickListener(this);
        setting_textview.setOnClickListener(this);
        logintitle_textview.setText(setLoginTitle());
        password_edittext.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent keyEvent) {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (imm.isActive()) {

                        imm.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);

                    }

                    return true;
                }

                return false;
            }
            });


    }


    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.login_textview)
        {
            String userName = username_edittext.getText().toString();
            String passWord = password_edittext.getText().toString();
            if (TextUtils.isEmpty(userName)||TextUtils.isEmpty(passWord))
            {
                toast(getResources().getString(R.string.login_completemessage));
                return;
            }
            loginVoid(userName,passWord);

        }
        if (view.getId()==R.id.setting_textview)
        {
            toSettingActivity();
        }
    }

//跳转到配置页面
    public void toSettingActivity() {

    }
//开始登录
    public void loginVoid(String userName,String passWord)
    {
//        demo
//        if (rememberusername_checkboxbutton.isCheckboxChecked())
//        {
//            saveStringOrIntCache(userNameStore,getUserName());
//            saveStringOrIntCache(isRememberUserName,rememberusername_checkboxbutton.isCheckboxChecked()?"":"false");
//        }else
//        {
//
//        }
    }
    public boolean isRememberChecked()
    {
        return rememberusername_checkboxbutton.isCheckboxChecked();
    }
    private String getUserName()
    {
        return username_edittext.getText().toString();
    }
//    private String getPa
    public String setLoginTitle()
    {
        return "麦迪斯顿医疗系统";
    }

}
