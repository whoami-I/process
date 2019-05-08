package com.example.processcommunicate.myokhttp.okhttp;

import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.net.ssl.HttpsURLConnection;

public class RealCall implements Call {
    Request originalRequest;
    OkHttpClient client;

    public RealCall(OkHttpClient okHttpClient, Request request) {
        this.client = okHttpClient;
        this.originalRequest = request;
    }

    public static Call newCall(OkHttpClient okHttpClient, Request request) {

        RealCall call = new RealCall(okHttpClient, request);
        return call;
    }

    @Override
    public Request request() {
        return originalRequest;
    }

    @Override
    public Response execute() {
        return null;
    }

    @Override
    public void enqueue(Callback responseCallback) {
        //异步执行
        client.getDispatcher().enqueue(new AsyncCall(responseCallback));
    }

    public class AsyncCall extends NameRunnable {

        Callback callback;

        public AsyncCall(Callback callback) {
            this.callback = callback;
        }

        @Override
        public void execute() {
            Log.e("TAG", "execute");
            Request request = originalRequest;
            try {
                URL url = new URL(request.url);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                if (connection instanceof HttpsURLConnection) {
                    connection = (HttpsURLConnection) connection;
                }
                connection.setRequestMethod(request.method.name);
                connection.setDoOutput(request.method.doOutput());

                RequestBody requestBody = request.requestBody;
                if (requestBody != null) {
                    connection.setRequestProperty("Content-Type", requestBody.getContentType());
                    connection.setRequestProperty("Content-Length",
                            Long.toString(requestBody.getContentLenght()));
                }

                connection.connect();
                if (requestBody != null) {
                    requestBody.onWriteBody(connection.getOutputStream());
                }
                int statusCode = connection.getResponseCode();
                if (statusCode == 200) {
                    Log.e("TAG", "connect success");
                    InputStream is =
                            connection.getInputStream();
                    Response response = new Response(is);
                    callback.onResponse(RealCall.this, response);

                } else {
                    Log.e("TAG", "connect fail----statusCode:" + statusCode);
                }


            } catch (IOException e) {
                e.printStackTrace();
                callback.onFailure(RealCall.this, e);
            }
        }
    }
}
