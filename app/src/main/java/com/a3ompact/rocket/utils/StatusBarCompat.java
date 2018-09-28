package com.a3ompact.rocket.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;

/**
 * Created by 3ompact on 2018-9-28.
 *
 * 本类用于实现沉浸式状态栏
 */
public class StatusBarCompat {

    private static final int INVALID_VAL = -1;
    //默认为透明色
    private static final int COLOR_DEFAULT = Color.parseColor("#20000000");

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static void compat(Activity activity , int statusColor){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            if(statusColor != INVALID_VAL){
                activity.getWindow().setStatusBarColor(statusColor);
            }
            return;
        }


        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP){
            int color = COLOR_DEFAULT;
            ViewGroup contentView = activity.findViewById(android.R.id.content);
            if(statusColor != INVALID_VAL){
                color = statusColor;
            }
            View statusBarView = new View(activity);
            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    getStatusBarHeight(activity));
            statusBarView.setBackgroundColor(color);
            contentView.addView(statusBarView,lp);
        }

    }

    public static void compat(Activity activity){
        compat(activity,INVALID_VAL);
    }

    //测量状态栏高度
    public static int getStatusBarHeight(Context context){
        int result = 0 ;
        int resourceId = context.getResources().getIdentifier("status_bar_height",
                "dimen","android");
        if(resourceId > 0){
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }


    // 当背景为图片的时候
    public static void setTranslucent(Activity activity){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            //设置状态栏透明
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //设置根部局
            ViewGroup rootView = (ViewGroup) ((ViewGroup) activity.findViewById(android.R.id.content)).getChildAt(0);
            rootView.setFitsSystemWindows(true);
            rootView.setClipToPadding(true);
        }
    }


    //当布局为DrawerLayout时的适配
    public static void setColorForDrawerLayout(Activity activity, DrawerLayout drawerLayout, int color){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //生成一个状态栏大小的view
            View statusBarView = createStatusView(activity,color);
            //把statusBarView添加到布局中
            ViewGroup contentView = (ViewGroup) drawerLayout.getChildAt(0);
            contentView.addView(statusBarView,0);
            //内容布局不是LinearLayout的时候需要设置top padding
            if( !(contentView instanceof LinearLayout) && contentView.getChildAt(1) != null ){
                contentView.getChildAt(0).setPadding(0,getStatusBarHeight(activity),0,0);
            }
            //设置属性
            ViewGroup drawer = (ViewGroup) drawerLayout.getChildAt(1);
            drawerLayout.setFitsSystemWindows(false);
            contentView.setFitsSystemWindows(false);
            contentView.setClipToPadding(true);
            drawer.setFitsSystemWindows(false);
        }

    }

    //生成一个和状态栏大小相同的矩形条
    private static View createStatusView(Activity activity , int color){
        int resourceId = activity.getResources().getIdentifier("status_bar_height",
                "dimen","android");
        int statusBarHeight = activity.getResources().getDimensionPixelSize(resourceId);
        View statusView = new View (activity);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT
        ,statusBarHeight);
        statusView.setLayoutParams(lp);
        statusView.setBackgroundColor(color);
        return statusView;
    }

}
