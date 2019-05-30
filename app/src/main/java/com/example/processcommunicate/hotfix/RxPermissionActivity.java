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
import com.example.processcommunicate.log.Log;

import java.io.File;

import io.reactivex.functions.Consumer;


public class RxPermissionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_permission);

        String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
        //if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

        int i = ContextCompat.checkSelfPermission(this, permissions[0]);
        if (i != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this
                    , permissions, 3);

        } else {
            onSuccess();
        }


    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 3:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.e("TAG", "tongyi");

                } else {
                    Toast.makeText(this, "权限被拒绝了", Toast.LENGTH_LONG).show();
                    finish();
                }
                break;
            default:
                break;
        }
    }

    void onSuccess() {
        String fromPath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator +
                "1myfile" + File.separator + "fixdex";
        FixbugManager fixbugManager = new FixbugManager(
                RxPermissionActivity.this.getApplicationContext(), fromPath);
        fixbugManager.fixBug();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
