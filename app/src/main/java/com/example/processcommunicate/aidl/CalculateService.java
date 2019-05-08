package com.example.processcommunicate.aidl;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.processcommunicate.R;

public class CalculateService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.v("TAG", "onBind");
        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.v("TAG", "onCreate");


    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.v("TAG", "onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    private ICalculateInterface.Stub mBinder = new ICalculateInterface.Stub() {

        @Override
        public int calculate(int a, int b) {
            Log.v("TAG", "calculate");
            Calculate c = new Calculate();
            return c.calculate(a, b);
        }
    };
}
