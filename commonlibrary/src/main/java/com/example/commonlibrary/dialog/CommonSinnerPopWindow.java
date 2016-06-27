package com.example.commonlibrary.dialog;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.example.commonlibrary.R;
import com.example.commonlibrary.dialog.adapter.CommonSpinnerAdapter;
import com.example.commonlibrary.model.DictionaryItem;
import com.example.commonlibrary.tools.Tool;

import java.util.List;

/**
 * Created by xuqiwei on 16-6-27.
 */
public class CommonSinnerPopWindow extends PopupWindow {
    private View contentView;
    private View parentView;
    private CommonSinnerPopWindow commonSpinnerPopWindow;
//    private ListView listview_commonspinnerpopwindow;
    private int itemHeight;
    private Activity activity;
    private List<DictionaryItem> dictionaryItems;
    private Context context;
    public CommonSinnerPopWindow(final Context context, final View parentView, List<DictionaryItem> dictionaryItems, final CommonSpinnnerPopWindowListener popWindowListener) {
        activity = (Activity)context;
        LayoutInflater inflater = (LayoutInflater) activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        contentView = inflater.inflate(R.layout.component_commonspinnerpopwindow, null);
//        listview_commonspinnerpopwindow = (ListView)contentView.findViewById(R.id.listview_commonspinnerpopwindow);
        this.parentView = parentView;
        commonSpinnerPopWindow = this;
        this.dictionaryItems = dictionaryItems;
        this.context = context;
        itemHeight = (int)context.getResources().getDimension(R.dimen.input_title_height);

        this.setContentView(contentView);
        parentView.post(new Runnable() {
            @Override
            public void run() {
//                this.setWidth(width);
                commonSpinnerPopWindow.setWidth(parentView.getWidth());
                Tool.printDebugLog(context,"setWidth",parentView.getWidth()+"");
            }
        });
//        this.setWidth(width);
        int totalLength = dictionaryItems.size();
        int height = totalLength>4?itemHeight* 4:itemHeight*totalLength;
        this.setHeight(height);
        Tool.printDebugLog(context,"setHeight",height+"");
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        this.update();
//        定义半透明的颜色
        ColorDrawable colorDrawable = new ColorDrawable(0000000000);
        this.setBackgroundDrawable(colorDrawable);
        this.setAnimationStyle(R.style.AnimationPreview);

        initViews();
    }

    private void initViews() {
//        listview_commonspinnerpopwindow.setAdapter(new CommonSpinnerAdapter(dictionaryItems,context));
    }


    public void showPopupWindow() {
        if (!this.isShowing()) {
//            parent.getLayoutParams().width / 2
            this.showAsDropDown(parentView, 0, 0);
        } else {
            this.dismiss();
        }
    }
    public interface CommonSpinnnerPopWindowListener
    {
        public void onSelected(String aaa);
    }

}
