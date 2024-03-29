package com.example.androidstudystronger.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Region;
import android.util.AttributeSet;
import android.view.View;

public class ClipRectDemo extends View {

    Paint mPaint;
    Path mPath;

    public ClipRectDemo(Context context) {
        super(context);
        init();
    }

    public ClipRectDemo(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ClipRectDemo(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(6);
        mPaint.setTextSize(16);
        mPaint.setTextAlign(Paint.Align.RIGHT);

        mPath = new Path();
    }

    protected void onDraw(Canvas canvas) {

        canvas.drawColor(Color.GRAY);

        canvas.save();
        drawScene(canvas);
        canvas.restore();

//        //DIFFERENCE,以第一次剪裁的为基础，在此基础上擦除第二次剪裁的部分(裁出第一次操作，并擦除掉第二次操作)
        canvas.save();
        canvas.translate(280, 0);
        canvas.clipRect(0, 0, 150, 150);
        canvas.clipRect(100, 100, 250, 250, Region.Op.DIFFERENCE);
        drawScene(canvas);
        canvas.restore();


        //INTERSECT,交叉,保留第一次剪裁和第二次剪裁重叠的部分
        canvas.save();
        canvas.translate(0, 280);
        canvas.clipRect(0, 0, 150, 150);
        canvas.clipRect(100, 100, 250, 250, Region.Op.INTERSECT);
        drawScene(canvas);
        mPaint.setColor(Color.WHITE);
        canvas.drawText("INTERSECT", 200, 200, mPaint);
        canvas.restore();

//        //REPLACE,替换,用第二次剪裁替换第一次剪裁(相当于只做了第二次剪裁,丢弃第一次剪裁)
//        //分别放开下面三种操作有助于理解
        canvas.save();
        canvas.translate(200, 250);
        //1.第一种操作
        canvas.clipRect(0, 0, 150, 150);
        canvas.clipRect(100, 100, 250, 250, Region.Op.REPLACE);
        //2.第二次操作
//        canvas.clipRect(200, 200, 500, 500);
//        canvas.clipRect(0, 0, 300, 300, Region.Op.REPLACE);
        //3.第三种操作
//        canvas.clipRect(0, 0, 300, 300);
        drawScene(canvas);
        canvas.restore();

//        //REVERSE_DIFFERENCE,以第二次剪裁的为基础，在此基础上擦除第一次剪裁的部分(裁出第二次操作，并擦除掉第一次操作)
        canvas.save();
        canvas.translate(0, 450);
        canvas.clipRect(0, 0, 150, 150);
        canvas.clipRect(100, 100, 250, 250, Region.Op.REVERSE_DIFFERENCE );
        drawScene(canvas);
        canvas.restore();

//        //UNION,合并,即保留第一次剪裁和第二次剪裁的并集
        canvas.save();
        canvas.translate(275, 600);
        canvas.clipRect(0, 0, 150, 150);
        canvas.clipRect(100, 100, 250, 250, Region.Op.UNION);
        drawScene(canvas);
        canvas.restore();

        //XOR,异或操作，做出第一次剪裁，并且做出第二次剪裁，在此基础上擦除掉重叠的部分(留下两次操作不重叠的部分)
        canvas.save();
        canvas.translate(275, 1000);
        canvas.clipRect(0, 0, 150, 150);
        canvas.clipRect(100, 100, 250, 250, Region.Op.XOR);
        drawScene(canvas);
        canvas.restore();

    }

    private void drawScene(Canvas canvas) {

        mPaint.setColor(Color.BLUE);
        canvas.drawRect(0, 0, 150, 150, mPaint);

        mPaint.setColor(Color.WHITE);
        mPaint.setTextSize(40);
        canvas.drawText("A", 70, 70, mPaint);

        mPaint.setColor(Color.GREEN);
        canvas.drawRect(100, 100, 250, 250, mPaint);

        mPaint.setColor(Color.WHITE);
        canvas.drawText("B", 175, 175, mPaint);

        mPaint.setColor(Color.RED);
        canvas.drawRect(100, 100, 150, 150, mPaint);

    }
}