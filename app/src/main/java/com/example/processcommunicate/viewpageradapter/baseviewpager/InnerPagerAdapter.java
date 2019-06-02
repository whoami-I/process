package com.example.processcommunicate.viewpageradapter.baseviewpager;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class InnerPagerAdapter extends PagerAdapter {

    private OuterAdapter mOuterAdapter;
    private List<View> convertViews;

    public InnerPagerAdapter(OuterAdapter outerAdapter) {

        this.mOuterAdapter = outerAdapter;
        convertViews = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return mOuterAdapter.getCount();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        int itemCount = mOuterAdapter.getCount();
        View view = (View) mOuterAdapter.getView(position % itemCount, getConvertView(),container);
        container.addView(view);
        return view;
    }


    private View getConvertView() {
        for (int i = 0; i < convertViews.size(); i++) {
            View convertView = convertViews.get(i);
            if (convertView.getParent() != null) {
                return convertView;
            }
        }
        return null;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }


}
