package com.example.processcommunicate.skin;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.example.processcommunicate.R;
import com.example.processcommunicate.log.Log;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseSkinActivity {

    private static final String TAG = "MainActivity";
    @BindView(R.id.skin)
    Button button1;
    @BindView(R.id.addActivity)
    Button button2;
    @BindView(R.id.restore)
    Button button3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_skin);
        ButterKnife.bind(this);
        Log.e(TAG, "skin");


    }


    @OnClick(R.id.skin)
    public void skin(View v) {
        Log.e(TAG, "skin");
        String path = Environment.getExternalStorageDirectory().getAbsolutePath() +
                File.separator + "1myfile" + File.separator + "night.skin";
        Log.e(TAG, "path --> " + path);
        SkinManager.getInstance().loadSkin(path);
    }

    @OnClick(R.id.addActivity)
    public void addActivity(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.restore)
    public void restore(View v) {
        SkinManager.getInstance().restoreDefaultSkin();
    }
}
