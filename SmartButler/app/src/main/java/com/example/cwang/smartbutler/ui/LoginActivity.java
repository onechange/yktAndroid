package com.example.cwang.smartbutler.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cwang.smartbutler.MainActivity;
import com.example.cwang.smartbutler.R;
import com.example.cwang.smartbutler.entity.MyUser;
import com.example.cwang.smartbutler.utils.ShereUtils;

import org.w3c.dom.Text;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * 项目名: SmartButler
 * 包名: com.example.cwang.smartbutler.ui
 * 文件名: LoginActivity
 * 创建者: cwang
 * 创建时间: 2018/7/24 4:55 PM
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    private Button registerBtn;
    private Button signBtn;
    private EditText username;
    private EditText user_password;
    private CheckBox save_password;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        
        initView();
    }

    private void initView() {

        registerBtn = findViewById(R.id.registerBtn);
        registerBtn.setOnClickListener(this);
        signBtn = findViewById(R.id.signBtn);
        signBtn.setOnClickListener(this);
        username = findViewById(R.id.login_username);
        user_password = findViewById(R.id.login_password);
        save_password = findViewById(R.id.save_password);

        boolean isCheck = ShereUtils.getBool(this,"savepassword",false);
        save_password.setChecked(isCheck);
        if (isCheck){
            username.setText(ShereUtils.getString(this,"username",""));
            user_password.setText(ShereUtils.getString(this,"password",""));
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.registerBtn:
                startActivity(new Intent(this,RegisterActivity.class));

                break;
            case R.id.signBtn:
                //获取输入框的值
                String name = username.getText().toString().trim();
                String password = user_password.getText().toString();
                if (!TextUtils.isEmpty(name) & !TextUtils.isEmpty(password)) {
                    //登录
                    MyUser user = new MyUser();
                    user.setPassword(password);
                    user.setUsername(name);
                    user.login(new SaveListener<MyUser>() {
                        @Override
                        public void done(MyUser myUser, BmobException e) {
                            if (e == null){
                                Toast.makeText(LoginActivity.this,"登录成功!",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                finish();
                            }else {
                                Toast.makeText(LoginActivity.this,"登录失败" + e.toString(),Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else {
                    Toast.makeText(LoginActivity.this,"输入框不为空",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        /*这些逻辑放在登陆成功后比较好*/
        //保存状态
        ShereUtils.putBool(this,"keeppass",save_password.isChecked());
        //是否记住密码
        if (save_password.isChecked()) {
            ShereUtils.putString(this,"username",username.getText().toString().trim());
            ShereUtils.putString(this,"password",user_password.getText().toString().trim());
        }else {
            ShereUtils.deleShare(this,"username");
            ShereUtils.deleShare(this,"password");
        }
    }
}
