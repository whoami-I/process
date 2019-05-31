package com.example.processcommunicate.hotfix;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;

import com.example.processcommunicate.R;
import com.example.processcommunicate.base.BaseActivity;

import java.io.File;


public class RxPermissionActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    @Override
    protected void onSuccess() {
        setContentView(R.layout.layout_permission);
        String fromPath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator +
                "1fix";
        FixbugManager fixbugManager = new FixbugManager(
                RxPermissionActivity.this.getApplicationContext(), fromPath);
        fixbugManager.fixBug();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }
}
