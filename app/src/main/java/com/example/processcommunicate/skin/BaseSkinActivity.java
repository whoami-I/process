package com.example.processcommunicate.skin;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewParent;

import org.xmlpull.v1.XmlPullParser;

import java.util.List;


public class BaseSkinActivity extends Activity {
    private static final String TAG = "BaseSkinActivity";
    private SkinCompatViewInflater mSkinCompatViewInflater;

    private static final boolean IS_PRE_LOLLIPOP = Build.VERSION.SDK_INT < 21;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SkinManager.getInstance().init(this);
        LayoutInflater inflater = LayoutInflater.from(this);
        LayoutInflaterCompat.setFactory2(inflater, new LayoutInflater.Factory2() {


            @Override
            public View onCreateView(String name, Context context, AttributeSet attrs) {
                return onCreateView(null, name, context, attrs);
            }

            @Override
            public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
                //1、创建view
                View view = createView(parent, name, context, attrs);
                //2、解析换肤参数
                Log.e(TAG, "view --> " + view);
                if (view != null) {
                    List<SkinAttr> skinAttrs = SkinAttrsSupport.getAttrs(context, attrs);
                    if (skinAttrs != null && !skinAttrs.isEmpty()) {
                        SkinView skinView = new SkinView(view, skinAttrs);
                        //加入到map集合中
                        SkinManager.getInstance().register(BaseSkinActivity.this, skinView);

                        //3、看是否需要换肤
                        String skinPath = SkinManager.getInstance().getSkinPath();
                        if (skinPath != null) {
                            Log.e(TAG, "skin path --> " + skinPath);
                            SkinManager.getInstance().checkSkin(skinView);
                        }
                    }
                }



                return view;

            }
        });


    }

    private View createView(View parent, String name, Context context, AttributeSet attrs) {

        if (mSkinCompatViewInflater == null) {
            TypedArray a = getApplicationContext().obtainStyledAttributes(android.support.v7.appcompat.R.styleable.AppCompatTheme);
            String viewInflaterClassName =
                    a.getString(android.support.v7.appcompat.R.styleable.AppCompatTheme_viewInflaterClass);
            if ((viewInflaterClassName == null)
                    || SkinCompatViewInflater.class.getName().equals(viewInflaterClassName)) {
                // Either default class name or set explicitly to null. In both cases
                // create the base inflater (no reflection)
                mSkinCompatViewInflater = new SkinCompatViewInflater();
            } else {
                try {
                    Class viewInflaterClass = Class.forName(viewInflaterClassName);
                    mSkinCompatViewInflater =
                            (SkinCompatViewInflater) viewInflaterClass.getDeclaredConstructor()
                                    .newInstance();
                } catch (Throwable t) {
                    Log.i(TAG, "Failed to instantiate custom view inflater "
                            + viewInflaterClassName + ". Falling back to default.", t);
                    mSkinCompatViewInflater = new SkinCompatViewInflater();
                }
            }
        }

        boolean inheritContext = false;
        if (IS_PRE_LOLLIPOP) {
            inheritContext = (attrs instanceof XmlPullParser)
                    // If we have a XmlPullParser, we can detect where we are in the layout
                    ? ((XmlPullParser) attrs).getDepth() > 1
                    // Otherwise we have to use the old heuristic
                    : shouldInheritContext((ViewParent) parent);
        }

        return mSkinCompatViewInflater.createView(parent, name, context, attrs, inheritContext,
                IS_PRE_LOLLIPOP, /* Only read android:theme pre-L (L+ handles this anyway) */
                true, /* Read read app:theme as a fallback at all times for legacy reasons */
                false /* Only tint wrap the context if enabled */
        );
    }

    private boolean shouldInheritContext(ViewParent parent) {
        if (parent == null) {
            // The initial parent is null so just return false
            return false;
        }
        final View windowDecor = getWindow().getDecorView();
        while (true) {
            if (parent == null) {
                // Bingo. We've hit a view which has a null parent before being terminated from
                // the loop. This is (most probably) because it's the root view in an inflation
                // call, therefore we should inherit. This works as the inflated layout is only
                // added to the hierarchy at the end of the inflate() call.
                return true;
            } else if (parent == windowDecor || !(parent instanceof View)
                    || ViewCompat.isAttachedToWindow((View) parent)) {
                // We have either hit the window's decor view, a parent which isn't a View
                // (i.e. ViewRootImpl), or an attached view, so we know that the original parent
                // is currently added to the view hierarchy. This means that it has not be
                // inflated in the current inflate() call and we should not inherit the context.
                return false;
            }
            parent = parent.getParent();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //因为hashmap有activity的引用，因此在销毁时，断开这个引用
        SkinManager.getInstance().unRegister(this);
    }
}
