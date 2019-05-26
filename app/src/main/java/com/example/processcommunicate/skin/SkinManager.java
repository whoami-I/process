package com.example.processcommunicate.skin;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;

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
    }

    public static SkinManager getInstance() {
        return skinManager;
    }

    public void loadSkin(String path) {
        checkNotNull(path);
        Set<Activity> activities =
                map.keySet();
        for (Activity activity : activities) {
            List<SkinView> skinViews = map.get(activity);
            for (SkinView skinView : skinViews) {
                skinView.skin();

            }
        }
    }

    private void checkNotNull(String path) {
        if (mSkinResource == null) {
            mSkinResource = new SkinResource(context, path);
        }
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
}
