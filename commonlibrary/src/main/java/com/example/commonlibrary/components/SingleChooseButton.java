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

import butterknife.BindView;

/**
 * Created by xuqiwei on 16-6-27.
 * 单选
 */
public class SingleChooseButton extends RelativeLayout {
    private Context context;
    private TextView title_singlechoosebutton;
    private ImageView btnimg_singlechoosebutton;
    private RelativeLayout singleChooseButton;
    private String singlechoose_title;
    private boolean singlechoose_isright = true;
    private boolean singleChooseButtonStatus = false;
    private ComponentOnClickListener componentOnClickListener;

    public SingleChooseButton(Context context) {
        super(context);
        initResources(context, null);
    }

    public SingleChooseButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        initResources(context, attrs);
    }

    public SingleChooseButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initResources(context, attrs);
    }

    public boolean isSingleChooseButtonStatus() {
        return singleChooseButtonStatus;
    }

    private void initResources(Context context, AttributeSet attrs) {

        this.context = context;
        initAttrs(context, attrs);
        initViews(context);
        initDatas();
        initEvent();
    }


    private void initEvent() {
        singleChooseButton.setOnClickListener(new OnClickListener() {
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

    private void initDatas() {
        title_singlechoosebutton.setText(singlechoose_title);
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SingleChooseButton);
        singlechoose_title = typedArray.getString(R.styleable.SingleChooseButton_singlechoose_title);
        singlechoose_isright = typedArray.getBoolean(R.styleable.SingleChooseButton_singlechoose_isright,true);


    }
    private void initViews(Context context) {
        if (!singlechoose_isright)
        {
            View.inflate(context, R.layout.component_imgleft_singlechoosebutton, this).getRootView();

        }else
        {
            View.inflate(context, R.layout.component_singlechoosebutton, this).getRootView();
        }

        title_singlechoosebutton = (TextView)findViewById(R.id.title_singlechoosebutton);
        btnimg_singlechoosebutton = (ImageView)findViewById(R.id.btnimg_singlechoosebutton);
        singleChooseButton = (RelativeLayout)findViewById(R.id.main_singlechoosebutton);
        singleChooseButton = this;
    }
//    选中状态
    public void buttonSelect()
    {
        singleChooseButtonStatus = true;
        btnimg_singlechoosebutton.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.bqfx_07));
    }
    /*非选中状态*/
    public void buttonDisSelect()
    {
        singleChooseButtonStatus = false;
        btnimg_singlechoosebutton.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.bqfx_07_03));
    }
    /*翻转状态*/
    public void toggle()
    {
        if (singleChooseButtonStatus)
        {
            buttonDisSelect();
        }else
        {
            buttonSelect();
        }
    }
}
