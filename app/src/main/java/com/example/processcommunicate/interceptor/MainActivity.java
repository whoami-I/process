package com.example.processcommunicate.interceptor;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;


import com.example.processcommunicate.R;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;


public class MainActivity extends Activity {
    private static final String TAG = "MainActivity";
    OkHttpClient okHttpClient;
    Request request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_interceptor);
        File file = new File(Environment.getExternalStorageDirectory() +
                File.separator + "1cache");
        Cache cache = new Cache(file, 10 * 1024 * 1024);


        okHttpClient = new OkHttpClient.Builder()
                .cache(cache)
                .addNetworkInterceptor(new MyCacheInterceptor())
                .build();

        request = new Request.Builder().url("https://www.baidu.com").build();


    }

    public String string(InputStream is) {
        InputStream input = is;
        StringBuffer out = new StringBuffer();
        byte[] b = new byte[4096];
        try {
            for (int n; (n = input.read(b)) != -1; ) {
                out.append(new String(b, 0, n));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public void click(View view) {
        call();
    }

    private void call() {
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e(TAG, "onResponse");
//                InputStream inputStream =
//                        response.body().byteStream();
//                Log.e(TAG, "content:" + string(inputStream));
                Headers headers =
                        response.headers();
                String cache = headers.get("Cache-Control");
//                String connection=headers.get("Connection");
//                String Content_Type=headers.get("Content-Type");
//                String Content_Encoding=headers.get("Content-Encoding");
//                Log.e(TAG, "Cache-Control:" + cache);
//                Log.e(TAG, "Connection:" + connection);
//                Log.e(TAG, "Content-Type:" + Content_Type);
//                Log.e(TAG, "Content-Encoding:" + Content_Encoding);

                Log.e("TAG", response.cacheResponse() + " ; " + response.networkResponse());
                Log.e(TAG, "cache--:" + cache);
                //这里有坑，可能DiskLruCache没有写进去
                ResponseBody body = response.body();
                //body.close();
                //body.close();
            }
        });

    }


}
