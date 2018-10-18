package com.xtensolution.core;

import android.app.Application;

import com.xtensolution.core.utils.ActivityLifecycle;

public class CoreApp extends Application {

    private static CoreApp instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        ActivityLifecycle.init(instance);
    }

    public static synchronized CoreApp getInstance() {
        return instance;
    }

}