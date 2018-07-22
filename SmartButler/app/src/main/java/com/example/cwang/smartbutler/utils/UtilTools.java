package com.example.cwang.smartbutler.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;

/**
 * 项目名: SmartButler
 * 包名: com.example.cwang.smartbutler.utils
 * 文件名: UtilTools
 * 创建者: cwang
 * 创建时间: 2018/7/12 10:05 AM
 * 统一工具类
 */

public class UtilTools {
    public static void setFont(Context mContext , TextView mTextView){
        Typeface fontType =  Typeface.createFromAsset(mContext.getAssets(),"fonts/LingWaiSC-Medium.otf");
        mTextView.setTypeface(fontType);
    }
}
