package com.example.processcommunicate.viewpageradapter.indicator;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import com.example.processcommunicate.R;
import com.example.processcommunicate.viewpageradapter.adapter.IndicatorViewAdapter;

public class IndicatorView extends FrameLayout {

    private static final String TAG = "IndicatorView";

    private static final int DEFAULT_HEIGHT = 45;
    private HorizontalScrollView mHorizontalScrollView;


    private IndicatorViewAdapter mIndicatorViewAdapter;


    private LinearLayout mHorizatalViewContainer;

    private int mHeight = DEFAULT_HEIGHT;
    private ViewPager.OnPageChangeListener mPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            ColorTrackTextView current = (ColorTrackTextView) mHorizatalViewContainer.getChildAt(position);
            //设置字体颜色变化
            current.setDirection(ColorTrackTextView.Direction.RIGHT_TO_LEFT);
            current.setProgress(1.0f - positionOffset);
            if (position < (mIndicatorViewAdapter.getCount() - 1)) {
                ColorTrackTextView next = (ColorTrackTextView) mHorizatalViewContainer.getChildAt(position + 1);
                next.setDirection(ColorTrackTextView.Direction.LEFT_TO_RIGHT);
                next.setProgress(positionOffset);
            }
            Log.e(TAG, "onPageScrolled; position -->" + position + ";offset --> " + positionOffset);

            //使得指示器居中
            scrollCurrent(position, positionOffset);

        }

        @Override
        public void onPageSelected(int position) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private void scrollCurrent(int position, float positionOffset) {
        int width = mHorizontalScrollView.getWidth();
        int totalWidth = 0;
        for (int i = 0; i < position; i++) {
            int childWidthWithMargins = getWidthWithMargins(i);
            totalWidth += childWidthWithMargins;
        }

        //View childCurrent = mHorizatalViewContainer.getChildAt(position);
        totalWidth += getWidthWithMargins(position) * positionOffset;
        int scroll = totalWidth - ((width - getWidthWithMargins(position)) / 2);
        mHorizontalScrollView.scrollTo(scroll, 0);
        Log.e(TAG, "scrollTo --> " + scroll);
    }

    private int getWidthWithMargins(int position) {
        View childAt = mHorizatalViewContainer.getChildAt(position);
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) childAt.getLayoutParams();
        return lp.leftMargin + lp.rightMargin + childAt.getWidth();
    }

    public IndicatorView(Context context) {
        this(context, null);
    }

    public IndicatorView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public IndicatorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = LayoutInflater.from(context).inflate(R.layout.layout_indicatorview, this, false);
        addView(view);
        mHorizontalScrollView = findViewById(R.id.horizontal_scroll_view);
        mHorizatalViewContainer = findViewById(R.id.indicator_container);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //将宽度设置为wrap_content，将高度设置成45dp
        //int width = MeasureSpec.getSize(widthMeasureSpec);
        //setMeasuredDimension(width, mHeight);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    public void setAdapter(IndicatorViewAdapter adapter, ViewPager viewPager) {
        if (adapter == null) {
            throw new NullPointerException("adapter can not be null!");
        }
        this.mIndicatorViewAdapter = adapter;
        //初始化所有的子view
        for (int i = 0; i < adapter.getCount(); i++) {
            View viewAt = adapter.getViewAt(i);
            //因为horizatalScrollView只能有一个child，因此将这些指示放入一个viewgroup中
            mHorizatalViewContainer.addView(viewAt);
        }
        //mHorizontalScrollView.addView(mHorizatalViewContainer);

        //设置监听
        viewPager.addOnPageChangeListener(mPageChangeListener);
    }

    public void setMargin(int margin) {
        for (int i = 0; i < mIndicatorViewAdapter.getCount(); ++i) {
            View childView = mHorizatalViewContainer.getChildAt(i);
            LinearLayout.LayoutParams layoutParams =
                    (LinearLayout.LayoutParams) childView.getLayoutParams();
            layoutParams.leftMargin = margin;
            layoutParams.rightMargin = margin;
        }
    }

}
