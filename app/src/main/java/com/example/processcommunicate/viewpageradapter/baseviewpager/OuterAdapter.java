package com.example.processcommunicate.viewpageradapter.baseviewpager;

import android.view.View;
import android.view.ViewGroup;

abstract public class OuterAdapter {
    public abstract View getView(int position, View convertView, ViewGroup parent);

    public abstract int getCount();
}
