package com.example.processcommunicate.dagger2.studentmodule;

import com.example.processcommunicate.log.Log;

import javax.inject.Inject;

public class Student {
    private static final String TAG = "Student";

    public Student(int a) {
    }
    public Student() {
    }


    public void show() {
        Log.e(TAG, "I am student -");
    }
}
