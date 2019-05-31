package com.example.processcommunicate.mvp.base;

import android.graphics.Bitmap;

public interface BaseView<T> {

    void onSuccess(T data);

    void onError();

    void onLoading();
}
