package com.example.commonlibrary.activity;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import com.example.commonlibrary.R;
import com.example.commonlibrary.staticstring.CacheName;
import com.example.commonlibrary.tools.ACache;
import com.example.commonlibrary.tools.Tool;
import com.orhanobut.logger.Logger;

import org.json.JSONObject;
import org.w3c.dom.Text;

/*
基类
 */
public class BaseActivity extends AppCompatActivity {
    private ActionBar bar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.activity_base);
        initDatas();
        initViews();
        initEvents();

    }


    public void initDatas()
    {

    }
    public void initViews()
    {

        bar = getSupportActionBar();
        if (bar!=null)
        {

            bar.setDisplayHomeAsUpEnabled(true);
            bar.setHomeButtonEnabled(true);

            //abcLog.d("bar", "ActionBar非空");
        }
        else
        {
            //abcLog.d("bar","ActionBar空");
        }
    }
    public void initEvents()
    {

    }

    /**
     *
     * @param fragment
     */
    public void jumpToFragment(Fragment fragment, Class cls)
    {
        Tool.hideKeyBoard(this);
        if (fragment == null)
        {
            fragment = new Fragment();
        }
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

            fragmentTransaction.setCustomAnimations(R.anim.in_from_right,
                    R.anim.out_to_left, R.anim.in_from_left,
                    R.anim.out_to_right).replace(R.id.fragment_common_layout, fragment, cls.getName()).addToBackStack(null).commit();

    }

    /**
     * 初始化fragment
     * @param fragment
     * @param cls
     */
    public void initFragment(Fragment fragment , Class cls)
    {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.fragment_common_layout, fragment, cls.getName()).commit();
    }





    /**
     * 隐藏右侧的setting按钮
     * @param isHidden
     */
    public void setSettingIconHidden(Boolean isHidden)
    {

    }
    /**
     * 不显示actionbar
     */
    public void hiddenActionBar()
    {
        getSupportActionBar().hide();
    }




//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        return super.onOptionsItemSelected(item);
//    }

    @Override
    protected void onResume() {
        super.onResume();
//        if(getRequestedOrientation()!=ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE){
//            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
//        }
    }

    protected void toast(String text)
    {
        Toast.makeText(BaseActivity.this, text, Toast.LENGTH_SHORT).show();
    }
    //    存放json类型的cache
    public void saveJsonCache(String cacheName,JSONObject jsonObject)
    {
        ACache cache = ACache.get(this);
        cache.put(cacheName,jsonObject);
    }
    //存放String或int类型的cache
    public void saveStringOrIntCache(String cacheName,String cacheContent)
    {
        ACache cache = ACache.get(this);
        cache.put(cacheName,cacheContent);
    }
    /*
    获得json类的缓存
     */
    public JSONObject getJsonCache(String cacheName)
    {
        ACache cache = ACache.get(this);
        JSONObject json = cache.getAsJSONObject(cacheName);
        return json;
    }


    /**
     * 获得String类的缓存
     */
    public String getStringCache(String cacheName)
    {
        ACache cache = ACache.get(this);
        String resultString = cache.getAsString(cacheName);
        return resultString;
    }

    /**
     * 获得int类型的缓存
     */
    public int getIntCache(String cacheName)
    {
        ACache cache = ACache.get(this);
        int resultInt = Integer.parseInt(cache.getAsString(cacheName));
        return resultInt;
    }
    /*
打印日志：在debug模式下打印
 */
    public void printJsonLog(String jsonObject)
    {
        String debugString = getStringCache(CacheName.isDebug_String);
        if (debugString.equals("false"))
        {
//            release模式

        }else
        {
//            Log.d(msg_title,msg);
            Logger.json(jsonObject);
        }
    }
    public void printLog(String msg_title, String msg)
    {
        String debugString = getStringCache(CacheName.isDebug_String);
        if (debugString.equals("false"))
        {
//            release模式

        }else
        {
            Log.d(msg_title,msg);
//            Logger.d(msg_title,);
        }

    }
    public void printLog(String msg)
    {
        String debugString = getStringCache(CacheName.isDebug_String);
        if (debugString.equals("false"))
        {
//            release模式

        }else
        {
            Log.d(this.getClass().getSimpleName(),msg);
        }

    }

}
