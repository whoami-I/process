package com.example.processcommunicate.mvp;

import android.graphics.Bitmap;

public interface BitmapView {

    void onSuccess(Bitmap bitmap);

    void onError();

    void onLoading();
}
