package com.example.processcommunicate.myrxjava;

import io.reactivex.annotations.NonNull;

public interface Emitter<T> {

    void onNext(T value);


    void onError(Throwable error);


    void onComplete();
}
