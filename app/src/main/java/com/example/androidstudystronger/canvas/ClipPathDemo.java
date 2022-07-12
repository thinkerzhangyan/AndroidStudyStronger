package com.example.androidstudystronger.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

public class ClipPathDemo extends View {

    Paint mPaint;
    Path mPath;

    public ClipPathDemo(Context context) {
        super(context);
        init();
    }

    public ClipPathDemo(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ClipPathDemo(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);

        mPath = new Path();
        // 移动起点至[50,50]
        mPath.moveTo(50, 50);
        mPath.lineTo(75, 23);
        mPath.lineTo(150, 100);
        mPath.lineTo(80, 110);
        // 闭合路径
        mPath.close();

        postInvalidate();
    }

    protected void onDraw(Canvas canvas) {

        // 在原始画布上绘制蓝色
        canvas.drawColor(Color.GRAY);
        // 按照路径进行裁剪
        canvas.clipPath(mPath);
        // 在裁剪后剩余的画布上绘制红色
        canvas.drawColor(Color.RED);

    }


}