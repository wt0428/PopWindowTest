package com.popwindow.util;


import android.content.Context;
import android.util.DisplayMetrics;

import com.popwindow.MyApplication;

public class LUtils {

    private LUtils() {
    }


    /**
     * <p>
     * 功能 获取屏幕宽度
     * </p>
     *
     * @param context
     * @return 屏幕宽度 单位：像素
     */
    public static int getWidthPixels(Context context) {
        if (MyApplication.screenWidth <= 0) {
            initWindowSize(context);
        }
        return MyApplication.screenWidth;
    }

    /**
     * <p>
     * 功能 获取屏幕高度
     * </p>
     *
     * @param context
     * @return 屏幕高度 单位：像素
     */
    public static int getHeightPixels(Context context) {
        if (MyApplication.screenHeight <= 0) {
            initWindowSize(context);
        }
        return MyApplication.screenHeight;
    }

    /**
     * <p>
     * 功能 初始化屏幕大小数据
     * </p>
     *
     * @param context
     */
    private static void initWindowSize(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        MyApplication.screenWidth = dm.widthPixels;
        MyApplication.screenHeight = dm.heightPixels;
    }





}
