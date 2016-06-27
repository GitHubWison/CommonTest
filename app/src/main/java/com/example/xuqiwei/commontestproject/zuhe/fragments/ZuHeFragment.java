package com.example.xuqiwei.commontestproject.zuhe.fragments;


import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

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
        printLog(Pinyin.toPinyin('谁').substring(0,1));
    }
}
