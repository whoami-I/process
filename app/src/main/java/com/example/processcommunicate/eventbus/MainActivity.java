package com.example.processcommunicate.eventbus;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.processcommunicate.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


public class MainActivity extends Activity {


    private final static String TAG = "MainActivity";
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_eventbus);
        button = findViewById(R.id.btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });
        EventBus.getDefault().register(this);

    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = false, priority = 100)
    public void setButtonText(String s) {
        button.setText(s);
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = false, priority = 100)
    public void f1(String s) {
        Log.e(TAG, "s --> " + s);
    }


    @Override
    protected void onDestroy() {

        EventBus.getDefault().unregister(this);

        super.onDestroy();

    }
}
