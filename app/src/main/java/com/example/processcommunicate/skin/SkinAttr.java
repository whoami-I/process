package com.example.processcommunicate.skin;

import android.content.Context;
import android.view.View;

class SkinAttr {

    private String mResName;
    private SkinType mSkinType;

    public SkinAttr(SkinType skinType, String resName) {
        mResName = resName;
        mSkinType = skinType;
    }

    public void skin(View view) {
        mSkinType.skin(view, mResName);
    }
}
