package com.example.cwang.smartbutler.application;

import android.app.Application;

import com.example.cwang.smartbutler.utils.StaticClass;

import cn.bmob.v3.Bmob;

/**
 * 项目名: ${PROGECT_NAME}
 * 包名: com.example.cwang.smartbutler.application
 * 文件名: BaseApplication
 * 创建者: cwang
 * 创建时间: 2018/7/10 11:20 AM
 */

public class BaseApplication extends Application{
    //创建
    @Override
    public void onCreate() {

        super.onCreate();

        Bmob.initialize(this, StaticClass.APPLICATION_ID);

    }
}

