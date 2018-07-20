package com.example.cwang.smartbutler.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

/**
 * 项目名: SmartButler
 * 包名: com.example.cwang.smartbutler.ui
 * 文件名: BaseActivity
 * 创建者: cwang
 * 创建时间: 2018/7/10 11:33 AM
 */

/*
* 同一属性
* 统一接口
* 统一方法
*
* */
public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //让返回按钮显示出来
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    //菜单操作
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);

    }
}
