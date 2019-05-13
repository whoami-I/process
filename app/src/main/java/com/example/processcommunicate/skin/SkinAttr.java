package com.example.processcommunicate.skin;

import android.view.View;

class SkinAttr {

    private String mResName;
    private SkinType mSkinType;

    public void skin(View view) {
        mSkinType.skin(view, mResName);
    }
}
