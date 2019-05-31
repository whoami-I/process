package com.example.processcommunicate.myclassloader;

import com.example.processcommunicate.log.Log;

public class A implements IA {

    @Override
    public void show() {
        Log.e("TAG", "this is normal A");
    }
}
