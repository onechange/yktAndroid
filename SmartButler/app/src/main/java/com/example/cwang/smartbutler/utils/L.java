package com.example.cwang.smartbutler.utils;

import android.util.Log;

/**
 * 项目名: SmartButler
 * 包名: com.example.cwang.smartbutler.utils
 * 文件名: L
 * 创建者: cwang
 * 创建时间: 2018/7/16 9:18 AM
 */

public class L {

    //开关
    public static final boolean DEBUG = true;

    //tag
    public static final String TAG = "smartbutler";

    //五个等级  DIWE

    public static void d(String text){
        if (DEBUG) {
            Log.d(TAG,text);
        }
    }

    public static void i(String text){
        if (DEBUG){
            Log.i(TAG,text);
        }
    }

    public static void w(String text){
        if (DEBUG){
            Log.w(TAG,text);
        }
    }

}
