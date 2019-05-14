package com.example.processcommunicate.myrxjava;

public interface ObservableSource<T> {

    void subscribe(Observer<T> observer);
}
