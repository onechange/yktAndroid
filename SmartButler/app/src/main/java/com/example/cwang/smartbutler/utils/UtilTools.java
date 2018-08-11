package com.example.cwang.smartbutler.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.util.Base64;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

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

    public static void putImgToShere(Context mContext, ImageView image_view){
        BitmapDrawable drawable = (BitmapDrawable) image_view.getDrawable();
        Bitmap bitmap = drawable.getBitmap();
//        将bitmap压缩成字节数组输出流
        ByteArrayOutputStream byStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,80,byStream);
//        利用base64将字节数组输出流转换成string
        byte[] byteArray = byStream.toByteArray();
        String imgString = new String(Base64.encodeToString(byteArray,Base64.DEFAULT));

        ShereUtils.putString(mContext,"image_title",imgString );
    }
    public static void getImgFromShere(Context mContext, ImageView image_view){
        String imgString = ShereUtils.getString(mContext,"image_title","");
        if (!imgString.equals("")){
            byte[] byteArray = Base64.decode(imgString,Base64.DEFAULT);
            ByteArrayInputStream inStream = new ByteArrayInputStream(byteArray);
            Bitmap bitmap =  BitmapFactory.decodeStream(inStream);
            image_view.setImageBitmap(bitmap);
        }
    }
}
