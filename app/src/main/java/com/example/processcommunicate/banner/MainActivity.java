package com.example.processcommunicate.banner;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.example.processcommunicate.R;
import com.example.processcommunicate.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.view_pager)
    BannerViewPager bannerViewPager;


    @Override
    protected void onSuccess() {
        setContentView(R.layout.layout_banner);
        ButterKnife.bind(this);
        List<ImageView> imageViews = new ArrayList<>();


        ImageView i1 = new ImageView(this);
        i1.setImageResource(R.drawable.first);
        ImageView i2 = new ImageView(this);
        i2.setImageResource(R.drawable.second);
        ImageView i3 = new ImageView(this);
        i3.setImageResource(R.drawable.third);
        imageViews.add(i1);
        imageViews.add(i2);
        imageViews.add(i3);
        bannerViewPager.setAdapter(new BannerAdapter() {
            @Override
            public View getView(int position) {
                return imageViews.get(position);
            }
        });
    }
}
