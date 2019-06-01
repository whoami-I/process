package com.example.processcommunicate.banner;

import android.view.View;

abstract class BannerAdapter {
    public abstract View getView(int position,View convertView);
    public abstract int getCount();
}
