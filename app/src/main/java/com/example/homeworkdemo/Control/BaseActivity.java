package com.example.homeworkdemo.Control;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.chad.library.adapter.base.module.LoadMoreModuleConfig;
import com.example.homeworkdemo.widgets.CustomLoadMoreView;
import com.tataera.base.ETApplication;

import butterknife.ButterKnife;

/*
 *   Created by Dullyoung on 2020/10/20 0020
 */
public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        ETApplication.init(this);
        LoadMoreModuleConfig.setDefLoadMoreView(new CustomLoadMoreView());
        initViews();
    }

    protected abstract int getLayoutId();

    protected abstract void initViews();
}
