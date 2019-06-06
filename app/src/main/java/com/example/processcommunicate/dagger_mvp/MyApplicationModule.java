package com.example.processcommunicate.dagger_mvp;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import dagger.Module;
import dagger.Provides;

@Module
public class MyApplicationModule {
    Context mContext;

    public MyApplicationModule(Context context) {
        this.mContext = context;
    }

    /**
     * 提供application的上下文context
     */
    @Provides
    Context provideContext() {
        return mContext;
    }

    @Provides
    SharedPreferences provideSharedPreferences() {
        SharedPreferences sp =
                mContext.getSharedPreferences("file", Context.MODE_PRIVATE);
        return sp;
    }
}
