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

import com.example.cwang.smartbutler.R;


public class ButlerFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_butler,null);
        return view;
    }
}
