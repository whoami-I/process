package com.example.processcommunicate.myeventbus;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.processcommunicate.R;
import com.example.processcommunicate.log.Log;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MainActivity extends Activity {

    private static final String TAG = "MainActivity";
    @BindView(R.id.btn)
    Button button;
    Unbinder bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_myeventbus);
        bind = ButterKnife.bind(this);
        EventBus.getDefault().register(this);
    }

    @OnClick(R.id.btn)
    public void btnClick(View v) {
        Log.e(TAG, "click");
        EventBus.getDefault().post("haha");

    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND, priority = 10, sticky = true)
    public void f1(String s) {
        Log.e(TAG, "receive messgae --> " + s);
    }

    @Override
    protected void onDestroy() {
        bind.unbind();
        EventBus.getDefault().unRegister(this);

        super.onDestroy();
    }
}
