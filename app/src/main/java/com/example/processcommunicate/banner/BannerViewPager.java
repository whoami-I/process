package com.example.processcommunicate.banner;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

public class BannerViewPager extends ViewPager {
    private BannerAdapter mBannerAdapter;

    public BannerViewPager(@NonNull Context context) {
        super(context);
    }

    public BannerViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void setAdapter(BannerAdapter adapter) {
        this.mBannerAdapter = adapter;

        super.setAdapter(new BannerPagerAdapter(mBannerAdapter));
    }
}
