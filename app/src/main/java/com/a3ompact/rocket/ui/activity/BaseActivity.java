package com.a3ompact.rocket.ui.activity;

import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by 3ompact on 2018-9-27.
 */
public abstract class  BaseActivity  extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setContentLayoutId());
    }



    //设置布局id
    protected abstract int setContentLayoutId();

    //当当前页面需要进行消息提示的时候进行重写
    protected abstract void showDialog();

    //界面初始化
    protected abstract void initView();

    //数据初始化
    protected abstract void initData();

    //当页面出现网络问题等时，对背景进行代替
    protected abstract void setBackground();

}
