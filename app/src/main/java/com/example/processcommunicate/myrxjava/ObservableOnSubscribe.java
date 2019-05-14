package com.example.processcommunicate.myrxjava;

public interface ObservableOnSubscribe<T> {

    void subscribe(Emitter<T> emitter);
}
