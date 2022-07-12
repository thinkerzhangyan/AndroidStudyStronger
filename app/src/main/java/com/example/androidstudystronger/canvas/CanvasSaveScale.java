package com.example.androidstudystronger.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
*@author zhangyan
*@date 2017/10/25
*/
public class CanvasSaveScale extends View {

    private Paint mPaint;

    /**
     * 圆的宽度
     */
    private int mCircleWidth = 3;

    private int width = 0;
    private int height = 0;

    public CanvasSaveScale(Context context) {
        this(context, null);
    }

    public CanvasSaveScale(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CanvasSaveScale(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {

        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.CYAN);
        canvas.drawRect(0,0,100,100,mPaint);
        canvas.save();
        canvas.scale(2.5f,2.5f);
        mPaint.setColor(Color.BLACK);
        canvas.drawRect(0,0,100,100,mPaint);
        canvas.restore();
        mPaint.setColor(Color.YELLOW);
        canvas.drawRect(0,0,200,200,mPaint);


    }

}

