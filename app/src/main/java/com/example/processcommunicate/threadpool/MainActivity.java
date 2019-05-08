package com.example.processcommunicate.threadpool;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.processcommunicate.R;
import com.example.processcommunicate.myokhttp.okhttp.Call;
import com.example.processcommunicate.myokhttp.okhttp.OkHttpClient;
import com.example.processcommunicate.myokhttp.okhttp.Request;
import com.example.processcommunicate.myokhttp.okhttp.RequestBody;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class MainActivity extends Activity {
    //ThreadPoolExecutor poolExecutor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_threadpool);

       final ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(3,
                5, 60, TimeUnit.SECONDS,
                new SynchronousQueue<>(), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread t = new Thread(r);
                t.setDaemon(false);
                return t;
            }
        });

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < 30; i++) {
                    final int finali=i;
                    Runnable r = new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            Log.e("TAG", "execute:" + finali);
                        }
                    };
                    Log.e("TAG", "execute(r):" + finali);
                    poolExecutor.execute(r);

                }
            }
        });


    }

    private void test() {

    }
}
