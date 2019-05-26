package com.example.processcommunicate.skin;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import android.view.LayoutInflater;

import com.example.processcommunicate.skin.config.SkinPreUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class SkinManager {

    private static SkinManager skinManager = new SkinManager();
    private Context context;
    private Map<Activity, List<SkinView>> map = new HashMap<>();
    private SkinResource mSkinResource;

    private SkinManager() {
    }

    public void init(Context context) {
        this.context = context.getApplicationContext();
        //如果存在已经换肤的情况，那么就要先初始化mSkinResource变量
        String skinPath = getSkinPath();
        if (!TextUtils.isEmpty(skinPath)) {
            mSkinResource = new SkinResource(context, skinPath);

        }

    }

    public static SkinManager getInstance() {
        return skinManager;
    }

    public void loadSkin(String path) {


        if (TextUtils.isEmpty(path)) {
            throw new RuntimeException("Skin path can not be null");
        }
        if (mSkinResource == null) {
            mSkinResource = new SkinResource(context, path);
        } else {
            String resourcePath = mSkinResource.getResourcePath();
            if (!path.equals(resourcePath)) {
                mSkinResource = new SkinResource(context, path);
            }
        }

        Set<Activity> activities =
                map.keySet();
        for (Activity activity : activities) {
            List<SkinView> skinViews = map.get(activity);
            for (SkinView skinView : skinViews) {
                skinView.skin();

            }
        }
        //保存换肤路径
        saveSkinPath(path);
    }

    public void restoreDefaultSkin() {
        String skinPath = getSkinPath();
        if (TextUtils.isEmpty(skinPath)) return;
        skinPath = context.getPackageResourcePath();
        mSkinResource = new SkinResource(context, skinPath);
        Set<Activity> activities =
                map.keySet();
        for (Activity activity : activities) {
            List<SkinView> skinViews = map.get(activity);
            for (SkinView skinView : skinViews) {
                skinView.skin();

            }
        }
        //清除皮肤
        clearSkinPath();
    }

    public void saveSkinPath(String path) {
        SkinPreUtils.getInstance(context).saveSkinPath(path);

    }

    public void clearSkinPath() {
        SkinPreUtils.getInstance(context).clearSkinPath();
    }

    public String getSkinPath() {
        return SkinPreUtils.getInstance(context).getSkinPath();
    }

    private void checkNotNull(String path) {

    }

    public SkinResource getSkinResource() {
        return mSkinResource;
    }

    public void register(Activity activity, SkinView skinView) {
        List<SkinView> skinViews = map.get(activity);
        if (skinViews == null) {
            skinViews = new ArrayList<>();
        }
        skinViews.add(skinView);
        map.put(activity, skinViews);
    }

    public void unRegister(Activity activity) {
        map.remove(activity);
    }

    public void checkSkin(SkinView skinView) {
        String skinPath = getSkinPath();
        if (!TextUtils.isEmpty(skinPath)) {
            skinView.skin();
        }
    }
}
