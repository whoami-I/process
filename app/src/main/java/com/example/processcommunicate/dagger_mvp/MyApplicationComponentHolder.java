package com.example.processcommunicate.dagger_mvp;

public class MyApplicationComponentHolder {
    private static MyApplicationComponent myApplicationComponent;

    public static void setMyApplicationComponent(MyApplicationComponent c) {
        myApplicationComponent = c;
    }

    public static MyApplicationComponent getMyApplicationComponent() {
        return myApplicationComponent;
    }
}
