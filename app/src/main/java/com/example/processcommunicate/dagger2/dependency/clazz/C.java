package com.example.processcommunicate.dagger2.dependency.clazz;

import com.example.processcommunicate.log.Log;

public class C {
    public C(B b, E e) {

    }

    public void show() {
        Log.e("TAG", "this is C");
    }
}
