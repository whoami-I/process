package com.example.processcommunicate.skin;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public enum SkinType {

    TEXT_COLOR("textColor") {
        @Override
        public void skin(View view, String mResName) {
            SkinResource skinResource = (SkinResource) getSkinResource();
            ColorStateList color = skinResource.getColorByName(mResName);
            if (color != null) {
                TextView v = (TextView) view;
                v.setTextColor(color);
            }
        }
    }, BACKGROUND("background") {
        @Override
        public void skin(View view, String mResName) {
            SkinResource skinResource = (SkinResource) getSkinResource();
            Drawable drawable = skinResource.getDrawableByName(mResName);
            if (drawable != null) {
                ImageView v = (ImageView) view;
                v.setImageDrawable(drawable);
            }
            //drawable也可能是颜色
            ColorStateList color = skinResource.getColorByName(mResName);
            if (color != null) {
                view.setBackgroundColor(color.getDefaultColor());
            }
        }
    }, SRC("src") {
        @Override
        public void skin(View view, String mResName) {
            SkinResource skinResource = (SkinResource) getSkinResource();
            Drawable drawable = skinResource.getDrawableByName(mResName);
            if (drawable != null) {
                ImageView v = (ImageView) view;
                v.setImageDrawable(drawable);
            }
        }
    };

    private String mResName;

    SkinType(String name) {
        this.mResName = name;
    }

    public abstract void skin(View view, String mResName);

    public String getResName() {
        return mResName;
    }

    private static SkinResource getSkinResource() {
        return SkinManager.getInstance().getSkinResource();
    }

}
