package com.example.processcommunicate.okhttp;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.processcommunicate.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class MainActivity extends Activity {
    private static final String TAG = "MainActivity";
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Bitmap sb = (Bitmap) msg.obj;
            iv.setImageBitmap(sb);
        }
    };
    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_okhttp);
        HttpUtils httpUtils = HttpUtils.getmInstance(this).initEngine(new OkHttpEngine());

        iv = findViewById(R.id.iv);
        iv.post(new Runnable() {
            @Override
            public void run() {
                getData();
            }
        });


    }

    private void getData() {

           // final URL url = new URL("https://gss0.bdstatic.com/-4o3dSag_xI4khGkpoWK1HF6hhy/baike/w%3D268%3Bg%3D0/sign=a4757f2f043b5bb5bed727f80ee8b204/b7fd5266d01609242759dc9fd30735fae6cd3431.jpg");
            Callback callback = new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.v(TAG, "response fail------code:");
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    Log.v(TAG, "response.code:" + response.code());
                    Log.v(TAG, "response.message:" + response.message());
                    InputStream in = response.body().byteStream();


                    Bitmap bitmap = BitmapFactory.decodeStream(in);


                    Message message = handler.obtainMessage();
                    message.obj = bitmap;
                    handler.sendMessage(message);
                }
            };
            //HttpUtils.getmInstance(this).url(url.getPath()).execute(callback);





        new Thread() {

            @Override
            public void run() {
                HttpUtils.getmInstance(MainActivity.this).url("https://gss0.bdstatic.com/-4o3dSag_xI4khGkpoWK1HF6hhy/baike/w%3D268%3Bg%3D0/sign=a4757f2f043b5bb5bed727f80ee8b204/b7fd5266d01609242759dc9fd30735fae6cd3431.jpg").execute(callback);
//                try {
//                    OkHttpClient okHttpClient = new OkHttpClient();
//                    Request request = new Request.Builder().url(
//                            "https://gss0.bdstatic.com/-4o3dSag_xI4khGkpoWK1HF6hhy/baike/w%3D268%3Bg%3D0/sign=a4757f2f043b5bb5bed727f80ee8b204/b7fd5266d01609242759dc9fd30735fae6cd3431.jpg").build();
//                    Response response = null;
//                    response = okHttpClient.newCall(request).execute();
//                    if (response.isSuccessful()) {
//                        Log.v(TAG, "response.code:" + response.code());
//                        Log.v(TAG, "response.message:" + response.message());
//                        InputStream in = response.body().byteStream();
//
//
//                        Bitmap bitmap = BitmapFactory.decodeStream(in);
//
//
//                        Message message = handler.obtainMessage();
//                        message.obj = bitmap;
//                        handler.sendMessage(message);
//
//                    } else {
//                        Log.v(TAG, "response fail------code:" + response.code());
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }


            }
        }.start();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }
}
