package com.example.commonlibrary.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.commonlibrary.R;
import com.example.commonlibrary.commoninterface.ComponentOnClickListener;

/**
 * Created by xuqiwei on 16-6-29.
 */
public class CheckBoxButton extends RelativeLayout {
    private Context context;
    private TextView title_checkboxbutton;
    private ImageView btnimg_checkboxbutton;
    private RelativeLayout checkBoxButton;

    private String checkboxbutton_title;
    private boolean checkboxbuttonStatus=false;
    private int titleTextColor;
    private boolean checkboxbutton_isselected = false;


    private ComponentOnClickListener componentOnClickListener;
    public CheckBoxButton(Context context) {
        super(context);
        initResources(context, null);
    }

    public CheckBoxButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        initResources(context, attrs);
    }

    public CheckBoxButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initResources(context, attrs);
    }

    public boolean isCheckboxChecked() {
        return checkboxbuttonStatus;
    }


    private void initResources(Context context, AttributeSet attrs) {

        this.context = context;
        initAttrs(context, attrs);
        initViews();
        initDatas();
        initEvent();
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CheckBoxButton);
        checkboxbutton_title = typedArray.getString(R.styleable.CheckBoxButton_checkboxbutton_title);
        titleTextColor = typedArray.getColor(R.styleable.CheckBoxButton_checkboxbutton_titlecolor, 0xFFFFFFFF);
        checkboxbutton_isselected = typedArray.getBoolean(R.styleable.CheckBoxButton_checkboxbutton_isselected,false);
    }

    private void initViews() {
        View.inflate(context, R.layout.component_checkboxbutton, this);
        title_checkboxbutton = (TextView)findViewById(R.id.title_checkboxbutton);
        btnimg_checkboxbutton = (ImageView)findViewById(R.id.btnimg_checkboxbutton);
        checkBoxButton = (RelativeLayout)findViewById(R.id.main_checkboxbutton);

    }

    private void initDatas() {
        title_checkboxbutton.setText(checkboxbutton_title);
        title_checkboxbutton.setTextColor(titleTextColor);
        if (checkboxbutton_isselected)
        {
            checkboxButtonSelect();
        }else
        {
            checkboxButtonDisSelect();
        }
    }

    private void initEvent() {
        checkBoxButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (componentOnClickListener!=null)
                {
//                    首先翻转状态
                    toggle();
//                    执行后续操作
                    componentOnClickListener.componentClicked(view);
                }
            }
        });

    }

    public ComponentOnClickListener getComponentOnClickListener() {
        return componentOnClickListener;
    }

    public void setComponentOnClickListener(ComponentOnClickListener componentOnClickListener) {
        this.componentOnClickListener = componentOnClickListener;
    }

    //    选中状态
    public void checkboxButtonSelect()
    {
        checkboxbuttonStatus = true;
        btnimg_checkboxbutton.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.checkbox_selected));
    }
    /*非选中状态*/
    public void checkboxButtonDisSelect()
    {
        checkboxbuttonStatus = false;
        btnimg_checkboxbutton.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.checkbox_notselect));
    }
    public void setChecked(boolean ischecked)
    {
        if (ischecked)
        {
            checkboxButtonSelect();
        }
        else
        {
            checkboxButtonDisSelect();
        }

    }

    /*翻转状态*/
    public void toggle()
    {
        if (checkboxbuttonStatus)
        {
            checkboxButtonDisSelect();
        }else
        {
            checkboxButtonSelect();
        }
    }
}
