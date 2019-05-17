package com.example.processcommunicate.okhttp;

import android.content.Context;

import java.io.IOException;
import java.util.Map;

import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okio.BufferedSink;

class OkHttpEngine implements IHttpEngine {

    private OkHttpClient mokHttpClient = new OkHttpClient();

    @Override
    public void post(Context context, String url, Map<String, Object> params, Callback callback, boolean cache) {
        RequestBody requestBody = new RequestBody() {

            @Override
            public MediaType contentType() {
                return null;
            }

            @Override
            public void writeTo(BufferedSink bufferedSink) {

            }
        };
        Request request = new Request.Builder().
                url(url).post(requestBody).
                tag(context).build();
        mokHttpClient.newCall(request).enqueue(callback);

    }

    @Override
    public void get(Context context, String url, Map<String, Object> params, Callback callback, boolean cache) {
        Request request = new Request.Builder().
                url(url).
                tag(context).build();
        mokHttpClient.newCall(request).enqueue(callback);
    }
}
