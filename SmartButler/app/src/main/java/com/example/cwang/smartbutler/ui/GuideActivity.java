package com.example.cwang.smartbutler.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.cwang.smartbutler.MainActivity;
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

public class GuideActivity extends AppCompatActivity implements View.OnClickListener {
    private ViewPager guideVP;
    //容器
    private List<View>vpList = new ArrayList<>();
    private View firstView,secView,thridView;
    private ImageView point1,point2,point3,quickJumpImg;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        initView();
    }
    private void initView(){

        quickJumpImg = findViewById(R.id.quickJump);
        quickJumpImg.setOnClickListener(this);
        point1 = findViewById(R.id.point1);
        point2 = findViewById(R.id.point2);
        point3 = findViewById(R.id.point3);
        setPointImg(true,false,false);
        guideVP = findViewById(R.id.guideVP);
        firstView = View.inflate(this,R.layout.pager_item_first,null);
        secView = View.inflate(this, R.layout.pager_item_sec,null);
        thridView = View.inflate(this, R.layout.pager_item_thrid,null);

        thridView.findViewById(R.id.btn_start).setOnClickListener(this);
        vpList.add(firstView);
        vpList.add(secView);
        vpList.add(thridView);

        //设置适配器
        guideVP.setAdapter(new GuideAdapter());

        //监听viewpager滑动
        guideVP.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        quickJumpImg.setVisibility(View.VISIBLE);
                        setPointImg(true,false,false);
                        break;
                    case 1:
                        quickJumpImg.setVisibility(View.VISIBLE);
                        setPointImg(false,true,false);
                        break;
                    case 2:
                        quickJumpImg.setVisibility(View.GONE);
                        setPointImg(false,false,true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_start:
            case R.id.quickJump:
                startActivity(new Intent(this, MainActivity.class));
                finish();
                break;
        }
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

    private void setPointImg(boolean isCheck1,boolean isCheck2,boolean isCheck3){
        if (isCheck1){
            point1.setBackgroundResource(R.drawable.point_on);
        }else {
            point1.setBackgroundResource(R.drawable.point_off);
        }
        if (isCheck2){
            point2.setBackgroundResource(R.drawable.point_on);
        }else {
            point2.setBackgroundResource(R.drawable.point_off);
        }
        if (isCheck3){
            point3.setBackgroundResource(R.drawable.point_on);
        }else {
            point3.setBackgroundResource(R.drawable.point_off);
        }
    }
}
