package com.example.processcommunicate.mvp;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.processcommunicate.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MainActivity extends Activity implements BitmapView {

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

        bitmapPresenter = new BitmapPresenter(new BitmapModel(), this);
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
    public void onSuccess(Bitmap bitmap) {
        iv.setImageBitmap(bitmap);
    }

    @Override
    public void onError() {

    }

    @Override
    public void onLoading() {

    }
}
