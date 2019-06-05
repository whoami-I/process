package com.example.processcommunicate.dagger2.studentmodule;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.processcommunicate.R;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.OkHttpClient;


public class MainActivity extends AppCompatActivity {
    @Inject
    Student s;
    @BindView(R.id.button)
    Button button;

    Unbinder bind;
    OkHttpClient okHttpClient;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dagger);
        bind = ButterKnife.bind(this);
        DaggerStudentComponent.builder()
                .build().inject(this);

        okHttpClient = new OkHttpClient();
    }


    @OnClick(R.id.button)
    public void click(View v) {
        s.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }
}
