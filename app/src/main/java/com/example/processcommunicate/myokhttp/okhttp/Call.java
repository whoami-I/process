package com.example.processcommunicate.myokhttp.okhttp;



public interface Call {
    Request request();


    Response execute() ;


    void enqueue(Callback responseCallback);
}
