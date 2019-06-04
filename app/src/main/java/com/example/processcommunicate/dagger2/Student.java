package com.example.processcommunicate.dagger2;

import com.example.processcommunicate.log.Log;

import javax.inject.Inject;


public class Student {
    private static final String TAG = "Student";
    @Inject
    public Student() {
    }

    public void show() {
        Log.e(TAG, "I am student");
    }
}
