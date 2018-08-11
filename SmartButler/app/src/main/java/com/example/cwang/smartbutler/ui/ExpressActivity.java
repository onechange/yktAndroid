package com.example.cwang.smartbutler.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.cwang.smartbutler.R;
import com.example.cwang.smartbutler.entity.ExpressData;
import com.example.cwang.smartbutler.utils.L;
import com.example.cwang.smartbutler.utils.StaticClass;
import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.util.ArrayList;
import java.util.List;

/**
 * 项目名: SmartButler
 * 包名: com.example.cwang.smartbutler.ui
 * 文件名: ExpressActivity
 * 创建者: cwang
 * 创建时间: 2018/8/11 上午9:17
 */

public class ExpressActivity extends BaseActivity implements View.OnClickListener {
    private EditText et_name;
    private EditText et_number;
    private Button btn_search;

    private List<ExpressData> mList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_express);
        initView();
    }

    private void initView() {
    et_name = findViewById(R.id.et_name);
    et_number = findViewById(R.id.et_number);
    btn_search  = findViewById(R.id.btn_search);
    btn_search.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_search:
            String name = et_name.getText().toString().trim();
            String number = et_number.getText().toString().trim();
            String url = "http://v.juhe.cn/exp/index?com=" + name +"&no=" + number + "&key=" + StaticClass.EXPRESS_ID;
            //2.判断是否为空
                if (!TextUtils.isEmpty(name) & !TextUtils.isEmpty(number)){
                    RxVolley.get(url, new HttpCallback() {
                        @Override
                        public void onSuccess(String t) {
                            super.onSuccess(t);
                            L.i("json"+t);
                            //解析json
                            parsingJSON(t);
                        }
                    });
                }else {
                    Toast.makeText(this,"不能为空",Toast.LENGTH_SHORT).show();
                }

        }
    }

    //解析json
    private void parsingJSON(String t) {

            try {
                JSONObject jsonObject = new JSONObject(t);
                JSONObject jsonRequest = jsonObject.getJSONObject("result");
                JSONArray jsonArray = jsonObject.getJSONArray("list");

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject json = (JSONObject) jsonArray.get(i);
                    ExpressData model = new ExpressData();
                    model.setDatetime(json.getString("datetime"));
                    model.setZone(json.getString("zone"));
                    model.setRemark(json.getString("remark"));
                    mList.add(model);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
    }
}
