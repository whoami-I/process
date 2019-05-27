package com.example.processcommunicate.plugin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.processcommunicate.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends Activity {


    private final static String TAG = "MainActivity";
    @BindView(R.id.create)
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_plugin);
        //startActivity();
        ButterKnife.bind(this);

    }

    @OnClick(R.id.create)
    public void create(View v) {
        Intent intent = new Intent(this, RealActivity.class);
        startActivity(intent);
    }


}
