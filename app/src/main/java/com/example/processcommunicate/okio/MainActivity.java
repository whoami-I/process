package com.example.processcommunicate.okio;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

import com.example.processcommunicate.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import okio.BufferedSource;
import okio.Okio;
import okio.Source;

public class MainActivity extends Activity {

    private final static String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_okio);

        String filePath = Environment.getExternalStorageDirectory().
                getAbsolutePath() + File.separator + "1.log";
        File file = new File(filePath);

        try {
            Source source = Okio.source(file);
            BufferedSource bs = Okio.buffer(source);
            String s = bs.readUtf8();
            bs.readInt();
            Log.e(TAG, s);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
