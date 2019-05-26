package com.example.processcommunicate.skin;

import android.view.View;

import java.util.List;

public class SkinView {
    private View mView;
    //可能有多个参数需要换肤，比如同时更换
    //背景、字体颜色等
    private List<SkinAttr> mAttrs;

    public SkinView(View view, List<SkinAttr> skinAttrs) {
        this.mView = view;
        this.mAttrs = skinAttrs;
    }

    public void skin() {
        for (SkinAttr attr : mAttrs) {
            attr.skin(mView);
        }
    }
}
