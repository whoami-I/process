package com.example.processcommunicate.myokhttp.okhttp;

public abstract class NameRunnable implements Runnable {
    @Override
    public void run() {
        execute();
    }

    public abstract void execute();
}
