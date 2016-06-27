package com.example.commonlibrary.dialog;

import android.app.Dialog;

import android.content.Context;
import android.os.Bundle;

import android.view.View;

import android.view.Window;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;


import com.example.commonlibrary.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;



/**
 * Created by xuqiwei-Office on 2016/5/3.
 */
public class TimeSelectDialog extends Dialog implements NumberPicker.Formatter{
    private TimeSelectDialog timeSelectDialog;
    private NumberPicker hour_time_picker;
    private NumberPicker min_time_picker;
    private NumberPicker sec_time_picker;
    private TextView sure_commontextview;
    private TextView cancel_commontextview;
    private TextView clear_commontextview;
    private boolean hasHMS;
    private LinearLayout hms_linearlayout;

    private OnTimeSelectedListener onTimeSelectedListener;
    private DatePicker datePicker;
    public TimeSelectDialog(Context context,OnTimeSelectedListener onTimeSelectedListener) {
        super(context);
        timeSelectDialog = this;
        this.onTimeSelectedListener = onTimeSelectedListener;
    }
    public TimeSelectDialog(Context context,boolean hasHMS,OnTimeSelectedListener onTimeSelectedListener) {
        super(context);
        timeSelectDialog = this;
        this.onTimeSelectedListener = onTimeSelectedListener;
        this.hasHMS = hasHMS;
    }
    public TimeSelectDialog(Context context,boolean hasHMS)
    {
        super(context);
        timeSelectDialog = this;
        this.hasHMS = hasHMS;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        timeSelectDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.select_time_layout);
        initDatas();
        initViews();
        initEvents();



    }

    private void initViews() {

        hour_time_picker = (NumberPicker)findViewById(R.id.hour_time_picker);
        min_time_picker = (NumberPicker)findViewById(R.id.min_time_picker);
        sec_time_picker = (NumberPicker)findViewById(R.id.sec_time_picker);
        sure_commontextview = (TextView)findViewById(R.id.sure_commontextview);
        cancel_commontextview = (TextView)findViewById(R.id.cancel_commontextview);
        clear_commontextview = (TextView)findViewById(R.id.clear_commontextview);

        hms_linearlayout = (LinearLayout)findViewById(R.id.hms_linearlayout);
        if (hasHMS)
        {
            clear_commontextview.setVisibility(View.VISIBLE);
            hms_linearlayout.setVisibility(View.VISIBLE);

        }else
        {
            clear_commontextview.setVisibility(View.GONE);
            hms_linearlayout.setVisibility(View.GONE);
        }
        datePicker = (DatePicker)findViewById(R.id.datePicker);


        hour_time_picker.setMinValue(0);
        hour_time_picker.setMaxValue(23);
        hour_time_picker.setFormatter(this);
//        hour_time_picker.set

        min_time_picker.setMinValue(0);
        min_time_picker.setMaxValue(59);
        min_time_picker.setFormatter(this);

        sec_time_picker.setMinValue(0);
        sec_time_picker.setMaxValue(59);
        sec_time_picker.setFormatter(this);

        initTimes();
    }

    private void initEvents() {
        sure_commontextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                int year = datePicker.getYear();
                int month = datePicker.getMonth() + 1;
                int day = datePicker.getDayOfMonth();

                int hour = hour_time_picker.getValue();
                int min = min_time_picker.getValue();
                int sec = sec_time_picker.getValue();
                String timeString="";
                if (hasHMS)
                {
                    format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    timeString= year+"-"+month+"-"+day+" "+hour+":"+min+":"+sec;
                }else
                {
                    format = new SimpleDateFormat("yyyy-MM-dd");
                    timeString = year+"-"+month+"-"+day;

                }

                Date date = new Date();
                try {
                    date = format.parse(timeString);
//                    Tool.printDebugLog(getContext(),"date=",date.toString());
                    onTimeSelectedListener.onSure(date);
//                    timeSelectDialog.dismiss();
                } catch (ParseException e) {
                    e.printStackTrace();
                    timeSelectDialog.dismiss();
                }
//                date.setTime();
//                date.setYear();


            }
        });
        cancel_commontextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onTimeSelectedListener.onCancel();
                timeSelectDialog.dismiss();
            }
        });
        clear_commontextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                hour_time_picker.setValue(0);
//                min_time_picker.setValue(0);
//                sec_time_picker.setValue(0);
                initTimes();
            }
        });

    }

    private void initDatas() {
    }

    public OnTimeSelectedListener getOnTimeSelectedListener() {
        return onTimeSelectedListener;
    }

    public void setOnTimeSelectedListener(OnTimeSelectedListener onTimeSelectedListener) {
        this.onTimeSelectedListener = onTimeSelectedListener;
    }

    @Override
    public String format(int value) {
        if (value/10 == 0)
        {
          return "0"+ value;
        }
        return value +"";
    }
    public interface OnTimeSelectedListener
    {
        public void onSure(Date date);
        public void onCancel();
    }


    //"2015-03-23  12:12:12"
    private String LocalToGTM(String LocalDate) {


        SimpleDateFormat format;
        format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
        Date result_date;
        long result_time = 0;


        if (null == LocalDate) {
            return LocalDate;
        } else {
            try {
                format.setTimeZone(TimeZone.getDefault());
                result_date = format.parse(LocalDate);
                result_time = result_date.getTime();
                format.setTimeZone(TimeZone.getTimeZone("GMT00:00"));
                return format.format(result_time);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return LocalDate;


    }
//    初始化时间控件
    private void initTimes()
    {
        SimpleDateFormat format;
        Date currentdate = new Date();
        format=new SimpleDateFormat("yyyy:MM:dd:HH:mm:ss");
        String formattedNowDate = format.format(currentdate);
        String[] dataArray = formattedNowDate.split(":");
        if (hasHMS)
        {
//            Tool.printDebugLog(getContext(),"formattedNowDate",formattedNowDate);
            hour_time_picker.setValue(Integer.parseInt(dataArray[3]));
            min_time_picker.setValue(Integer.parseInt(dataArray[4]));
            sec_time_picker.setValue(Integer.parseInt(dataArray[5]));

        }
        datePicker.updateDate(Integer.parseInt(dataArray[0]),Integer.parseInt(dataArray[1])-1,Integer.parseInt(dataArray[2]));
        /*
        else
        {
            format = new SimpleDateFormat("yyyy-MM-dd");
        }
        */
    }
}
