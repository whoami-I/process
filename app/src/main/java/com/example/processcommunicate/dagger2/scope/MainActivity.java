package com.example.processcommunicate.dagger2.scope;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.processcommunicate.R;
import com.example.processcommunicate.log.Log;

import javax.inject.Inject;
import javax.inject.Named;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    @Inject
    @Named("null")
    Student s;
    @Inject
    @Named("int")
    Student s1;
    @BindView(R.id.button)
    Button button;
    @BindView(R.id.button2)
    Button button2;
    Unbinder bind;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dagger);
        bind = ButterKnife.bind(this);
        StudentComponent studentComponent = DaggerStudentComponent.builder()
                .build();
        studentComponent.inject(this);
        Holder.setStudentComponent(studentComponent);

        Log.e(TAG, "-->" + s);
        Log.e(TAG, "-->" + s1);

    }


    @OnClick(R.id.button)
    public void click(View v) {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.button2)
    public void click2(View v) {
        Intent intent = new Intent(this, ThirdActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }
}
