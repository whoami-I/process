package com.example.processcommunicate.skin;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;

import com.example.processcommunicate.log.Log;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class SkinResource {
    private static final String TAG = "SkinResource";

    private Resources mResource;
    private String mResourcePath;
    private String mPackageName;

    public SkinResource(Context context, String resourcePath) {
        mResourcePath = resourcePath;
        Constructor<AssetManager> constructor = null;
        Resources rs = context.getResources();
        try {
            constructor =
                    AssetManager.class.getConstructor();
            AssetManager assetManager = constructor.newInstance();

            Method addAssetPathMethod =
                    AssetManager.class
                            .getDeclaredMethod("addAssetPath", String.class);

            addAssetPathMethod.invoke(assetManager, resourcePath);
            mResource = new Resources(assetManager, rs.getDisplayMetrics(),
                    rs.getConfiguration());
            mPackageName = context.getPackageManager().getPackageArchiveInfo(mResourcePath,
                    PackageManager.GET_ACTIVITIES).packageName;
            Log.e(TAG, "mPackageName --> " + mPackageName);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public Drawable getDrawableByName(String name) {
        Log.e(TAG, "name --> " + name);
        int identifier = mResource.getIdentifier(name, "drawable", mPackageName);
        if (identifier == 0) return null;
        //mResource.getDrawable()不能传入0，会报错
        Drawable drawable = mResource.getDrawable(identifier);
        return drawable;
    }

    public String getResourcePath() {
        return mResourcePath;
    }

    public ColorStateList getColorByName(String name) {

        int identifier = mResource.getIdentifier(name, "color", mPackageName);
        if (identifier == 0) return null;
        ColorStateList colorStateList = mResource.getColorStateList(identifier);
        return colorStateList;
    }


}
