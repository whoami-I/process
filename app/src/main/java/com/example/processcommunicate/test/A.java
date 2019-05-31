package com.example.processcommunicate.test;

import android.widget.Toast;

import com.example.processcommunicate.log.Log;

public class A implements IA {

    @Override
    public void show() {
        Log.e("TAG", "this is normal A");
    }
}
