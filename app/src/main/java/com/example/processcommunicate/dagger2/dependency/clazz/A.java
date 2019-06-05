package com.example.processcommunicate.dagger2.dependency.clazz;

import com.example.processcommunicate.log.Log;

public class A {
    public A(B b, C c, F f) {

    }
    public void show(){
        Log.e("TAG","this is A");
    }
}
