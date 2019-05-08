package com.example.processcommunicate.binder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.TextView;

import com.example.processcommunicate.R;

public class SecondActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_binder);
        TextView tv = findViewById(R.id.launch_mode);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        Book book = bundle.getParcelable("book");
        Log.v("TAG", "book.name" + book.name);
    }


}
