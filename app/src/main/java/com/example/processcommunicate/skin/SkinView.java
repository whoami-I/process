package com.example.processcommunicate.skin;

import android.view.View;

import java.util.List;

public class SkinView {
    private View mView;
    private List<SkinAttr> mAttrs;

    public void skin(){
        for(SkinAttr attr:mAttrs){
            attr.skin(mView);
        }
    }
}
