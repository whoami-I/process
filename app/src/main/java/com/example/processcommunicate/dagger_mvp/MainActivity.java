package com.example.processcommunicate.dagger_mvp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.processcommunicate.R;
import com.example.processcommunicate.log.Log;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class MainActivity extends AppCompatActivity {

    @BindView(R.id.button)
    Button button;

    Unbinder bind;


    @Inject
    SharedPreferences sp;

    @Inject
    Student s;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dagger);
        bind = ButterKnife.bind(this);
        DaggerMainActivityComponent
                .builder()
                .myApplicationComponent(MyApplicationComponentHolder.getMyApplicationComponent())
                .build()
                .inject(this);
        Log.e("TAG", "" + s);

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
