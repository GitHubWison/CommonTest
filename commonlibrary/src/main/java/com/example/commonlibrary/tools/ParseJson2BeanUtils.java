package com.example.commonlibrary.tools;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.Field;

import org.json.JSONObject;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by xuqiwei on 16-6-23.
 */
public class ParseJson2BeanUtils {
    public static <T> T parseJson2Bean(JSONObject object,Class<T> clazz ) throws Exception{
//        FileInputStream fis = new FileInputStream("/mnt/sdcard/json.txt");
//        int len = fis.available();
//        byte[] buffer = new byte[len];
//        fis.read(buffer);
//        object = new JSONObject(new String(buffer));
        T newInstance = null;
        Field[] fields = clazz.getFields();
        //只要保证clazz有一个无参的public构造方法就不可能发生异常
        try {
            newInstance = clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for(int i = 0;i< fields.length;i++){
            String name = fields[i].getName();
            //不可能发生的异常
            try {

                String jsonName = name.replaceFirst(name.substring(0, 1), name.substring(0, 1).toUpperCase());
                if(object.has(jsonName)){
                    if(jsonName.equals("ResultCode")){
                        fields[i].set(newInstance, object.getInt(jsonName));
                    }else{
                        fields[i].set(newInstance, object.getString(jsonName));
                    }
                    Log.i("name", name+"****");

                }
                //Log.i("name", jsonName+":"+object.has(jsonName)+object.getString(jsonName));

            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return newInstance;
    }
}
