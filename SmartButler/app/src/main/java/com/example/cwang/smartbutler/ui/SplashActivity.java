package com.example.cwang.smartbutler.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.cwang.smartbutler.MainActivity;
import com.example.cwang.smartbutler.R;
import com.example.cwang.smartbutler.utils.ShereUtils;
import com.example.cwang.smartbutler.utils.StaticClass;

/**
 * 项目名: SmartButler
 * 包名: com.example.cwang.smartbutler.ui
 * 文件名: SplashActivity
 * 创建者: cwang
 * 创建时间: 2018/7/16 9:51 AM
 */

public class SplashActivity extends AppCompatActivity {
    /*
    * 1.延时
    * 2.判断程序是否第一次运行
    * */
    private TextView tv_splash;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case StaticClass.HANDLER_SPLASH:
                    /*判断是否第一次运行*/
                    if (isFirst()){
                        startActivity(new Intent(SplashActivity.this,GuideActivity.class));

                    }else {
                        startActivity(new Intent(SplashActivity.this,MainActivity.class));

                    }
                    finish();
                    break;
            }
        }

    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spalash);

        initView();
    }

    //初始化
    private void initView() {
        handler.sendEmptyMessageDelayed(StaticClass.HANDLER_SPLASH,2000);
        tv_splash = findViewById(R.id.tv_splash);

    }

    private boolean isFirst() {
       boolean isFirst = ShereUtils.getBool(this,StaticClass.SHARE_IS_FIRST,true);
       if (isFirst){
           ShereUtils.putBool(this,StaticClass.SHARE_IS_FIRST,false);
           return true;
       }else {
           return false;
       }

    }
}
