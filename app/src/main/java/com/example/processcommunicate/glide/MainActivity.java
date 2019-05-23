package com.example.processcommunicate.glide;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.processcommunicate.R;
import com.example.processcommunicate.mvp.BitmapModel;
import com.example.processcommunicate.mvp.BitmapPresenter;
import com.example.processcommunicate.mvp.BitmapView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MainActivity extends Activity {

    private static final String TAG = "MainActivity";
    @BindView(R.id.btn)
    Button button;
    @BindView(R.id.iv)
    ImageView iv;
    Unbinder bind;
    BitmapPresenter bitmapPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_mvp);
        bind = ButterKnife.bind(this);

    }

    @OnClick(R.id.btn)
    public void btnClick(View v) {
        String url = "http://cn.bing.com/az/hprichbg/rb/Dongdaemun_ZH-CN10736487148_1920x1080.jpg";
        Glide.with(this)
                .load(url)
                .override(200,200)
                .into(iv);
    }

    @Override
    protected void onDestroy() {
        bind.unbind();
        super.onDestroy();
    }


}
