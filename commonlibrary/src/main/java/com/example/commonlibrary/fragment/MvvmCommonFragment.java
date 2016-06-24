package com.example.commonlibrary.fragment;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.commonlibrary.activity.BaseActivity;
import com.example.commonlibrary.staticstring.CacheName;
import com.example.commonlibrary.staticstring.CommonNetMsg;
import com.example.commonlibrary.tools.ACache;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.ButterKnife;

/**
 * Created by xuqiwei on 16-6-13.
 */
public abstract class MvvmCommonFragment extends Fragment {
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

//        initViews();
//        initEvents();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = initDataBindingView(inflater, container, savedInstanceState);
        EventBus.getDefault().register(this);
        ButterKnife.bind(this, view);
        initDatas();
        return view;
    }

    public void initDatas(){

    }
//    public abstract void initViews();
//    public abstract void initEvents();
    public abstract View initDataBindingView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState);

    /**
     * 设置标题
     * @param
     */
    public void setActionBarTitle(int titleId)
    {
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(titleId);
    }
    public void setActionBarTitleWithString(String title)
    {
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(title);
    }
    /**
     * 隐藏左侧的返回按钮
     * @param isHidden
     */
    public void setBackButtonHidden(Boolean isHidden)
    {
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(!isHidden);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);//反注册EventBus
    }



    /**
     * 跳转到activity
     * @param cls
     */
    public void toActivity(Class cls)
    {
        startActivity(new Intent(getContext(), cls));
    }


    protected void toast(String text)
    {
        Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
    }

    public Drawable zoomDrawable(Drawable drawable, int w, int h) {
        int width = drawable.getIntrinsicWidth();
        int height = drawable.getIntrinsicHeight();
        Bitmap oldbmp = drawableToBitmap(drawable);
        Matrix matrix = new Matrix();
        float scaleWidth = ((float) w / width);
        float scaleHeight = ((float) h / height);
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap newbmp = Bitmap.createBitmap(oldbmp, 0, 0, width, height,
                matrix, true);
        return new BitmapDrawable(null, newbmp);
    }

    public Bitmap drawableToBitmap(Drawable drawable) {
        int width = drawable.getIntrinsicWidth();
        int height = drawable.getIntrinsicHeight();
        Bitmap.Config config = drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                : Bitmap.Config.RGB_565;
        Bitmap bitmap = Bitmap.createBitmap(width, height, config);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, width, height);
        drawable.draw(canvas);
        return bitmap;
    }
    //    存放json类型的cache
    public void saveJsonCache(String cacheName,JSONObject jsonObject)
    {
        ACache cache = ACache.get(getContext());
        cache.put(cacheName,jsonObject);
    }
    //存放String或int类型的cache
    public void saveStringOrIntCache(String cacheName,String cacheContent)
    {
        ACache cache = ACache.get(getContext());
        cache.put(cacheName,cacheContent);
    }
    /*
    获得json类的缓存
     */
    public JSONObject getJsonCache(String cacheName)
    {
        ACache cache = ACache.get(getContext());
        JSONObject json = cache.getAsJSONObject(cacheName);
        return json;
    }


    /**
     * 获得String类的缓存
     */
    public String getStringCache(String cacheName)
    {
        ACache cache = ACache.get(getContext());
        String resultString = cache.getAsString(cacheName);
        return resultString;
    }

    /**
     * 获得int类型的缓存
     */
    public int getIntCache(String cacheName)
    {
        ACache cache = ACache.get(getContext());
        int resultInt = Integer.parseInt(cache.getAsString(cacheName));
        return resultInt;
    }

    /*
打印日志：在debug模式下打印
*/
    public void printLog(String msg_title, String msg)
    {
        String debugString = getStringCache(CacheName.isDebug_String);
        if (debugString.equals("false"))
        {
//            release模式

        }else
        {
            Log.d(msg_title,msg);
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

    public void setActionBackImage(int backImage)
    {
        try {
            int actionbarHeight = getActionBarHeight();
//                    Tool.getSPInJsonObject(getContext(), SPreference.Dimens.sp_name,SPreference.Dimens.actionHeight).getInt("ActionBarHeight");

            //abcLog.d("actionbar的高度",actionbarHeight+"" );
            Drawable drawable = ContextCompat.getDrawable(getContext(), backImage);
            ((BaseActivity)getActivity()).getSupportActionBar().setHomeAsUpIndicator(zoomDrawable(drawable, actionbarHeight/2,actionbarHeight/2));

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    /**
     * 返回actionbar的高度
     *
     */
    public int getActionBarHeight() throws JSONException {
        int actionbarh = 0;

        actionbarh = getIntCache(CacheName.actionBarHeight_int);
//                Tool.getSPInJsonObject(getContext(), SPreference.Dimens.sp_name, SPreference.Dimens.actionHeight).getInt("ActionBarHeight");
        return actionbarh;

    }

    /**
     * 对空数值的判断
     * @return
     */
    public boolean isNotNull(String string)
    {
        if (string==null&&string.equals("")||string.equals("null"))
        {
            return false;
        }
        return true;
    }

    /**
     * 判断返回的数值是否正常
     * @param jsonObject
     * @return
     * @throws JSONException
     */
    public boolean isResultCodeSucess(JSONObject jsonObject) throws JSONException,NullPointerException {

        if (jsonObject.getString(CommonNetMsg.resultCodeSignal).equals(CommonNetMsg.resultCodeSuccess))
        {
            return true;
        }
        return false;
    }



}
