package com.example.processcommunicate.eventbus;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.processcommunicate.R;

import org.greenrobot.eventbus.EventBus;

public class SecondActivity extends Activity {
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_eventbus);
        button = findViewById(R.id.btn);
        button.setText("SecondActivity");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post("hello from second activity");
                finish();
            }
        });
    }
}
