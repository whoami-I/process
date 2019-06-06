package com.example.processcommunicate.dagger2.scope;

public class Holder {
    private static StudentComponent mStudentComponent;

    public static StudentComponent getStudentComponent() {
        return mStudentComponent;
    }

    public static void  setStudentComponent(StudentComponent studentComponent) {
        mStudentComponent = studentComponent;
    }
}
