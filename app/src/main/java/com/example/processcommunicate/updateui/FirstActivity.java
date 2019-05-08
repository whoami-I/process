package com.example.processcommunicate.updateui;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.processcommunicate.R;
import com.example.processcommunicate.service.MainActivity;

public class FirstActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_updateui2);
        startActivity(new Intent());
        Button button = findViewById(R.id.start_service);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                new Thread(){
                    @Override
                    public void run() {
                        Looper looper = Looper.myLooper();
                        looper.prepare();
                        final Dialog dialog=new Dialog(FirstActivity.this);
                        final TextView view=new TextView(FirstActivity.this);
                        view.setText("21213");
                        dialog.setContentView(view);

                        dialog.show();
                        view.setText("abcd");
                        dialog.setContentView(view);
                        looper.loop();
                    }
                }.start();


            }
        });
    }
}
