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
 * Created by xuqiwei on 16-6-27.
 * 多选
 */
public class MutableChooseButton extends RelativeLayout {
    private Context context;
    private TextView title_mutablechoosebutton;
    private ImageView btnimg_mutablechoosebutton;
    private RelativeLayout mutableChooseButton;
    private String mutablechoose_title;
    private boolean mutablechoose_isright = true;
    private boolean mutableChooseButtonStatus = false;
    private ComponentOnClickListener componentOnClickListener;

    public MutableChooseButton(Context context) {
        super(context);
        initResources(context, null);
    }

    public MutableChooseButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        initResources(context, attrs);
    }

    public MutableChooseButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initResources(context, attrs);
    }

    public boolean isSingleChooseButtonStatus() {
        return mutableChooseButtonStatus;
    }

    private void initResources(Context context, AttributeSet attrs) {


        this.context = context;
        initAttrs(context, attrs);
        initViews(context);
        initDatas(context);
        initEvent();
    }


    private void initEvent() {
        mutableChooseButton.setOnClickListener(new OnClickListener() {
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

    private void initDatas(Context context) {
        title_mutablechoosebutton.setText(mutablechoose_title);
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MutableChooseButton);
        mutablechoose_title = typedArray.getString(R.styleable.MutableChooseButton_mutablechoose_title);
        mutablechoose_isright = typedArray.getBoolean(R.styleable.MutableChooseButton_mutablechoose_isright,true);


    }
    private void initViews(Context context) {
        if (!mutablechoose_isright)
        {
            View.inflate(context, R.layout.component_imgleft_mutablechoosebutton, this);
        }else
        {
            View.inflate(context, R.layout.component_mutablechoosebutton, this);
        }
        title_mutablechoosebutton = (TextView)findViewById(R.id.title_mutablechoosebutton);
        btnimg_mutablechoosebutton = (ImageView)findViewById(R.id.btnimg_mutablechoosebutton);
        mutableChooseButton = (RelativeLayout)findViewById(R.id.main_mutablechoosebutton);
        mutableChooseButton = this;
//        if (!singlechoose_isright)
    }
    //    选中状态
    public void buttonSelect()
    {
        mutableChooseButtonStatus = true;
        btnimg_mutablechoosebutton.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.bqfx_06));
    }
    /*非选中状态*/
    public void buttonDisSelect()
    {
        mutableChooseButtonStatus = false;
        btnimg_mutablechoosebutton.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.bqfx_11));
    }
    /*翻转状态*/
    public void toggle()
    {
        if (mutableChooseButtonStatus)
        {
            buttonDisSelect();
        }else
        {
            buttonSelect();
        }
    }
}
