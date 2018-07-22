package com.example.cwang.smartbutler.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

import com.example.cwang.smartbutler.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名: SmartButler
 * 包名: com.example.cwang.smartbutler.ui
 * 文件名: GuideActivity
 * 创建者: cwang
 * 创建时间: 2018/7/20 5:04 PM
 */

public class GuideActivity extends AppCompatActivity {
    private ViewPager guideVP;
    //容器
    private List<View>vpList = new ArrayList<>();
    private View firstView,secView,thridView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        initView();
    }
    private void initView(){
        guideVP = findViewById(R.id.guideVP);
        firstView = View.inflate(this,R.layout.pager_item_first,null);
        secView = View.inflate(this, R.layout.pager_item_sec,null);
        thridView = View.inflate(this, R.layout.pager_item_thrid,null);

        vpList.add(firstView);
        vpList.add(secView);
        vpList.add(thridView);

        //设置适配器
        guideVP.setAdapter(new GuideAdapter());

    }

    class GuideAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return vpList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override//添加
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(vpList.get(position));
            return vpList.get(position);
        }

        @Override//删除
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(vpList.get(position));
        }
    }
}
