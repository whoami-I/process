package com.example.processcommunicate.dagger2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.processcommunicate.R;
import com.example.processcommunicate.base.BaseActivity;
import com.example.processcommunicate.log.Log;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import javax.inject.Inject;
import javax.security.auth.login.LoginException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import dagger.android.AndroidInjectionKey;
import dagger.android.DaggerActivity;


public class MainActivity extends AppCompatActivity {
    @Inject
    Student s;
    @BindView(R.id.button)
    Button button;

    Unbinder bind;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dagger);
        bind = ButterKnife.bind(this);
        DaggerStudentComponent.builder().build().inject(this);
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
