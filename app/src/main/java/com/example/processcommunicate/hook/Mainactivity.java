package com.example.processcommunicate.hook;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.processcommunicate.R;

public class Mainactivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = LayoutInflater.from(this);
        LayoutInflaterCompat.setFactory2(inflater, new LayoutInflater.Factory2() {


            @Override
            public View onCreateView(String name, Context context, AttributeSet attrs) {
                return onCreateView(null, name, context, attrs);
            }

            @Override
            public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
                View view = null;
                switch (name) {
                    case "Button":
                        view = new TextView(context, attrs);
                        break;

                }
                return view;

            }
        });
        setContentView(R.layout.layout_hook);
        View start_service = findViewById(R.id.start_service);
        Button b = new Button(this);
        Log.v("TAG", "" + b);
        Log.v("TAG", "" + start_service);

        b.setText("start_service");
        b.setTextSize(20);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        LinearLayout li = findViewById(R.id.li);
        li.addView(b, lp);

    }
}
