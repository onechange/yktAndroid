package com.example.cwang.smartbutler.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 项目名: SmartButler
 * 包名: com.example.cwang.smartbutler.utils
 * 文件名: ShereUtils
 * 创建者: cwang
 * 创建时间: 2018/7/15 8:03 PM
 */

public class ShereUtils {

    public static final String NAME = "config";

    public static void deleShare(Context mContext,String key){
        SharedPreferences sp = mContext.getSharedPreferences(NAME,Context.MODE_PRIVATE);
        sp.edit().remove(key).commit();
    }
    public static void putString(Context mContext,String key,String value){

        SharedPreferences sp = mContext.getSharedPreferences(NAME,Context.MODE_PRIVATE);

        sp.edit().putString(key,value).commit();
    }

    public static String getString(Context mContext,String key,String defValue){

        SharedPreferences sp = mContext.getSharedPreferences(NAME,Context.MODE_PRIVATE);

        return sp.getString(key,defValue);
    }

    public static void putBool(Context mContext,String key,boolean value){
        SharedPreferences sp = mContext.getSharedPreferences(NAME,Context.MODE_PRIVATE);

        sp.edit().putBoolean(key,value).commit();
    }
    public static boolean getBool(Context mContext,String key,boolean defValue){
        SharedPreferences sp = mContext.getSharedPreferences(NAME,Context.MODE_PRIVATE);
        return sp.getBoolean(key,defValue);
    }
}


