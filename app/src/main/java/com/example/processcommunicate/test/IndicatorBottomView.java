package com.example.processcommunicate.test;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;

public class IndicatorBottomView extends View {

    private int color;
    private int mHeight;

    public IndicatorBottomView(Context context) {
        this(context, null);
    }

    public IndicatorBottomView(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
    }

    public IndicatorBottomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public void setColor(int c) {
        this.color = c;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.RED);
    }
}
