package com.example.commonlibrary.components;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.BitmapDrawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.commonlibrary.R;
import com.example.commonlibrary.dialog.CommonSinnerPopWindow;
import com.example.commonlibrary.model.DictionaryItem;
import com.example.commonlibrary.tools.Tool;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by xuqiwei on 16-6-27.
 * 带提示功能的下拉列表
 */
public class CommonSpinnerWithEditor extends RelativeLayout{
    private Context context;
    private List<DictionaryItem> dictionaryList;
    private String  commonspinnerwitheditor_title;
    private boolean commonspinnerwitheditor_istitleleft = false;

    private TextView title_spinnerwitheditor;
    private EditText inputedittext_spinnerwitheditor;
    private ImageView pullbutton_spinnerwitheditor;
    private LinearLayout inputspace_linearlayout;
    private PopupWindow listPopupWindow;
    private CommonSinnerPopWindow commonSinnerPopWindow;
    private CommonSpinnerWithEditor commonSpinnerWithEditor;
    public CommonSpinnerWithEditor(Context context) {
        super(context);
        initResources(context,null);

    }

    public CommonSpinnerWithEditor(Context context, AttributeSet attrs) {
        super(context, attrs);
        initResources(context,attrs);
    }

    public CommonSpinnerWithEditor(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initResources(context,attrs);
    }

    public List<DictionaryItem> getDictionaryList() {
        return dictionaryList;
    }

    public void setDictionaryList(List<DictionaryItem> dictionaryList) {
        this.dictionaryList = dictionaryList;
    }

    private void initResources(Context context, AttributeSet attrs)
    {
        this.context = context;
        commonSpinnerWithEditor = this;
        initAttrs(context, attrs);
        initViews();
        initDatas();
        initEvent();
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CommonSpinnerWithEditor);
        commonspinnerwitheditor_istitleleft = typedArray.getBoolean(R.styleable.CommonSpinnerWithEditor_commonspinnerwitheditor_istitleleft,false);
        commonspinnerwitheditor_title = typedArray.getString(R.styleable.CommonSpinnerWithEditor_commonspinnerwitheditor_title);
    }

    private void initViews() {
        if (commonspinnerwitheditor_istitleleft)
        {
            View.inflate(context,R.layout.component_titleleft_spinnerwitheditor,this);
        }else
        {
            View.inflate(context,R.layout.component_spinnerwitheditor,this);
        }
        title_spinnerwitheditor = (TextView)findViewById(R.id.title_spinnerwitheditor);
        inputedittext_spinnerwitheditor = (EditText)findViewById(R.id.inputedittext_spinnerwitheditor);
        pullbutton_spinnerwitheditor = (ImageView) findViewById(R.id.pullbutton_spinnerwitheditor);
        inputspace_linearlayout = (LinearLayout)findViewById(R.id.inputspace_linearlayout);


    }

    private void initDatas() {
        List<DictionaryItem> dictionaryItems = new ArrayList<>();
        dictionaryItems.add(new DictionaryItem("001","氯苯那敏"));
        dictionaryItems.add(new DictionaryItem("001","玻丽玛朗"));
        dictionaryItems.add(new DictionaryItem("001","地氯雷他定"));
        dictionaryItems.add(new DictionaryItem("001","氯雷他定"));
        commonSinnerPopWindow = new CommonSinnerPopWindow(context,dictionaryItems);
    }

    private void initEvent() {
        pullbutton_spinnerwitheditor.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                commonSinnerPopWindow.showPopupWindow(inputspace_linearlayout);
                commonSinnerPopWindow.findItemByPy("");
            }
        });

        commonSinnerPopWindow.setCommonSinnerListViewListener(new CommonSinnerPopWindow.CommonSinnerListViewListener() {
            @Override
            public void onListItemClicked(DictionaryItem dictionaryItem) {
                inputedittext_spinnerwitheditor.setText(dictionaryItem.getDicName());
            }
        });
        inputedittext_spinnerwitheditor.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                commonSinnerPopWindow.showPopupWindow(inputspace_linearlayout);
                commonSinnerPopWindow.findItemByPy(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}
