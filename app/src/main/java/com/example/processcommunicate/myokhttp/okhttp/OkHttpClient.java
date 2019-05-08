package com.example.processcommunicate.myokhttp.okhttp;

public class OkHttpClient {
    Dispatcher dispatcher;
    public OkHttpClient(){
        dispatcher=new Dispatcher();
    }
    public Call newCall(Request request) {
        return RealCall.newCall(this,request);
    }

    public Dispatcher getDispatcher(){
        return dispatcher;
    }
}
