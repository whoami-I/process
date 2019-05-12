package com.example.processcommunicate.loadresource;

import android.app.Activity;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.processcommunicate.R;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class MainActivity extends Activity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_loadresource);


    }

    public void click(View v) {
        Constructor<AssetManager> constructor = null;
        Resources rs = getResources();
        try {
            constructor =
                    AssetManager.class.getConstructor();
            AssetManager assetManager = constructor.newInstance();

            Method addAssetPathMethod =
                    AssetManager.class
                            .getDeclaredMethod("addAssetPath", String.class);
            String resourcePath = Environment.getExternalStorageDirectory()
                    .getAbsolutePath() + File.separator + "1myfile"
                    + File.separator + "night.skin";
            addAssetPathMethod.invoke(assetManager, resourcePath);
            Resources resource = new Resources(assetManager, rs.getDisplayMetrics(),
                    rs.getConfiguration());
            int drawableId = resource.getIdentifier("image", "drawable", "com.example.zhuhaigen.testresource");
            Drawable drawable = resource.getDrawable(drawableId);
            Log.e(TAG, "drawableId" + drawableId);

            ImageView iv = findViewById(R.id.iv);
            iv.setImageDrawable(drawable);
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


}
