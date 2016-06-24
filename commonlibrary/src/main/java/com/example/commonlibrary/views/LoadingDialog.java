package com.example.commonlibrary.views;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.commonlibrary.R;
import com.example.commonlibrary.tools.Tool;


/**
 * Created by xuqiwei-Office on 2016/3/30.
 * 网络请求开始时显示的loading页面
 */
public class LoadingDialog extends Dialog {
    private Context context;
    /*
    提示语句
     */
    private String tips;
    private ProgressBar progressBar;
    private LinearLayout pb_linearlayout;
    private static LoadingDialog instanceLoadingBar;
    private TextView loading_text_textView;

    /**
     * 方便以后扩展
     * @param context
     * @param tips
     */
    public LoadingDialog(Context context, String tips) {
        super(context);
        this.context = context;
        this.tips = tips;
    }

    public LoadingDialog(Context context) {
        super(context);
        this.context = context;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progress);
        initDatas();
        initViews();
        initEvents();
    }

    private void initEvents() {

    }

    private void initViews() {
        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        pb_linearlayout = (LinearLayout)findViewById(R.id.pb_linearlayout);
        int width = Tool.getscreenWidth((Activity) context) < Tool.getscreenHeight((Activity) context) ? Tool.getscreenWidth((Activity) context) : Tool.getscreenHeight((Activity) context);
        pb_linearlayout.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, width*1/8));
        this.setCanceledOnTouchOutside(false);// 设置点击屏幕Dialog不消失

        loading_text_textView = (TextView)findViewById(R.id.loading_text_textView);
//        CustomerViewInterface.AutoTextSize.getSmall1(context)
        loading_text_textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,context.getResources().getInteger(R.integer.font_small1));


    }

    private void initDatas() {

    }

//    @Override
//    public void dismiss() {
//        super.dismiss();
////        终止网络请求
////        asyncHttpClient.cancelRequests(context, true);
//        if (asyncHttpClient!=null)
//        {
//            asyncHttpClient.getThreadPool().shutdownNow();
//        }
//
//        //abcLog.d("dismiss","终止网络请求");
////        发出一个通知，让所有的fragment收到
//        EventBus.getDefault().post(new DialogDismissEvent("dialogDismiss"));
//    }
}
