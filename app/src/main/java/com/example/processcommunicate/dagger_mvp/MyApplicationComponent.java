package com.example.processcommunicate.dagger_mvp;

import android.content.Context;
import android.content.SharedPreferences;

import dagger.Component;

@Component(modules = {MyApplicationModule.class})
public interface MyApplicationComponent {

    SharedPreferences getSharedPreferences();

    Context getContext();
}
