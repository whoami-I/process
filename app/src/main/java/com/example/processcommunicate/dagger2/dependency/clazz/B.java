package com.example.processcommunicate.dagger2.dependency.clazz;

import com.example.processcommunicate.log.Log;

public class B {
    public B(D d, E e, F f) {
        Log.e("TAG", "create B");
    }

    public void show() {
        Log.e("TAG", "this is B");
    }

}
