package com.example.processcommunicate.dagger_mvp;

import android.app.Application;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init() {
        MyApplicationComponent myApplicationComponent = DaggerMyApplicationComponent
                .builder()
                .myApplicationModule(new MyApplicationModule(this))
                .build();
        MyApplicationComponentHolder.setMyApplicationComponent(myApplicationComponent);
    }
}
