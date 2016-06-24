package com.example.commonlibrary.tools;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.system.ErrnoException;

import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.commonlibrary.R;
import com.example.commonlibrary.activity.BaseActivity;
import com.example.commonlibrary.staticstring.CacheName;
import com.orhanobut.logger.Logger;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by Nangy-Office on 2016/3/24.
 */
public class Tool {
    /*
    存储登录信息的sharepreference
     */
    public static String LOGIN_SP = "LOGIN";

    public static int[] taskListBgColor = {
            R.color.task_bg1,
            R.color.task_bg2,
            R.color.task_bg3,
            R.color.task_bg4
    };

    public static JSONObject activityJson;


    /**
     * MD5加密程序
     * @param info
     * @return
     */
    public static String getMD5(String info)
    {
        try
        {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(info.getBytes("UTF-8"));
            byte[] encryption = md5.digest();

            StringBuffer strBuf = new StringBuffer();
            for (int i = 0; i < encryption.length; i++)
            {
                if (Integer.toHexString(0xff & encryption[i]).length() == 1)
                {
                    strBuf.append("0").append(Integer.toHexString(0xff & encryption[i]));
                }
                else
                {
                    strBuf.append(Integer.toHexString(0xff & encryption[i]));
                }
            }

            return strBuf.toString().toUpperCase();
        }
        catch (NoSuchAlgorithmException e)
        {
            return "";
        }
        catch (UnsupportedEncodingException e)
        {
            return "";
        }
    }
    public static String base64Encode(String str)
    {
       String result = Base64.encodeToString(str.getBytes(), Base64.DEFAULT);
        return result;
    }
    public static String base64EncodeV2(String str)
    {
        String result = Base64.encodeToString(str.getBytes(), Base64.DEFAULT).replace("\n","");
        return result;
    }

    /**
     * 将jsonobject转换成StringEntity,网络传输
     * @param
     * @return

    public static StringEntity getEntityWithJsonObject(JSONObject jsObject)
    {
        JSONObject jsonObject = new JSONObject();
        StringEntity stringEntity = null;
        try {
            stringEntity = new StringEntity(jsonObject.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return stringEntity;

    }
     */


    /*
    public static void checkNetWorkStatues(final Context context, final CheckNetWorkListener checkNetWorkListener)
    {
        String domain = Tool.getSp(context, SPreference.Login.sp_name, SPreference.Login.domain);
        //abcLog.d("domain==", domain);
        if (!domain.equals("")){
            //            已经将domain记录在了sp中
            String url = MethodModel.spellURL(domain, MethodModel.testConnection);
            new com.test.medicalsystem.tools.NetAsynTask(url, new com.test.medicalsystem.tools.NetAsynTask.CheckNetWorkListener() {
                @Override
                public void netOn() {
                    checkNetWorkListener.netOn();
                }

                @Override
                public void netOff() {
//
                    checkNetWorkListener.netOff();
                    Toast.makeText(context, "网络不通，请检查服务器设置和网络连接", Toast.LENGTH_SHORT).show();
                }
            }).execute();


        }
        else {
            //abcLog.d("error==","没有记录domain");
        }
    }
    */

    public static void checkNetWorkStatues(final Context context, final CheckNetWorkListener checkNetWorkListener, String url)
    {
        String domain = Tool.getStringCache(context,CacheName.domain_String);
//                Tool.getSp(context, SPreference.Login.sp_name, SPreference.Login.domain);
//        String domain = SImpleca

        //abcLog.d("domain==", domain);

            //            已经将domain记录在了sp中
//            String url = MethodModel.spellURL(domain, MethodModel.testConnection);
            new NetAsynTask(url, new NetAsynTask.CheckNetWorkListener() {
                @Override
                public void netOn() {
//                    Toast.makeText(context, "通", Toast.LENGTH_SHORT).show();
                    checkNetWorkListener.netOn();
                }

                @Override
                public void netOff() {
//
                    checkNetWorkListener.netOff();
                    Toast.makeText(context, "网络不通，请检查服务器设置和网络连接", Toast.LENGTH_SHORT).show();
                }
            }).execute();



    }

//    public static boolean checkURL(String url){
//        boolean value=false;
//        try {
//            HttpURLConnection conn=(HttpURLConnection)new URL(url).openConnection();
//            int code=conn.getResponseCode();
//            System.out.println(">>>>>>>>>>>>>>>> "+code+" <<<<<<<<<<<<<<<<<<");
//            if(code!=200){
//                value=false;
//            }else{
//                value=true;
//            }
//        } catch (MalformedURLException e) {
//// TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (IOException e) {
//// TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        return value;
//    }


    public interface CheckNetWorkListener
    {
        /**
         * 内网通畅
         */
        public void netOn();

        /**
         * 内网链接异常
         */
        public void netOff();
    }

    /**
     * 获得sharepreference
     * @param context
     * @param spClass sharepreference种类
     * @param spName sharepreference具体的名
     * @return

    public static String getSp(Context context, String spClass, String spName)
    {

        SharedPreferences sharedPreferences = context.getSharedPreferences(spClass, Context.MODE_PRIVATE);
        String result = sharedPreferences.getString(spName, "");

        return result;

    }
     */
    //读取缓存的json并转换成javabean
    public static <T> T getBeanCache(Context context,String cacheName, Class<T> clazz)
    {
        String cacheJsonString = getJsonCache(context,cacheName).toString();
        return (T) JSON.parseObject(cacheJsonString,clazz);
    }
//    缓存javabean类型
//    public static void saveJavaBeanCache(Class<T> clazz)
    //    存放json类型的cache
    public static void saveJsonCache(Context context,String cacheName,JSONObject jsonObject)
    {
        ACache cache = ACache.get(context);
        cache.put(cacheName,jsonObject);
    }
    //存放String或int类型的cache
    public static void saveStringOrIntCache(Context context,String cacheName,String cacheContent)
    {
        ACache cache = ACache.get(context);
        cache.put(cacheName,cacheContent);
    }
    /*
    获得json类的缓存
     */
    public static JSONObject getJsonCache(Context context,String cacheName)
    {
        ACache cache = ACache.get(context);
        JSONObject json = cache.getAsJSONObject(cacheName);
        return json;
    }


    /**
     * 获得String类的缓存
     */
    public static String getStringCache(Context context,String cacheName)
    {
        ACache cache = ACache.get(context);
        String resultString = cache.getAsString(cacheName);
        return resultString;
    }

    /**
     * 获得int类型的缓存
     */
    public static int getIntCache(Context context,String cacheName)
    {
        ACache cache = ACache.get(context);
        int resultInt = Integer.parseInt(cache.getAsString(cacheName));
        return resultInt;
    }

    /**
     * 存入sharepreference
     * @param context
     * @param spClass sharepreference种类
     * @param spName sharepreference具体的名
     * @param spValue  sharepreference具体的值

    public static void saveSp(Context context, String spClass, String spName, String spValue)
    {




        SharedPreferences share = context.getSharedPreferences(spClass, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = share.edit(); //编辑文件
        edit.putString(spName, spValue);
        edit.commit();  //保存数据信息

    }
     */

    /**
     * 将返回的jsonobject保存到sharepreference
     * @param context
     * @param spClass
     * @param spName
     * @param jsonObject

    public static void saveSPByJsonObject(Context context, String spClass, String spName,JSONObject jsonObject)
    {
        try {
            //abcLog.d("spclass",spClass);
            //abcLog.d("spName",spName);
            String spValue = jsonObject.toString();
            SharedPreferences share = context.getSharedPreferences(spClass, Context.MODE_PRIVATE);
            SharedPreferences.Editor edit = share.edit(); //编辑文件
            edit.putString(spName, spValue);
            edit.commit();  //保存数据信息
        }catch (NullPointerException ex)
        {

        }

    }
     */
    /**
     * 返回的是json字符串
     * @param context
     * @param spClass
     * @param spName
     * @return

    public static JSONObject getSPInJsonObject(Context context, String spClass, String spName)
    {
        //abcLog.d("getspclass",spClass);
        //abcLog.d("getspName",spName);
        SharedPreferences sharedPreferences = context.getSharedPreferences(spClass, Context.MODE_PRIVATE);
        String result = sharedPreferences.getString(spName, "");
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(result);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;

    }
     */
    /**
     * 判断某个jsonobj在sharepreference中是否存在
     */
    public static boolean isSPJSONExist(Context context, String spClass, String spName)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(spClass, Context.MODE_PRIVATE);
        String result = sharedPreferences.getString(spName, "");
        if (result == "")
        {
            return false;
        }
        return true;
    }

    /**
     * 获得屏幕的宽度
     * @param activity
     * @return
     */
    public static int getscreenWidth(Activity activity) {
        DisplayMetrics metric = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metric);
        int width = metric.widthPixels;
//        int height = metric.heightPixels;
//        float density = metric.density;
//        int densityDpi = metric.densityDpi;
        return width;
    }

    /**
     * 获得屏幕的高度
     * @param activity
     * @return
     */
    public static int getscreenHeight(Activity activity) {
        DisplayMetrics metric = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metric);
//        int width = metric.widthPixels;
        int height = metric.heightPixels;
//        float density = metric.density;
//        int densityDpi = metric.densityDpi;
        return height;
    }
    /**
     * 检测网络是否连接
     */
//    public static boolean isNetWorkActive(Context context)
//    {
//        boolean flag = false;
//        ConnectivityManager manager=(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE); //获取系统服务
//        if (manager != null)
//        {
//            flag = manager.getActiveNetworkInfo().isAvailable();
//
//        }
//        return flag;
//    }

//    public boolean isConnectingToInternet(){
//        ConnectivityManager connectivity = (ConnectivityManager) _context.getSystemService(Context.CONNECTIVITY_SERVICE);
//        if (connectivity != null)
//        {
//            Network[] networks = connectivity.getAllNetworks();
//            if (info != null)
//                for (int i = 0; i < info.length; i++)
//                    if (info[i].getState() == NetworkInfo.State.CONNECTED)
//                    {
//                        return true;
//                    }
//
//        }
//        return false;
//    }

//    public static void testNet(String url)
//    {
//            new NetAsynTask().execute(url);
//
//    }
    public static void hideKeyBoard(BaseActivity activity)
    {
//        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        InputMethodManager imm = (InputMethodManager)activity.getSystemService(Context.INPUT_METHOD_SERVICE);

        boolean isOpen=imm.isActive();//isOpen若返回true，则表示输入法打开
        if (isOpen){
            Log.d("OpenOpen","键盘打开");
            try{
                ((InputMethodManager)activity.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }catch (NullPointerException nullE)
            {

            }


        }else
        {
            Log.d("close","键盘关闭");
        }
    }
    public static JSONObject getDivAndModel(int x, int y)
    {
        int div = x/y;
        int model = x%y;
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("div", div);
            jsonObject.put("model", model);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

//下载apk
    /*
    public static void downFile(final String url) {
        pBar.show();
        new Thread() {
            public void run() {
                HttpClient client = new DefaultHttpClient();
                HttpGet get = new HttpGet(url);
                HttpResponse response;
                try {
                    response = client.execute(get);
                    HttpEntity entity = response.getEntity();
                    long length = entity.getContentLength();
                    InputStream is = entity.getContent();
                    FileOutputStream fileOutputStream = null;
                    if (is != null) {
                        File file = new File(
                                Environment.getExternalStorageDirectory(),
                                Bitmap.Config.UPDATE_SAVENAME);
                        fileOutputStream = new FileOutputStream(file);
                        byte[] buf = new byte[1024];
                        int ch = -1;
                        int count = 0;
                        while ((ch = is.read(buf)) != -1) {
                            fileOutputStream.write(buf, 0, ch);
                            count += ch;
                            if (length > 0) {
                            }
                        }
                    }
                    fileOutputStream.flush();
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                    down();
                } catch (ClientProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
    */
/**
 * 字体适配
 */
public static int getFontSize(Context context, int textSize) {
    DisplayMetrics dm = new DisplayMetrics();
    WindowManager windowManager = (WindowManager) context
            .getSystemService(Context.WINDOW_SERVICE);
    windowManager.getDefaultDisplay().getMetrics(dm);
    int screenHeight = dm.heightPixels;
    int rate = (int) (textSize * (float) screenHeight / 1280);
    return rate;
}
    public static void openFile(File file, Context context) {
        // TODO Auto-generated method stub
        Log.e("OpenFile", file.getName());
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(android.content.Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(file),
                "application/vnd.android.package-archive");
        context.startActivity(intent);
    }
/**
 * 获取当前的时间，搭配胸痛评估使用
 */
    public static String getCurrentTime()
    {
        Date date=new Date();
        DateFormat format=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String time=format.format(date);
        return time;
    }
//    设置系统时间
//设置系统时间
public static void timeSynchronization(String date) {
    Log.i("nadiee", "timeSynchronization()-date:" + date);
    ArrayList<String> envlist = new ArrayList<String>();
    Map<String, String> env = System.getenv();
    for (String envName : env.keySet()) {
        envlist.add(envName + "=" + env.get(envName));
    }
    String[] envp = (String[]) envlist.toArray(new String[0]);
    String command;
    command = "date -s " + date;
    Log.i("nadiee", "command:" + command);
    try {
        Runtime.getRuntime().exec(new String[] { "su", "-c", command }, envp);
    } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
}
    //把字符串转为日期 2016-04-22T16:54:19.4933169+08:00
    public static String ConverToDate() throws Exception
    {
//        DateFormat df = new SimpleDateFormat("yyyy0MM0dd0HH0mm0ss");
//        DateFormat df = new SimpleDateFormat("EEE0MMM0d'T'0H0m0s0z0y'+'", Locale.ENGLISH);
//        Date date = df.parse(strDate);
//        String time=df.format(date);

        String returnDateString = "2016004022017007023";
//        String[] dates = strDate.split("T");
//        Log.d("dateslength",dates.length + "");
//        String[] ymd = dates[0].split("-");
//        for (int i = 0;i< ymd.length;i++)
//        {
//            returnDateString += ymd[i]+"0";
//        }
//        Log.d("dates00",returnDateString);
//        //2016-04-22T17:07:23.1918934+08:00
//        String[]hmsorigin = dates[1].split(".")[0].split(":");
//        for (int i = 0;i<hmsorigin.length;i ++)
//        {
//            returnDateString += hmsorigin[i];
//        }





        return returnDateString;
    }
    /**
     * 获取当前apk是release还是debug

    public static boolean isApkDebugable(Context context) {
        try {
            ApplicationInfo info= context.getApplicationInfo();
            return (info.flags& ApplicationInfo.FLAG_DEBUGGABLE)!=0;
        } catch (Exception e) {

        }
        return false;
    }
     */

    public static void printJsonLog(Context context,String jsonObject)
    {
        ACache cache = ACache.get(context);
//        String debugString = getStringCache(CacheName.isDebug_String);
        String debugString = cache.getAsString(CacheName.isDebug_String);
        if (debugString.equals("false"))
        {
//            release模式

        }else
        {
//            Log.d(msg_title,msg);
            Logger.json(jsonObject);
        }
    }

    public static void printDebugLog(Context context, String msg_title, String msg)
    {
//        if (Tool.isApkDebugable(context))
//        {
//            Log.d(msg_title, msg);
//        }
        ACache cache = ACache.get(context);
        String debugString = cache.getAsString(CacheName.isDebug_String);
//        String debugString = getStringCache(CacheName.isDebug_String);
        if (debugString.equals("false"))
        {
//            release模式

        }else
        {
            Log.d(msg_title,msg);
        }

    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 对非空值得判断
     * true：非空
     */
    public boolean isNotNull(String string)
    {
        if(string.equals(""))
        {
            return false;
        }
        return true;
    }

    /**
     * 获取uuid
     */
    public static String getOrderIDUUID(){
        UUID uuid = UUID.randomUUID();
        String uniqueId = uuid.toString();
//        Log.d("debug","----->UUID"+uuid);
        return uniqueId;
    }
    //正则匹配
    public static boolean isMatched(String str,String reg) {
        Pattern p = Pattern
                .compile(reg);
        Matcher m = p.matcher(str);
//        System.out.println(m.matches() + "---");
        return m.matches();
    }



}
