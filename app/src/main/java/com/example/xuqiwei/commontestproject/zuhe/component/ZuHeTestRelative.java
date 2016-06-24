package com.example.xuqiwei.commontestproject.zuhe.component;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.databinding.DataBindingUtil;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.xuqiwei.commontestproject.R;
import com.example.xuqiwei.commontestproject.databinding.ZuhelayoutCompBinding;
import com.example.xuqiwei.commontestproject.model.ZhuCeCompontModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by xuqiwei on 16-6-24.
 */
public class ZuHeTestRelative extends RelativeLayout {


//    @BindView(R.id.show_textview)
//    TextView showTextview;
//    @BindView(R.id.button)
//    Button button;

    private ZuhelayoutCompBinding zuhelayoutCompBinding;
    private String zuhe_btnname;

    public ZuHeTestRelative(Context context, AttributeSet attrs) {
        super(context, attrs);
        initSources(context,attrs);
    }

    public ZuHeTestRelative(Context context) {
        super(context);
        initSources(context,null);
    }

    public ZuHeTestRelative(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initSources(context,attrs);
    }

    private void initSources(Context context,AttributeSet attrs) {
        View rootView = View.inflate(context,R.layout.zuhelayout_comp,this).getRootView();
       DataBindingUtil.getBinding(LayoutInflater.from(context).inflate(R.layout.zuhelayout_comp, this, true)) ;
//        zuhelayoutCompBinding = DataBindingUtil.bind(rootView);
//        zuhelayoutCompBinding = DataBindingUtil.bind( View.inflate(context, R.layout.zuhelayout_comp, this).getRootView());
//        zuhelayoutCompBinding = DataBindingUtil.setContentView((Activity) context,R.layout.zuhelayout_comp);
//        zuhelayoutCompBinding = DataBindingUtil.inflate(LayoutInflater.from(getContext()),R.layout.zuhelayout_comp,this,false);
//        ButterKnife.bind(this, this);
        initAttrs(context,attrs);
        initViews(context);
    }

    private void initViews(Context context) {
//        button.setText(zuhe_btnname);
//        zuhelayoutCompBinding.setZucemodel(new ZhuCeCompontModel("111222"));

    }

    private void initAttrs(Context context,AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ZuHeTestRelative);
        zuhe_btnname = typedArray.getString(R.styleable.ZuHeTestRelative_zuhe_btnname);

    }


    @OnClick(R.id.button)
    public void onClick() {
//        showTextview.setText("Hello world!");
    }
}
