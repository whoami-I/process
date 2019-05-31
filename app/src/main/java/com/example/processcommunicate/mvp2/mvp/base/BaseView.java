package com.example.processcommunicate.mvp2.mvp.base;

public interface BaseView<T> {

    void onSuccess(T data);

    void onError();

    void onLoading();
}
