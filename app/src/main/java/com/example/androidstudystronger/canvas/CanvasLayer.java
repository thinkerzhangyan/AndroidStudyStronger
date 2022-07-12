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
public class CanvasLayer extends View {

    private Paint mPaint;

    /**
     * 圆的宽度
     */
    private int mCircleWidth = 3;

    private int width = 0;
    private int height = 0;

    public CanvasLayer(Context context) {
        this(context, null);
    }

    public CanvasLayer(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CanvasLayer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {


//
//        mPaint.setColor(Color.BLUE);
//        int count=canvas.saveLayer(0,0,1000,1000,mPaint,Canvas.CLIP_SAVE_FLAG);
//        canvas.translate(200, 200);
//        canvas.drawRect(100, 100, 300, 300, mPaint);
//        canvas.restoreToCount(count);
//
//        mPaint.setColor(Color.RED);
//        canvas.drawRect(100, 100, 300, 300, mPaint);


        mPaint.setColor(Color.BLUE);
        int count1=canvas.saveLayer(0,0,1000,1000,mPaint);
        canvas.drawRect(100, 100, 300, 300, mPaint);
        int count2=canvas.saveLayer(0,0,1000,1000,mPaint);
        canvas.drawRect(300, 300, 500, 500, mPaint);
        canvas.restoreToCount(count1);

//        canvas.drawColor(Color.RED);
//        canvas.saveLayer(200,200,700,700,mPaint,Canvas.CLIP_TO_LAYER_SAVE_FLAG);
//        canvas.drawColor(Color.GREEN);
//        canvas.restore();
//        canvas.drawColor(Color.BLACK);

    }

}

