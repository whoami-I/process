package com.example.processcommunicate.retrofit;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface RxJavaFactory {

    @GET("/")
    Observable<String> getMessage();
}
