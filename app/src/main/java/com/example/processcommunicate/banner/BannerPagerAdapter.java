package com.example.processcommunicate.banner;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

public class BannerPagerAdapter extends PagerAdapter {

    private BannerAdapter mBannerAdapter;

    public BannerPagerAdapter(BannerAdapter bannerAdapter) {
        this.mBannerAdapter = bannerAdapter;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = (View) mBannerAdapter.getView(position);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
