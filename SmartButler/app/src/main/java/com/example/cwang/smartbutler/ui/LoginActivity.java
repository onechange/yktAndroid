package com.example.cwang.smartbutler.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.example.cwang.smartbutler.R;

/**
 * 项目名: SmartButler
 * 包名: com.example.cwang.smartbutler.ui
 * 文件名: LoginActivity
 * 创建者: cwang
 * 创建时间: 2018/7/24 4:55 PM
 */

public class LoginActivity extends AppCompatActivity {
    private Button registerBtn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        
        initView();
    }

    private void initView() {

    }
}
