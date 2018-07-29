package com.example.cwang.smartbutler.entity;

import cn.bmob.v3.BmobUser;

/**
 * 项目名: SmartButler
 * 包名: com.example.cwang.smartbutler.entity
 * 文件名: MyUser
 * 创建者: cwang
 * 创建时间: 2018/7/25 10:51 AM
 */

public class MyUser extends BmobUser{

    private int age; //年龄
    private boolean gender; //性别
    private String desc; //简介

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }


}
