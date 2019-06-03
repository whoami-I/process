package com.example.processcommunicate.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.processcommunicate.R;
import com.example.processcommunicate.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SecondActivity extends BaseActivity {

    @BindView(R.id.li)
    RelativeLayout li;

    @Override
    protected void onSuccess() {
        setContentView(R.layout.layout_testsecond);
        ButterKnife.bind(this);

        IndicatorBottomView indicatorBottomView = new IndicatorBottomView(this);
        li.addView(indicatorBottomView);
        ViewGroup.LayoutParams layoutParams = indicatorBottomView.getLayoutParams();
        layoutParams.width = ViewGroup.LayoutParams.WRAP_CONTENT;
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        indicatorBottomView.setLayoutParams(layoutParams);


    }
}
