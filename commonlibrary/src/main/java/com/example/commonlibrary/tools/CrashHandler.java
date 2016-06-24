package com.example.commonlibrary.tools;

import android.content.Context;
import android.os.Environment;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

/**
 * Created by xuqiwei-Office on 2016/5/30.
 */
public class CrashHandler implements Thread.UncaughtExceptionHandler {
    private Context mContext;
    private static CrashHandler mCrashHandler=new CrashHandler();

    public static CrashHandler getInstance(){
        return mCrashHandler;
    }

    /**
     * 设置当线程由于未捕获到异常而突然终止的默认处理程序。
     */
    public void setCrashHandler(Context context){
        mContext=context;
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    /**
     * 当发生Crash时调用该方法
     */
    @Override
    public void uncaughtException(Thread thread, Throwable throwable) {
        //1 保存错误日志到SD卡
        Utils.saveCrashInfoToSDCard(mContext, throwable);
        //2 提示Crash信息
        showCrashTipToast();
        try {
            Thread.sleep(3000);
        } catch (Exception e) {
        }
        //3 退出应用
        System.exit(0);
    }

    private void showCrashTipToast() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                Toast.makeText(mContext, "很抱歉，智慧急救因为一些原因导致崩溃，请联系工程师。", Toast.LENGTH_LONG).show();
                Looper.loop();
            }
        }).start();
    }
}
