package com.example.processcommunicate.binder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.example.processcommunicate.R;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_binder);
        TextView tv = findViewById(R.id.launch_mode);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                Book book = new Book("huizhou");
                Bundle bundle = new Bundle();
                bundle.putParcelable("book", book);
                intent.putExtras(bundle);

                startActivity(intent);

            }
        });

    }


}
