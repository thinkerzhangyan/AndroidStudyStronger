package com.example.androidstudystronger.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
*@author zhangyan
*@date 2017/10/25
*/
public class CanvasAndPaint extends View {

    private Paint mPaint;



    public CanvasAndPaint(Context context) {
        this(context, null);
    }

    public CanvasAndPaint(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CanvasAndPaint(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {

        mPaint.setAntiAlias(true);//取消锯齿
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);
        mPaint.setColor(Color.BLACK);


        //画点
        canvas.drawPoint(10,10,mPaint);

        mPaint.setStrokeWidth(2);

        //画线
        canvas.drawLine(10,20,420,20,mPaint);

        //画多条线
        float[] floats = new float[]{30,30,40,40,50,50,60,60};
        canvas.drawLines(floats,mPaint);


        //绘制矩形
        canvas.drawRect(30,100,230,200,mPaint);

        //绘制圆角矩形
        canvas.drawRoundRect(240,100,440,200,10,10,mPaint);


        //Android 绘图基础三 paint使用   https://www.jianshu.com/p/1fc5f6d771ef
        //Android Paint 浅坑——Style模式 https://blog.csdn.net/ziwang_/article/details/72857650
        mPaint.setStrokeWidth(20);
        mPaint.setColor(Color.RED);
        //画圆形
        canvas.drawCircle(540+100,150+300,100,mPaint);
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(1);
        canvas.drawCircle(540+100,150+300,100,mPaint);

        mPaint.setStrokeWidth(2);
        mPaint.setColor(Color.BLACK);

        //绘制弧度
        float x = 30;
        float y = 230;

        RectF rec = new RectF( x, y,
                x+200,y+200);


        canvas.drawRect(rec, mPaint);//画矩形
        canvas.drawArc(rec,360,180,true,mPaint);

        mPaint.setStrokeWidth(2);

        canvas.drawRect(x,y+220,x+300,y+720, mPaint);//画矩形
        canvas.drawArc(x,y+220,x+300,y+720,360,180,true,mPaint);


        //绘制文字
        mPaint.setStrokeWidth(1);
        mPaint.setTextSize(50);
        canvas.drawText("Android",30,1000,mPaint);


        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawPosText("And",new float[]{30,1050,80,1100,110,1150},mPaint);

        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.BLACK);

        canvas.drawPosText("And",new float[]{30,1050+100,80,1100+100,110,1150+100},mPaint);

        //绘制路径
        Path path = new Path();

        path.moveTo(600, 100);
        path.lineTo(800, 150);
        path.lineTo(850, 300);
        path.lineTo(1000, 900);

        canvas.drawPath(path,mPaint);



    }
}

