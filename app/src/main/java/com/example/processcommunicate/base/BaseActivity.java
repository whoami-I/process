package com.example.processcommunicate.base;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.processcommunicate.log.Log;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //申请读写外部存储权限
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

    //在success中setContentView
    protected abstract void onSuccess();

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 3:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.e("TAG", "write external storage permitted");
                    onSuccess();
                } else {
                    Toast.makeText(this, "权限被拒绝了", Toast.LENGTH_LONG).show();
                    finish();
                }
                break;
            default:
                break;
        }
    }
}
