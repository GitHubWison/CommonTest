package com.example.commonlibrary.tools;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.util.Log;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;


/**
 * Created by xuqiwei-Office on 2016/5/30.
 */
public class Utils {
    public static void saveCrashInfoToSDCard(Context context, Throwable throwable) {
        StringBuilder crashInfoStringBuilder=new StringBuilder();

        //获取Crash时间
        String crashTime=getCrashTime();
        crashInfoStringBuilder.append("*******************"+"\n");
        crashInfoStringBuilder.append(crashTime+"\n");
        crashInfoStringBuilder.append("*******************"+"\n");

        //获取Crash时设备及该App的基本信息
        HashMap<String, String> hashMap=getBaseInfo(context);
        for(Map.Entry<String, String> entry:hashMap.entrySet()){
            String key=entry.getKey();
            String value=entry.getValue();
            crashInfoStringBuilder.append(key).append("=").append(value).append("\n");
        }
        crashInfoStringBuilder.append("*******************"+"\n");

        //获取导致Crash的时间
        String uncaughtException=getUncaughtException(throwable);
        crashInfoStringBuilder.append(uncaughtException+"\n");
        crashInfoStringBuilder.append("*******************"+"\n");
//        保存到sd卡中
//        Log.d("内置存储卡路径",Utils.neizhiSD());

        ACache mCache = ACache.get(context);
        mCache.put("WrongMessage", crashInfoStringBuilder.toString());

        try {
            Utils.saveToSDCard("medicalsyslog.txt",crashInfoStringBuilder.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("crashInfo如下:"+"\n"+crashInfoStringBuilder.toString());

    }
    private static void printSuccess()
    {
        Log.d("printSuccess","上传错误日志成功");
    }
    private static void printFail()
    {
        Log.d("printSuccess","上传错误日志失败");
    }

    /**
     * 向sdcard中写入文件
     * @param filename 文件名
     * @param content 文件内容
     */
    public static void saveToSDCard(String filename,String content) throws Exception{
//        File pathfile = new File(Environment.getExternalStorageDirectory().getPath());
        File file=new File(Environment.getExternalStorageDirectory(), filename);
//        保存到临时文件中

//        if (!file.exists())
//        {
//            pathfile.mkdir();
//        }
        OutputStream out=new FileOutputStream(file);
        out.write(content.getBytes());
        out.close();
    }
    public static String neizhiSD()
    {
        return Environment.getExternalStorageDirectory().getPath();
    }
    /**
     * 获取设备及该App的基本信息
     */
    public static HashMap<String, String> getBaseInfo(Context context){
        HashMap<String, String> hashMap = new HashMap<String, String>();
        PackageManager packageManager = context.getPackageManager();
        PackageInfo packageInfo = null;
        try {
            packageInfo = packageManager.getPackageInfo(context.getPackageName(), PackageManager.GET_ACTIVITIES);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        hashMap.put("versionName", packageInfo.versionName);
        hashMap.put("versionCode", packageInfo.versionCode+"");

        hashMap.put("MODEL",  Build.MODEL+"");
        hashMap.put("SDK_INT",Build.VERSION.SDK_INT+"");
        hashMap.put("RELEASE",Build.VERSION.RELEASE+"");
        hashMap.put("PRODUCT",Build.PRODUCT+"");


        return hashMap;
    }
    /**
     * 获取造成Crash的异常的具体信息
     */
    public static String getUncaughtException(Throwable throwable){
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        throwable.printStackTrace(printWriter);
        printWriter.close();
        String uncaughtException=stringWriter.toString();
        return uncaughtException;

    }

    /**
     * 获取Crash的时间
     */
    public static String getCrashTime(){
        String currentTime="";
        long currentTimeMillis=System.currentTimeMillis();
        System.setProperty("user.timezone", "Asia/Shanghai");
        TimeZone timeZone = TimeZone.getTimeZone("Asia/Shanghai");
        TimeZone.setDefault(timeZone);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        Date currentDate=new Date(currentTimeMillis);
        currentTime = simpleDateFormat.format(currentDate);
        return currentTime;
    }
}
