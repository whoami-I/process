package com.example.processcommunicate.banner;

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

        int[] ids = new int[]{R.drawable.first,
                R.drawable.second,
                R.drawable.third};

        bannerViewPager.setAdapter(new BannerAdapter() {
            @Override
            public View getView(int position, View convertView) {
                ImageView imageView = null;
                if (convertView == null) {
                    imageView = new ImageView(MainActivity.this);
                } else {
                    imageView = (ImageView) convertView;
                }

                imageView.setImageResource(ids[position]);
                return imageView;
            }

            @Override
            public int getCount() {
                return ids.length;
            }
        });
        bannerViewPager.setDuration(1500);
        //bannerViewPager.setOffscreenPageLimit();
        bannerViewPager.attach();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        bannerViewPager.detach();
    }
}
