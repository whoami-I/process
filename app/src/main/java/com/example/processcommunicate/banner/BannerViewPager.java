package com.example.processcommunicate.banner;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

import com.example.processcommunicate.log.Log;

import java.lang.reflect.Field;

public class BannerViewPager extends ViewPager {
    static final String TAG = "BannerViewPager";
    static final int NEXT_PAGE = 0x11;
    static final int DEFAULT_DURATION = 800;
    private BannerAdapter mBannerAdapter;
    private BannerScroller mBannerScroller;
    private Handler mH = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == NEXT_PAGE) {
                int currentItem = getCurrentItem();
                setCurrentItem(currentItem + 1);
                Log.e(TAG, "");
                startRoll();
            }
        }
    };

    private void startRoll() {
        Message message = Message.obtain();
        message.what = NEXT_PAGE;
        mH.sendMessageDelayed(message, 3000);
    }


    public BannerViewPager(@NonNull Context context) {

        this(context, null);
    }

    public BannerViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mBannerScroller = new BannerScroller(context);
        //利用反射将mScroller注入进ViewPager中
        Field scrollField = null;
        try {
            scrollField = ViewPager.class.getDeclaredField("mScroller");
            scrollField.setAccessible(true);
            scrollField.set(this, mBannerScroller);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    public void setAdapter(BannerAdapter adapter) {
        this.mBannerAdapter = adapter;
        if (adapter.getCount() < 4) {
            setOffscreenPageLimit(0);
        } else {
            setOffscreenPageLimit(1);
        }
        super.setAdapter(new BannerPagerAdapter(mBannerAdapter));

    }

    public void setDuration(int milli) {
        if (milli <= 0) {
            mBannerScroller.setDuration(DEFAULT_DURATION);
        } else {
            mBannerScroller.setDuration(milli);
        }
    }

    public void attach() {
        if (mBannerAdapter == null) {
            throw new RuntimeException("can not roll with mBannerAdapter == null");
        }
        startRoll();
    }

    public void detach() {
        if (mH != null) {
            mH.removeCallbacksAndMessages(null);
            mH = null;
        }
    }
}
