package com.example.processcommunicate.interceptor;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class MyCacheInterceptor implements Interceptor {
    private final static String TAG = "MyCacheInterceptor";

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request userRequest = chain.request();
        Request request = userRequest;
        //对request进行处理


        Response response = chain.proceed(request);
        //对response进行处理
        Log.e(TAG, "MyCacheInterceptor");
        Response response1 = response.newBuilder().
                removeHeader("Cache-Control")
                .removeHeader("Pragma")
                .header("Cache-Control",
                        "public, max-age="+30)
                .build();
        return response1;
    }
}
