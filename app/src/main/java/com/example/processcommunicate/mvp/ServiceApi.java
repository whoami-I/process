package com.example.processcommunicate.mvp;

import android.graphics.Bitmap;


import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ServiceApi {

    @GET("Upload/News/2019-05/22/MrL/1558485831218017364.png")
    Observable<Bitmap> getImage();
}
