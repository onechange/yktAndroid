package com.example.cwang.smartbutler.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.cwang.smartbutler.entity.ExpressData;

import java.util.List;

/**
 * 项目名: SmartButler
 * 包名: com.example.cwang.smartbutler.adapter
 * 文件名: ExpressAdapter
 * 创建者: cwang
 * 创建时间: 2018/8/11 下午5:36
 */

public class ExpressAdapter extends BaseAdapter {


    private Context mContext;
    private List<ExpressData> mList;
    //布局加载器
    private LayoutInflater inflater;

    public ExpressAdapter(Context mContext, List<ExpressData> mList) {
        this.mContext = mContext;
        this.mList = mList;
        //获取系统服务
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }


}
