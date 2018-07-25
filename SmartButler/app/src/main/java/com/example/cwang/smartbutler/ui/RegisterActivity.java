package com.example.cwang.smartbutler.ui;


import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.cwang.smartbutler.R;
import com.example.cwang.smartbutler.entity.MyUser;

public class RegisterActivity extends BaseActivity implements View.OnClickListener{

    private EditText et_user;
    private EditText et_age;
    private EditText et_desc;
    private RadioGroup registerRG;
    private EditText et_password;
    private EditText et_secPassword;
    private EditText et_email;
    private Button btn_register;

    //性别
    private boolean isGender = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        
        initView();
    }

    private void initView() {

        et_user = findViewById(R.id.et_user);
        et_age = findViewById(R.id.et_age);
        et_desc = findViewById(R.id.et_desc);
        registerRG = findViewById(R.id.registerRG);
        et_password = findViewById(R.id.et_password);
        et_secPassword = findViewById(R.id.et_secPassword);
        et_email = findViewById(R.id.et_email);
        btn_register = findViewById(R.id.btn_register);
        btn_register.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_register:
                String name = et_user.getText().toString().trim();
                String age = et_age.getText().toString().trim();
                String desc = et_desc.getText().toString().trim();
                String password = et_password.getText().toString().trim();
                String secPassword = et_secPassword.getText().toString().trim();
                String email = et_email.getText().toString().trim();

                if (!TextUtils.isEmpty(name)
                        && !TextUtils.isEmpty(age)
                        && !TextUtils.isEmpty(password)

                        && !TextUtils.isEmpty(secPassword)
                        && !TextUtils.isEmpty(email)) {
                    //判断性别
                    registerRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(RadioGroup radioGroup, int i) {
                            if (i == R.id.rb_boy) {
                                isGender = true;
                            } else if(i == R.id.rb_girl) {
                                isGender = false;
                            }
                        }
                    });
                    //判断简介是否为空
                    if (!TextUtils.isEmpty(desc)){
                        desc = "这人很懒什么都没留下";
                    }
                    //注册
                    MyUser user = new MyUser();
                    user

            } else {
                    Toast.makeText(this,"输入框不能为空",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
