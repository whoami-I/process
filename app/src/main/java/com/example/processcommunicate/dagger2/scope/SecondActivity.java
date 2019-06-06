package com.example.processcommunicate.dagger2.scope;

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


public class SecondActivity extends AppCompatActivity {
    private static final String TAG = "SecondActivity";
    @Named("null")
    @Inject
    Student s;
    @Named("null")
    @Inject
    Student s1;
    @BindView(R.id.button)
    Button button;

    @Inject
    Data d;
    Unbinder bind;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dagger);
        bind = ButterKnife.bind(this);
        Holder.getStudentComponent().addSecondActivityComponent().inject(this);
//        DaggerSecondActivityComponent.builder()
//                .build().inject(this);
        Log.e(TAG, "-->" + s);
        Log.e(TAG, "-->" + s1);


    }


    @OnClick(R.id.button)
    public void click(View v) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }
}
