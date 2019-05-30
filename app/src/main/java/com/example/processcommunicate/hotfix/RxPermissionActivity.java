package com.example.processcommunicate.hotfix;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.processcommunicate.R;
import com.example.processcommunicate.base.BaseActivity;
import com.example.processcommunicate.log.Log;

import java.io.File;

import io.reactivex.functions.Consumer;


public class RxPermissionActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    @Override
    protected void onSuccess() {
        setContentView(R.layout.layout_permission);
        String fromPath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator +
                "1myfile" + File.separator + "fixdex";
        FixbugManager fixbugManager = new FixbugManager(
                RxPermissionActivity.this.getApplicationContext(), fromPath);
        fixbugManager.fixBug();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }
}
