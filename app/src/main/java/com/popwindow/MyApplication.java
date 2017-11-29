package com.popwindow;

import android.app.Application;
import android.content.Context;

/**
 * MyApplication
 */

public class MyApplication extends Application {
    private static final String TAG = "MyApplicationDoctor";
    private static MyApplication s_application = null;
    public static int screenWidth = -1;
    public static int screenHeight = -1;
    public static Context mContext;

    public static Context getContext() {
        return mContext;

    }

    public static MyApplication getApplication() {
        return s_application;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        s_application = this;
    }


}
