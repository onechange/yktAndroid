/**
 * 项目名: SmartButler
 * 包名: com.example.cwang.smartbutler.fragment
 * 文件名: ButlerFragment
 * 创建者: cwang
 * 创建时间: 2018/7/13 5:04 PM
 */

package com.example.cwang.smartbutler.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.example.cwang.smartbutler.R;
import com.example.cwang.smartbutler.adapter.ChatlistAdapter;
import com.example.cwang.smartbutler.entity.Chatlist;

import java.util.ArrayList;
import java.util.List;


public class ButlerFragment extends Fragment implements View.OnClickListener {

    private ListView listView;
    private Button btn_left, btn_right;
    private List<Chatlist> mList = new ArrayList<Chatlist>();
    private ChatlistAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_butler,null);
        findView(view);
        return view;
    }

    private void findView(View view) {
        listView = view.findViewById(R.id.wechatListView);
        btn_left = view.findViewById(R.id.btn_left);
        btn_left.setOnClickListener(this);
        btn_right = view.findViewById(R.id.btn_right);
        btn_right.setOnClickListener(this);

        adapter = new ChatlistAdapter(getActivity(),mList);
        listView.setAdapter(adapter);
        addLeftItem("你好主人");

    }

    private void addLeftItem(String text) {
        Chatlist model = new Chatlist();
        model.setType(ChatlistAdapter.VALUE_LEFT_TEXT);
        model.setText(text);
        mList.add(model);
        //刷新adapter
        adapter.notifyDataSetChanged();
        //滚动到底部
        listView.setSelection(listView.getBottom());
    }
    private void addRightItem(String text) {
        Chatlist model = new Chatlist();
        model.setType(ChatlistAdapter.VALUE_RIGHT_TEXT);
        model.setText(text);
        mList.add(model);
        //刷新adapter
        adapter.notifyDataSetChanged();
        //滚动到底部
        listView.setSelection(listView.getBottom());
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_left:
                addLeftItem("左边");
                break;
            case R.id.btn_right:
                addRightItem("右边");
                break;
        }
    }
}
