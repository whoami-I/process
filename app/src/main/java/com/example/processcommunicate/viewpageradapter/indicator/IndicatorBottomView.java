package com.example.processcommunicate.viewpageradapter.indicator;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

public class IndicatorBottomView extends View {

    private int color;

    public IndicatorBottomView(Context context) {
        this(context, null);
    }

    public IndicatorBottomView(Context context,  AttributeSet attrs) {
        super(context, attrs, 0);
    }

    public IndicatorBottomView(Context context,  AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }






    public void setColor(int c) {
        this.color = c;
    }
}
