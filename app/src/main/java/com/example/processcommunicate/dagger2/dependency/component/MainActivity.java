package com.example.processcommunicate.dagger2.dependency.component;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.processcommunicate.R;
import com.example.processcommunicate.dagger2.dependency.clazz.A;
import com.example.processcommunicate.dagger2.dependency.clazz.B;
import com.example.processcommunicate.dagger2.dependency.clazz.C;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.OkHttpClient;


public class MainActivity extends AppCompatActivity {

    @BindView(R.id.button)
    Button button;

    Unbinder bind;
    OkHttpClient okHttpClient;

    @Inject
    A a;
    @Inject
    B b;
    @Inject
    C c;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dagger);
        bind = ButterKnife.bind(this);
        DaggerMyComponent.create().inject(this);

        a.show();
        b.show();
        c.show();

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
