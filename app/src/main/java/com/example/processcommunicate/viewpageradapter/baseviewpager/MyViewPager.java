package com.example.processcommunicate.viewpageradapter.baseviewpager;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

public class MyViewPager extends ViewPager {
    static final String TAG = "MyViewPager";
    private OuterAdapter mOuterAdapter;




    public MyViewPager(@NonNull Context context) {

        this(context, null);
    }

    public MyViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);


    }

    public void setAdapter(OuterAdapter adapter) {
        this.mOuterAdapter = adapter;
        super.setAdapter(new InnerPagerAdapter(mOuterAdapter));

    }

}
