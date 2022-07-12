package com.example.androidstudystronger.canvas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

public class XfermodeView extends View {

    private Context context;

    //屏幕宽高
    private int screenW;

    //绘制的图片宽高
    private int width = 200;
    private int height = 200;

    //上层SRC的Bitmap和下层Dst的Bitmap
    private Bitmap srcBitmap, dstBitmap;

    public XfermodeView(Context context) {
        this(context, null);
    }

    public XfermodeView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public XfermodeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        this.context = context;

        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(dm);

        screenW = dm.widthPixels;

        //实例化两个Bitmap
        srcBitmap = makeSrc(width, height);
        dstBitmap = makeDst(width, height);
    }

    @Override
    public void onMeasure(int width, int height) {
        setMeasuredDimension(screenW, 8000);
    }

    //定义一个绘制圆形 Bitmap 的方法
    private Bitmap makeDst(int w, int h) {
        Bitmap bm = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bm);
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
        p.setColor(0xFFE54261);
        c.drawOval(new RectF(0, 0, w * 3 / 4, h * 3 / 4), p);
        return bm;
    }

    //定义一个绘制矩形的 Bitmap 的方法
    private Bitmap makeSrc(int w, int h) {
        Bitmap bm = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bm);
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
        p.setColor(0xFF3097F3);
        c.drawRect(w / 3, h / 3, w * 19 / 20, h * 19 / 20, p);
        return bm;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setFilterBitmap(false);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(48.0f);

        for (PorterDuff.Mode mode : PorterDuff.Mode.class.getEnumConstants()) {

            Log.d("modes", mode.name());
            canvas.drawText(mode.name(), 10, 50, paint);
            canvas.drawBitmap(srcBitmap, (screenW / 3 - width) / 2, 100, paint);
            canvas.drawBitmap(dstBitmap, (screenW / 3 - width) / 2 + screenW / 3, 100, paint);

            int sc = canvas.saveLayer(0, 0, screenW, 300, null, Canvas.ALL_SAVE_FLAG);

            canvas.drawBitmap(dstBitmap, (screenW / 3 - width) / 2 + screenW / 3 * 2,
                    100, paint);     //绘制

            //设置Paint的Xfermode
            paint.setXfermode(new PorterDuffXfermode(mode));
            canvas.drawBitmap(srcBitmap, (screenW / 3 - width) / 2 + screenW / 3 * 2,
                    100, paint);
            paint.setXfermode(null);

            // 还原画布
            canvas.restoreToCount(sc);

            canvas.translate(0, 400);

        }
    }
}