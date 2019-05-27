package com.example.processcommunicate.plugin;

import android.app.Application;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        HookUtils hookUtils = new HookUtils(
                ProxyActivity.class, this);
        try {
            hookUtils.hookStartActivity2();
            hookUtils.hookHandlerLaunchActivity();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
