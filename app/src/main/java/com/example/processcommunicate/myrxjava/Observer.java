package com.example.processcommunicate.myrxjava;

public interface Observer<T> {

    void onSubscribe();

    void onNext(T t);

    void onError();

    void onComplete();
}
