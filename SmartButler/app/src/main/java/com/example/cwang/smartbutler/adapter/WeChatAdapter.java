package com.example.cwang.smartbutler.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cwang.smartbutler.R;
import com.example.cwang.smartbutler.entity.WeChatData;

import java.util.List;

/**
 * 项目名: SmartButler
 * 包名: com.example.cwang.smartbutler.adapter
 * 文件名: WeChatAdapter
 * 创建者: cwang
 * 创建时间: 2018/8/18 下午3:54
 */

public class WeChatAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater inflater;
    private List<WeChatData> mList;
    private WeChatData model;
    public WeChatAdapter(Context mContext,List mList) {
        this.mContext = mContext;
        this.mList = mList;
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

        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null){
            viewHolder = new ViewHolder();
            view = inflater.inflate(R.layout.wechat_item,null);
            viewHolder.iv_img = view.findViewById(R.id.iv_img);
            viewHolder.tv_source = view.findViewById(R.id.tv_source);
            viewHolder.tv_title = view.findViewById(R.id.title);
            view.setTag(viewHolder);
        }else {

            viewHolder = (ViewHolder) view.getTag();

        }
        model = mList.get(i);
        viewHolder.tv_title.setText(model.getTitle());
        viewHolder.tv_source.setText(model.getSource());

        return view;
        
    }

    class ViewHolder{
        private ImageView iv_img;
        private TextView tv_title;
        private TextView tv_source;

    }
}
