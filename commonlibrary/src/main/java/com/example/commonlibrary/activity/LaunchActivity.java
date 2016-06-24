package com.example.commonlibrary.activity;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.view.WindowManager;

import com.example.commonlibrary.staticstring.CacheName;

/**
 * Created by xuqiwei on 16-6-12.
 */
public class LaunchActivity extends BaseActivity {
    private Class mainActivityClass;

    public Class getMainActivityClass() {
        return mainActivityClass;
    }

    public void setMainActivityClass(Class mainActivityClass) {
        this.mainActivityClass = mainActivityClass;
    }

    @Override
    public void initDatas() {
        super.initDatas();
//        将版本是否为debug的信息保存
        ApplicationInfo info= this.getApplicationInfo();
        String isDebug = "false";
        isDebug = (info.flags& ApplicationInfo.FLAG_DEBUGGABLE)!=0?"true":"false";
        saveStringOrIntCache(CacheName.isDebug_String,isDebug);

    }

    @Override
    public void initViews() {
        super.initViews();
//        隐藏标题栏
        hiddenActionBar();
        //隐藏状态栏
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        new Thread()
        {
            public void run()
            {
                try {
                    sleep(1000);     //等待三秒,
                    //        检查更新
//                    checkUpDate();
                    if (mainActivityClass!=null)
                    {
                        startActivity(new Intent(LaunchActivity.this,mainActivityClass));
                        finish();
                    }


                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                //progressDia//abcLog.dismiss();
            }
        }.start();
    }
}
