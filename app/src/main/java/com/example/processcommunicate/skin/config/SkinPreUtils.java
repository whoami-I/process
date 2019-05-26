package com.example.processcommunicate.skin.config;

import android.content.Context;

public class SkinPreUtils {
    private Context mContext;
    private static SkinPreUtils mInstance;

    private SkinPreUtils(Context context) {
        this.mContext = context.getApplicationContext();
    }

    public static SkinPreUtils getInstance(Context context) {
        if (mInstance == null) {
            synchronized (SkinPreUtils.class) {
                if (mInstance == null) {
                    mInstance = new SkinPreUtils(context);
                }
            }
        }
        return mInstance;
    }

    public void saveSkinPath(String path) {
        mContext.getSharedPreferences(SkinConstantUtils.SKINCONFIGFILENAME, Context.MODE_PRIVATE)
                .edit().putString(SkinConstantUtils.SKINPATH, path).commit();
    }

    public String getSkinPath() {
        return mContext.getSharedPreferences(SkinConstantUtils.SKINCONFIGFILENAME, Context.MODE_PRIVATE)
                .getString(SkinConstantUtils.SKINPATH, null);
    }

    public void clearSkinPath() {
        mContext.getSharedPreferences(SkinConstantUtils.SKINCONFIGFILENAME, Context.MODE_PRIVATE)
                .edit().remove(SkinConstantUtils.SKINPATH).commit();
    }
}
