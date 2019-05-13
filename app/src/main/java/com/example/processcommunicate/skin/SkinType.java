package com.example.processcommunicate.skin;

import android.view.View;

public enum SkinType {

    TEXT_COLOR("text_color"){
        @Override
        public void skin(View view, String mResName) {

        }
    }, BACKGROUND("background"){
        @Override
        public void skin(View view, String mResName) {

        }
    }, SRC("src"){
        @Override
        public void skin(View view, String mResName) {

        }
    };

    private String mResName;

    SkinType(String name) {
        this.mResName = name;
    }

    public abstract void skin(View view, String mResName);
}
