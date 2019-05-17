package com.example.processcommunicate.proxy.dynamicproxy;

import android.util.Log;

public class Person implements IBank {

    private static final String TAG = "Person";
    String name;

    public Person(String name) {
        this.name = name;
    }


    @Override
    public void register() {
        Log.e(TAG, name + " register");
    }

    @Override
    public void getCard() {
        Log.e(TAG, name + " getCard");
    }

    @Override
    public void lost() {
        Log.e(TAG, name + " lost");
    }
}
