package com.example.cwang.smartbutler.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.cwang.smartbutler.R;
import com.example.cwang.smartbutler.entity.Chatlist;

import java.util.List;

/**
 * 项目名: SmartButler
 * 包名: com.example.cwang.smartbutler.adapter
 * 文件名: ChatlistAdapter
 * 创建者: cwang
 * 创建时间: 2018/8/17 上午10:56
 */

public class ChatlistAdapter extends BaseAdapter {

    public static final int VALUE_LEFT_TEXT = 0;
    public static final int VALUE_RIGHT_TEXT = 1;

    private Context mContext;
    private LayoutInflater inflater;
    private Chatlist chatlistModel;
    private List<Chatlist> mList;


    public ChatlistAdapter(Context mContext, List<Chatlist> mList) {

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
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolderLeftText viewHolderLeftText = null;
        ViewHolderRightText viewHolderRightText = null;
        //获取当前要显示的type
        int type = getItemViewType(i);
        if (view == null){
            switch (type){
                case VALUE_LEFT_TEXT:
                    viewHolderLeftText = new ViewHolderLeftText();
                    view = inflater.inflate(R.layout.left_item,null);
                    viewHolderLeftText.left_tv = view.findViewById(R.id.tv_left_text);
                    view.setTag(viewHolderLeftText);
                    break;
                case VALUE_RIGHT_TEXT:
                    viewHolderRightText = new ViewHolderRightText();
                    view = inflater.inflate(R.layout.right_item,null);
                    viewHolderRightText.right_tv = view.findViewById(R.id.tv_right_text);
                    view.setTag(viewHolderRightText);
                    break;
            }
        }else {
            switch (type){
                case VALUE_LEFT_TEXT:
                    viewHolderLeftText = (ViewHolderLeftText) view.getTag();
                    break;
                case VALUE_RIGHT_TEXT:
                    viewHolderRightText = (ViewHolderRightText) view.getTag();
                    break;
            }
        }
        //赋值
        Chatlist model = mList.get(i);
        switch (type){
            case VALUE_LEFT_TEXT:
                viewHolderLeftText.left_tv.setText(model.getText());
                break;
            case VALUE_RIGHT_TEXT:
                viewHolderRightText.right_tv.setText(model.getText());
                break;
        }
        return view;
    }

    @Override
    public int getItemViewType(int position) {
        Chatlist model = mList.get(position);
        int type = model.getType();
        return type;
    }

    //返回所有的layout数据

    @Override
    public int getViewTypeCount() {

        return 3;
    }
    //左边文本
    class ViewHolderLeftText{
        private TextView left_tv;

    }

    //右边文本
    class ViewHolderRightText{
        private TextView right_tv;
    }

}
