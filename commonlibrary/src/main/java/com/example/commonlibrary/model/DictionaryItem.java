package com.example.commonlibrary.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by xuqiwei on 16-6-27.
 */
public class DictionaryItem extends CommonModel {
    private String dicID;
    private String dicName;

    public DictionaryItem(String dicID, String dicName) {
        this.dicID = dicID;
        this.dicName = dicName;
    }

    public String getDicID() {
        return dicID;
    }

    public void setDicID(String dicID) {
        this.dicID = dicID;
    }

    public String getDicName() {
        return dicName;
    }

    public void setDicName(String dicName) {
        this.dicName = dicName;
    }
//将 jsonobject转换为DictionaryItem
    public static DictionaryItem convertToDictionaryItem(JSONObject jsonObject,String itemIDKey,String itemNameKey)
    {
        String dID = "";
        String dName = "";
        try {
            dID = jsonObject.getString(itemIDKey);
            dName = jsonObject.getString(itemNameKey);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new DictionaryItem(dID,dName);
    }
}
