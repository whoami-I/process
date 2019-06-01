package com.example.processcommunicate.banner;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

class MyPager extends PagerAdapter {

    private List<ImageView> mImageViewList;

    public MyPager(List<ImageView> imageViewList) {
        this.mImageViewList = imageViewList;
    }

    @Override
    public int getCount() {
        return mImageViewList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        container.addView(mImageViewList.get(position));
        return mImageViewList.get(position);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView(mImageViewList.get(position));
    }
}
