package com.example.processcommunicate.dagger2.scope;

import com.example.processcommunicate.log.Log;

import javax.inject.Inject;

public class Student {
    private static final String TAG = "Student";

    @Inject
    public Student() {
        Log.e(TAG, "new Student");
    }

    public void show() {
        Log.e(TAG, "I am student -");
    }
}
