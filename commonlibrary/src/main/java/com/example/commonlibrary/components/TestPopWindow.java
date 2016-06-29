package com.example.commonlibrary.components;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.commonlibrary.R;
import com.example.commonlibrary.tools.Tool;


import java.util.zip.Inflater;

/**
 * Created by xuqiwei-Office on 2016/4/14.
 * 自定义popwindow
 */
public class TestPopWindow extends PopupWindow {
    private View contentView;
    private TextView close_and_notshow_textview;
    private TextView close_textview;
    private TestPopWindow testPopWindow;
    public TestPopWindow(final Activity activity, final TestPopWinListener testPopWinListener) {
        super(activity);
        testPopWindow = this;
        LayoutInflater inflater = (LayoutInflater) activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        contentView = inflater.inflate(R.layout.pop_layout, null);


        int width = Tool.getscreenWidth(activity)/4;
        int height = Tool.getscreenHeight(activity)/4;
        this.setContentView(contentView);
        this.setWidth(width);
        this.setHeight(height);
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        this.update();
//        定义半透明的颜色
        ColorDrawable colorDrawable = new ColorDrawable(0000000000);
        this.setBackgroundDrawable(colorDrawable);
//        this.setAnimationStyle(R.style.AnimationPreview);
    }

    public void showPopupWindow(View parent) {
        if (!this.isShowing()) {
//            parent.getLayoutParams().width / 2
            this.showAsDropDown(parent, 0, 0);
        } else {
            this.dismiss();
        }
    }
    public interface TestPopWinListener
    {
        public void closeAndNotShow();
        public void close();
    }
}
