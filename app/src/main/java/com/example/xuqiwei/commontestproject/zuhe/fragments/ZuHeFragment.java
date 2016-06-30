package com.example.xuqiwei.commontestproject.zuhe.fragments;


import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;

import com.example.commonlibrary.commoninterface.ComponentOnClickListener;
import com.example.commonlibrary.components.MutableChooseButton;
import com.example.commonlibrary.components.SingleChooseButton;
import com.example.commonlibrary.fragment.CommonAbstractFragment;
import com.example.xuqiwei.commontestproject.R;
import com.example.xuqiwei.commontestproject.eventbus.HttpEvent;
import com.github.promeg.pinyinhelper.Pinyin;

import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 *
 */
public class ZuHeFragment extends CommonAbstractFragment {
    @BindView(R.id.xt_singlechoosebutton)
    SingleChooseButton xtSinglechoosebutton;
    @BindView(R.id.xt_mutablechoosebutton)
    MutableChooseButton xtMutablechoosebutton;
    @BindView(R.id.pinyin_button)
    Button pinyinButton;
    @BindView(R.id.edit_01)
    EditText edit01;
    @BindView(R.id.edit_02)
    EditText edit02;
    @BindView(R.id.edit_03)
    EditText edit03;
    @BindView(R.id.edit_04)
    EditText edit04;
    @BindView(R.id.edit_05)
    EditText edit05;
    @BindView(R.id.edit_06)
    EditText edit06;
//    private SingleChooseButton xt_singlechoosebutton;

    public ZuHeFragment() {
        // Required empty public constructor
    }

    @Override
    public void initDatas() {
        super.initDatas();


    }

    @Override
    public void initEvents() {
        super.initEvents();
        xtSinglechoosebutton.setComponentOnClickListener(new ComponentOnClickListener() {
            @Override
            public void componentClicked(View view) {

            }
        });
        xtMutablechoosebutton.setComponentOnClickListener(new ComponentOnClickListener() {
            @Override
            public void componentClicked(View view) {

            }
        });
        edit01.setImeOptions(EditorInfo.IME_ACTION_NEXT);
        edit02.setImeOptions(EditorInfo.IME_ACTION_NEXT);
        edit03.setImeOptions(EditorInfo.IME_ACTION_NEXT);
//        edit01.setNextFocusForwardId(R.id.edit_02);

//        edit02.setNextFocusForwardId(R.id.edit_03);
//        edit01.setOnKeyListener(new View.OnKeyListener() {
//            @Override
//            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
//                if (keyCode == KeyEvent.KEYCODE_ENTER) {
//                    edit02.requestFocus();
//                    edit02.requestFocusFromTouch();
//                    return true;
//
//                }
//                return false;
//            }
//        });
//        edit02.setOnKeyListener(new View.OnKeyListener() {
//            @Override
//            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
//                if (keyCode == KeyEvent.KEYCODE_ENTER) {
//                    edit03.requestFocus();
//                    edit03.requestFocusFromTouch();
//                    return true;
//
//                }
//                return false;
//            }
//        });
//        edit04.setOnKeyListener(new View.OnKeyListener() {
//            @Override
//            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
//                if (keyCode == KeyEvent.KEYCODE_ENTER) {
//                    edit05.requestFocus();
//                    edit05.requestFocusFromTouch();
//                    return true;
//
//                }
//                return false;
//            }
//        });
//        edit05.setOnKeyListener(new View.OnKeyListener() {
//            @Override
//            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
//                if (keyCode == KeyEvent.KEYCODE_ENTER) {
//                    edit06.requestFocus();
//                    edit06.requestFocusFromTouch();
//                    return true;
//
//                }
//                return false;
//            }
//        });
    }

    @Subscribe
    public void onEventMainThread(HttpEvent event) {

    }


    @Override
    public int getFragmentLayout() {
        return R.layout.fragment_zu_he;
    }


    @OnClick(R.id.pinyin_button)
    public void pinyinClicked() {
        printLog(Pinyin.toPinyin('谁').substring(0, 1));
        String ytstring = "阿士匹灵";
        char[] chname = ytstring.toCharArray();
//        printLog(ytstring.split("").length+"");
//        String[] ytarray = ytstring.split("");
        for (int i = 1; i < chname.length; i++) {
//            printLog("****"+ytarray[i]);
            Log.d("***", chname[i] + "");
        }
//        CommonSinnerPopWindow commonSinnerPopWindow = new CommonSinnerPopWindow(getContext(),pinyinButton);
//        commonSinnerPopWindow.showPopupWindow(pinyinButton);
    }


}
