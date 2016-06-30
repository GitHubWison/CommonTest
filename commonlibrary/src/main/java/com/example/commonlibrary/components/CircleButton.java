package com.example.commonlibrary.components;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by xuqiwei on 16-6-29.
 */
public class CircleButton extends LinearLayout {
    public CircleButton(Context context) {
        super(context);
        initResources(context, null);
    }

    public CircleButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        initResources(context, attrs);
    }

    public CircleButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initResources(context, attrs);
    }

    private void initResources(Context context, AttributeSet attrs) {

    }

}
