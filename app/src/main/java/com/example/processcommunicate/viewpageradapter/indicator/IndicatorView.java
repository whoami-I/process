package com.example.processcommunicate.viewpageradapter.indicator;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.processcommunicate.R;
import com.example.processcommunicate.viewpageradapter.adapter.IndicatorViewAdapter;

public class IndicatorView extends FrameLayout {

    private static final String TAG = "IndicatorView";
    //这些默认的配置可以放在xml配置文件中，同时自定义属性让用户定义值
    private static final int DEFAULT_HEIGHT = 45;
    private static final int DEFAULT_IndicatorBottomViewHeight = 5;
    private static final int DEFAULT_IndicatorBottomView_COLOR = Color.parseColor("#000000");
    private HorizontalScrollView mHorizontalScrollView;


    private IndicatorViewAdapter mIndicatorViewAdapter;


    private LinearLayout mHorizatalViewContainer;

    private IndicatorBottomView mIndicatorBottomView;

    private FrameLayout mBottomContainer;
    private int mHeight = DEFAULT_HEIGHT;

    private int mIndicatorBottomViewHeight = DEFAULT_IndicatorBottomViewHeight;
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


            scrollCurrent2(position, positionOffset);
            //移动底部指示器,使用paddingLeft方式来移动指示器
            scrollBottomIndicator(position, positionOffset);


        }

        @Override
        public void onPageSelected(int position) {
            Log.e(TAG, "mIndicatorBottomView width --> " + mIndicatorBottomView.getWidth());
            Log.e(TAG, "mHorizatalViewContainer width --> " + mHorizatalViewContainer.getWidth());
            Log.e(TAG, "mHorizontalScrollView width --> " + mHorizontalScrollView.getWidth());
            Log.e(TAG, "IndicatorView width --> " + getWidth());
            Log.e(TAG, "mBottomContainer width --> " + mBottomContainer.getWidth());
//            Log.e(TAG, "position --> " + position);
//            //使得指示器居中
//            int width = mHorizontalScrollView.getWidth();
//            int totalWidth = 0;
//            for (int i = 0; i < position; ++i) {
//
//                int childWidthWithMargins = getWidthWithMargins(i);
//                totalWidth += childWidthWithMargins;
//            }
//            int scroll = totalWidth - ((width - getWidthWithMargins(position)) / 2);
//            mHorizontalScrollView.scrollTo(scroll, 0);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private void scrollBottomIndicator(int position, float positionOffset) {
        View childAt = mHorizatalViewContainer.getChildAt(position);
        int width = getWidthWithMargins(position);
        int center = (childAt.getLeft() + childAt.getRight()) / 2;
        int marginLeft = center - mIndicatorBottomView.getWidth() / 2;

        int centerNext = 0;
        int totalDistance = 0;
        if (position != mIndicatorViewAdapter.getCount() - 1) {
            View childNext = mHorizatalViewContainer.getChildAt(position + 1);
            int widthNext = getWidthWithMargins(position + 1);
            centerNext = (childNext.getLeft() + childNext.getRight()) / 2;

            totalDistance = centerNext - center;
            //int marginLeft = center - mIndicatorBottomView.getWidth() / 2;
        }
        int marginLeftScroll = (int) (marginLeft + totalDistance * positionOffset);
        FrameLayout.LayoutParams lp = (LayoutParams) mIndicatorBottomView.getLayoutParams();
        lp.leftMargin = marginLeftScroll;
        mIndicatorBottomView.setLayoutParams(lp);
    }

    /**
     * 这个方法没有考虑到不同长度的tab
     */
    @Deprecated
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

    /**
     * 解决了上面scrollCurrent(int position, float positionOffset)方法的bug
     */
    private void scrollCurrent2(int position, float positionOffset) {
        if (positionOffset == 0.0f) {
            return;
        }
        int width = mHorizontalScrollView.getWidth();
        int totalWidth = 0;
        for (int i = 0; i < position; i++) {
            int childWidthWithMargins = getWidthWithMargins(i);
            totalWidth += childWidthWithMargins;
        }
        totalWidth += getWidthWithMargins(position) * positionOffset;
        //滑到最后之所以不会溢出的原因是前面已经对positionOffset做了判断，返回
        int scroll = totalWidth - ((width - getWidthWithMargins(position + 1)) / 2);
        mHorizontalScrollView.scrollTo(scroll, 0);


    }

    private int gerChildWidth(int position) {
        View childAt = mHorizatalViewContainer.getChildAt(position);
        return childAt.getWidth();
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
        mBottomContainer = findViewById(R.id.bottom_container);
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


        //添加底部指示器栏
        mIndicatorBottomView = new IndicatorBottomView(this.getContext());
        mIndicatorBottomView.setColor(DEFAULT_IndicatorBottomView_COLOR);


        FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT
                , mIndicatorBottomViewHeight,
                Gravity.BOTTOM);
        layoutParams2.topMargin = 50;
        layoutParams2.width = 30;
        mBottomContainer.addView(mIndicatorBottomView, layoutParams2);


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
