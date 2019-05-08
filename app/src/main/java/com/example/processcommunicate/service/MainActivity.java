package com.example.processcommunicate.service;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Looper;
import android.os.Process;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.processcommunicate.R;
import com.example.processcommunicate.binder.Book;
import com.example.processcommunicate.binder.SecondActivity;

import org.w3c.dom.Text;

import java.lang.reflect.Field;

public class MainActivity extends Activity implements View.OnClickListener {
    private static final String TAG = "MainActivity";

    private ServiceConnection mServiceConn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.v(TAG, "onServiceConnected " + service);


        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.v(TAG, "onServiceDisconnected");
        }
    };


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//startActivity(new Intent());
        setContentView(R.layout.layout_service);
        Log.v("TAG", "getMainLooper()" + getMainLooper());
        final Button toast = findViewById(R.id.toast);
        toast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread() {
                    @Override
                    public void run() {
                        Looper looper = Looper.myLooper();
                        looper.prepare();
                        Toast toast1 = Toast.makeText(MainActivity.this, "12345", Toast.LENGTH_SHORT);

                        toast1.show();
                        toast1.setText("hahaha");
                        looper.loop();
                    }
                }.start();

            }
        });
        Button start_service = findViewById(R.id.start_service);
        Button stop_service = findViewById(R.id.stop_service);
        Button bind_service = findViewById(R.id.bind_service);
        Button unbind_service = findViewById(R.id.unbind_service);
        start_service.setOnClickListener(this);
        stop_service.setOnClickListener(this);
        bind_service.setOnClickListener(this);
        unbind_service.setOnClickListener(this);
        Log.v(TAG, "pid:" + Process.myPid());

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            for (int i = 0; i < permissions.length; i++) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "" + "权限" + permissions[i] + "申请成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "" + "权限" + permissions[i] + "申请失败", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    public void requestPower() {
        //判断是否已经赋予权限
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.SYSTEM_ALERT_WINDOW)
                != PackageManager.PERMISSION_GRANTED) {
            //如果应用之前请求过此权限但用户拒绝了请求，此方法将返回 true。
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.SYSTEM_ALERT_WINDOW)) {//这里可以写个对话框之类的项向用户解释为什么要申请权限，并在对话框的确认键后续再次申请权限
            } else {
                //申请权限，字符串数组内是一个或多个要申请的权限，1是申请权限结果的返回参数，在onRequestPermissionsResult可以得知申请结果
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.SYSTEM_ALERT_WINDOW,}, 1);
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start_service: {

                new Thread() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(MainActivity.this, MyService.class);
                        startService(intent);
                    }
                }.start();
            }

            break;
            case R.id.stop_service: {

                Intent intent = new Intent(MainActivity.this, MyService.class);
                stopService(intent);

            }

            break;

            case R.id.bind_service: {
                //Intent intent = new Intent(this, MyService.class);
                Intent intent = new Intent();
                intent.setAction("service.MyService");
                intent.setPackage("com.example.processcommunicate");
                bindService(intent, mServiceConn, BIND_AUTO_CREATE);
            }

            break;
            case R.id.unbind_service: {
                //Intent intent = new Intent(this, MyService.class);
                unbindService(mServiceConn);
            }

            break;
        }
    }
}
