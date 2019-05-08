package com.example.processcommunicate.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Process;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.processcommunicate.aidl.ICalculateInterface;

public class MyService extends Service {
    private static final String TAG = "MyService";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.v(TAG, "onBind");

        return calc;

    }

    private ICalculateInterface.Stub calc = new ICalculateInterface.Stub() {
        Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                Log.v("ICalculateInterface.Stub", Process.myPid() + "-----" + Process.myTid());
                Log.v("ICalculateInterface.Stub", handler.getLooper()+"");
            }
        };

        @Override
        public int calculate(int a, int b) throws RemoteException {

            handler.obtainMessage().sendToTarget();
            Log.v("calculate", Process.myPid() + "-----" + Process.myTid());
            return a + b;
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        Log.v(TAG, "onCreate");
        Log.v(TAG, "pid:" + Process.myPid());

        //Log.v("onCreate",);
//        try {
//            Thread.sleep(10000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        Log.v("onCreate", Process.myPid() + "-----" + Process.myTid());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.v(TAG, "onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.v(TAG, "onDestroy");
    }

    class MyBinder extends Binder {

        public void startDownload() {
            Log.d("TAG", "startDownload() executed");

        }

    }
}
