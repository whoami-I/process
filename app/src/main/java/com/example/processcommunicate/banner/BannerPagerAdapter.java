package com.example.processcommunicate.banner;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.processcommunicate.log.Log;

import java.util.ArrayList;
import java.util.List;

public class BannerPagerAdapter extends PagerAdapter {

    private BannerAdapter mBannerAdapter;
    private List<View> convertViews;

    public BannerPagerAdapter(BannerAdapter bannerAdapter) {

        this.mBannerAdapter = bannerAdapter;
        convertViews = new ArrayList<>();
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
        int itemCount = mBannerAdapter.getCount();
        View view = (View) mBannerAdapter.getView(position % itemCount,getConvertView());
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
        ImageView v = (ImageView) object;
        Object tag = v.getTag();
        Log.e("TAG", "TAG --> " + tag);
        container.removeView((View) object);
    }


}
