/**
 * 项目名: SmartButler
 * 包名: com.example.cwang.smartbutler.fragment
 * 文件名: ButlerFragment
 * 创建者: cwang
 * 创建时间: 2018/7/13 5:04 PM
 */

package com.example.cwang.smartbutler.fragment;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.cwang.smartbutler.R;
import com.example.cwang.smartbutler.adapter.WeChatAdapter;
import com.example.cwang.smartbutler.entity.WeChatData;
import com.example.cwang.smartbutler.ui.WebViewActivity;
import com.example.cwang.smartbutler.utils.L;
import com.example.cwang.smartbutler.utils.StaticClass;
import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;



public class WeChatFragment extends Fragment {

    private ListView mListView;
    private List<WeChatData> mList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.wechat_fragment,null);

        findView(view);
        return view;
    }

    private void findView(View view) {
        mListView = view.findViewById(R.id.mWechatListView);

        //解析接口
        String url = "http://v.juhe.cn/weixin/query?key=" + StaticClass.WECAHT_ID ;
        RxVolley.get(url, new HttpCallback() {
            @Override
            public void onSuccess(String t) {
                L.i("微信精选:" + t);
                Toast.makeText(getActivity(),t,Toast.LENGTH_SHORT).show();
                parsingJson(t);
            }
        });

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), WebViewActivity.class);
                intent.putExtra("title",mList.get(i).getTitle());
                intent.putExtra("url",mList.get(i).getUrl());
                startActivity(intent);
            }
        });
    }

    private void parsingJson(String t) {

        try {
            JSONObject jsonObject = new JSONObject(t);
            JSONObject jsonResult = jsonObject.getJSONObject("result");
            JSONArray jsonList = jsonResult.getJSONArray("list");

            for (int i=0; i<jsonList.length();i++){
                JSONObject json = (JSONObject) jsonList.get(i);
                WeChatData model = new WeChatData();
                String title = json.getString("title");
                String url = json.getString("url");

                model.setImgUrl(json.getString("firstImg"));
                model.setTitle(title);
                model.setSource(json.getString("source"));
                model.setUrl(json.getString("url"));
                mList.add(model);

            }
            WeChatAdapter adapter = new WeChatAdapter(getActivity(),mList);
            mListView.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
