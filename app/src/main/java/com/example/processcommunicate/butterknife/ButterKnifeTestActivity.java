package com.example.processcommunicate.butterknife;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;

import com.example.processcommunicate.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class ButterKnifeTestActivity extends Activity {
    private Unbinder ButterKnifeTestActivity_ViewBinding;
    @BindView(R.id.butterknife)
    public Button butterknife;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_butterknifetest);
        ButterKnifeTestActivity_ViewBinding = ButterKnife.bind(this);
        butterknife.setText("1234");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnifeTestActivity_ViewBinding.unbind();
    }
}
