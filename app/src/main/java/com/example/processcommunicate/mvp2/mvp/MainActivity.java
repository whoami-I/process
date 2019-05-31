package com.example.processcommunicate.mvp2.mvp;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.processcommunicate.R;
import com.example.processcommunicate.mvp2.mvp.base.BaseActivity;
import com.example.processcommunicate.mvp2.mvp.base.BaseModel;
import com.example.processcommunicate.mvp2.mvp.base.BasePresenter;
import com.example.processcommunicate.mvp2.mvp.inject.InjectPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MainActivity extends BaseActivity {

    private static final String TAG = "MainActivity";
    @BindView(R.id.btn)
    Button button;
    @BindView(R.id.iv)
    ImageView iv;
    Unbinder bind;

    @InjectPresenter
    BitmapPresenter bitmapPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_mvp);
        bind = ButterKnife.bind(this);


    }


    @OnClick(R.id.btn)
    public void btnClick(View v) {
        bitmapPresenter.getBitmap("https://images2015.cnblogs.com/blog/990919/201611/990919-20161108201105827-1200201864.png");

    }

    @Override
    protected void onDestroy() {
        bind.unbind();
        super.onDestroy();

    }


    @Override
    public void onSuccess(Object data) {
        iv.setImageBitmap((Bitmap) data);
    }

    @Override
    public void onError() {

    }

    @Override
    public void onLoading() {

    }
}
