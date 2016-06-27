package com.example.commonlibrary.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.commonlibrary.R;
import com.example.commonlibrary.dialog.TimeSelectDialog;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;

/**
 * Created by xuqiwei on 16-6-27.
 */
public class TimeSelector extends RelativeLayout {
    private Context context;
    private String timeselector_title;
    private boolean mutablechoose_istitleleft = false;
    private boolean timeselector_isshowtime = true;

    private TextView title_timeselector;
    private TextView showdate_timeselector;

    private TimeSelector timeSelector;
    private TimeSelectDialog timeSelectDialog;

    public TimeSelector(Context context) {
        super(context);
        initResources(context, null);
    }

    public TimeSelector(Context context, AttributeSet attrs) {
        super(context, attrs);
        initResources(context, attrs);
    }

    public TimeSelector(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initResources(context, attrs);
    }

    private void initResources(Context context, AttributeSet attrs) {
        this.context = context;
        timeSelector = TimeSelector.this;
        initAttrs(context, attrs);
        initViews(context);
        initDatas();
        initEvent();
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TimeSelector);
        timeselector_title = typedArray.getString(R.styleable.TimeSelector_timeselector_title);
        mutablechoose_istitleleft = typedArray.getBoolean(R.styleable.TimeSelector_timeselector_istitleleft,false);
        timeselector_isshowtime = typedArray.getBoolean(R.styleable.TimeSelector_timeselector_isshowtime,true);
    }

    private void initViews(Context context) {
        if (mutablechoose_istitleleft)
        {
            View.inflate(context,R.layout.component_titleleft_timeselector,this);
        }
        else
        {
            View.inflate(context,R.layout.component_timeselector,this);
        }
        title_timeselector = (TextView)findViewById(R.id.title_timeselector);
        showdate_timeselector = (TextView)findViewById(R.id.showdate_timeselector);
        timeSelectDialog = new TimeSelectDialog(context,timeselector_isshowtime);
        
    }

    private void initDatas() {
        if (timeselector_title!=null)
        {
            title_timeselector.setText(timeselector_title);

        }
    }

    private void initEvent() {
        timeSelector.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
//                弹出时间选择页面
                TimeSelectDialog.OnTimeSelectedListener timeSelectedListener = new TimeSelectDialog.OnTimeSelectedListener() {
                    @Override
                    public void onSure(Date date) {
                        SimpleDateFormat format;
                        if (timeselector_isshowtime)
                        {
                            format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        }
                        else
                        {
                            format =new SimpleDateFormat("yyyy-MM-dd");
                        }
//                        SimpleDateFormat );
                        showdate_timeselector.setText(format.format(date));
                        timeSelectDialog.dismiss();
                    }

                    @Override
                    public void onCancel() {

                    }
                };
                timeSelectDialog.setOnTimeSelectedListener(timeSelectedListener);
                timeSelectDialog.show();
            }
        });
        
    }
}
