package com.example.processcommunicate.myokhttp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.processcommunicate.R;
import com.example.processcommunicate.myokhttp.okhttp.Call;
import com.example.processcommunicate.myokhttp.okhttp.Callback;
import com.example.processcommunicate.myokhttp.okhttp.OkHttpClient;
import com.example.processcommunicate.myokhttp.okhttp.Request;
import com.example.processcommunicate.myokhttp.okhttp.Response;
import com.example.processcommunicate.myokhttp.okhttp.RequestBody;

import java.io.IOException;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_okhttp);

        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = new RequestBody().type(RequestBody.FORM)
                .addparams("pageNo", 1 + "")
                .addparams("platform", "android")
                .addparams("pageSize", 1 + "");
        //url("http://api.saiwuquan.com/api/appv2/sceneModel")
        Request request = new Request.Builder().url("https://api.saiwuquan.com/api/appv2/sceneModel")
                .post(requestBody).build();
//        Request request = new Request.Builder().url("https://www.baidu.com")
//                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("TAG", "onFailure");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e("TAG", "onResponse");
                Log.e("TAG", response.string());
            }
        });
    }


}
