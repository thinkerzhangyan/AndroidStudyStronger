package com.example.androidstudystronger.custom;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.androidstudystronger.BuildConfig;
import com.example.androidstudystronger.R;

/**
 * 绘制扇形的View
 */
public class SectorView extends View implements ValueAnimator.AnimatorUpdateListener, Animator.AnimatorListener {

    private int mSectorColor = Color.RED;
    private float mTotalTime = 2000;//默认两秒绘制完成
    private float mCurProgress = 0;
    private float mTimeGap = 10;//默认10ms，mCurProgress增加10

    private int mRadius = 0;

    private int mCenterX;
    private int mCenterY;

    private RectF mRec;

    private static int STOP_MODE = 0;
    private static int ClOCK_WISE_MODE = 1;
    private static int ANTI_CLOCK_WISE_MODE = 2;

    private  int drawMode = STOP_MODE;

    private TextPaint mTextPaint;

    private final Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            if (drawMode == ClOCK_WISE_MODE) {
                if (mCurProgress < mTotalTime) {
                    mCurProgress = mCurProgress + mTimeGap;
                    invalidate();
                    postDelayed(mRunnable, (long) mTimeGap);
                } else {
                    mCurProgress = mTotalTime;
                    stopDraw(false);
                    startChangeAlpha();
                }
            } else {
                if (mCurProgress > 0) {
                    mCurProgress = mCurProgress - mTimeGap;
                    invalidate();
                    postDelayed(mRunnable, (long) mTimeGap);
                } else {
                    mCurProgress = 0;
                    stopDraw(true);
                }
            }
        }
    };

    private ValueAnimator mAnimator = ValueAnimator.ofFloat(0, 300);

    public SectorView(Context context) {
        super(context);
        init(null, 0);
    }

    public SectorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public SectorView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.SectorView, defStyle, 0);

        mSectorColor = a.getColor(
                R.styleable.SectorView_sectorColor,
                mSectorColor);
        mTotalTime = a.getFloat(R.styleable.SectorView_totalTime, mTotalTime);

        a.recycle();

        mTextPaint = new TextPaint();
        mTextPaint.setColor(mSectorColor);
        mTextPaint.setStyle(Paint.Style.FILL);
        mTextPaint.setFlags(Paint.ANTI_ALIAS_FLAG);

        mAnimator.addUpdateListener(this);
        mAnimator.addListener(this);
        mAnimator.setDuration(300);
    }

    private void getDrawScope() {
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();

        int contentWidth = getWidth() - paddingLeft - paddingRight;
        int contentHeight = getHeight() - paddingTop - paddingBottom;

        mCenterX = contentWidth / 2;
        mCenterY = contentHeight / 2;

        mRadius = (contentWidth < contentHeight ? contentWidth : contentHeight) / 2;

        mRec = new RectF(mCenterX - mRadius, mCenterY - mRadius,
                mCenterX + mRadius, mCenterY + mRadius);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        getDrawScope();
        float arc = calculateArc(mCurProgress);
        canvas.drawArc(mRec, 270, arc, true, mTextPaint);
    }

    private float calculateArc(float curProgress) {
        if (curProgress <= 0) {
            return 0;
        }
        if (curProgress >= mTotalTime) {
            return 360;
        }
        float arc = (curProgress / mTotalTime) * 360;
        return arc;
    }

    //顺时针绘制 满足长按后，手指离开屏幕，再长按，需要等倒计时结束，进度归零后，重新变化进度。
//    public void startDrawClockWise() {
//        if (drawMode != ClOCK_WISE_MODE && drawMode != ANTI_CLOCK_WISE_MODE) {
//            drawMode = ClOCK_WISE_MODE;
//            removeCallbacks(mRunnable);
//            postDelayed(mRunnable, (long) mTimeGap);
//        }
//    }

    //顺时针绘制 满足长按后，手指离开屏幕，再长按，会当前倒计时的进度上，继续增加进度。
    public void startDrawClockWise() {
        if (drawMode != ClOCK_WISE_MODE && !mAnimator.isRunning()) {
            setAlpha(1);
            drawMode = ClOCK_WISE_MODE;
            removeCallbacks(mRunnable);
            postDelayed(mRunnable, (long) mTimeGap);
        }
    }

    //逆时针绘制
    public void startAntiClockWise() {
        if (mCurProgress > 0 && mCurProgress != mTotalTime && drawMode != ANTI_CLOCK_WISE_MODE) {
            setAlpha(1);
            drawMode = ANTI_CLOCK_WISE_MODE;
            removeCallbacks(mRunnable);
            postDelayed(mRunnable, (long) mTimeGap);
        }
    }

    //停止绘制
    public void stopDraw(boolean resetMode) {
        if (resetMode) {
            drawMode = STOP_MODE;
        }
        removeCallbacks(mRunnable);
        if (BuildConfig.DEBUG) {
            Log.d("SectorView", "stopDraw  CurProgress:   " + mCurProgress);
        }
    }

    //重置
    //UP事件的时候调用这个方法将mode设置为STOP_MODE；
    //不能在长按2s后的stop里面将mode设置为STOP_MODE，因为这个时候如果用户仍然在长按中，会再次调用startDrawClockWise()方法，这样就会重新开始倒计时了；
    public void reset() {
        removeCallbacks(mRunnable);
        //UP事件的时候，调用了reset()方法后，会接着调用startAntiClockWise()来进行倒计时，如果检测到mCurProgress >= mTotalTime说明是整个计时已经完成了，这个时候是不要倒计时的效果的，所以这个时候将mCurProgress重置为0，这样startAntiClockWise()方法就不会生效;
        if (mCurProgress >= mTotalTime) {
            mCurProgress = 0;
        }
        drawMode = STOP_MODE;
    }


    private void startChangeAlpha() {
        mAnimator.start();
    }

    @Override
    public void onAnimationUpdate(ValueAnimator animation) {
        float alpha = animation.getAnimatedFraction();
        setAlpha(1 - alpha);
        if (BuildConfig.DEBUG) {
            Log.d("SectorView", "onAnimationUpdate Alpha:   " + alpha);
        }
    }

    @Override
    public void onAnimationStart(Animator animation) {

    }

    @Override
    public void onAnimationEnd(Animator animation) {
        //防止出现长按，进度完成，控件渐隐消失，然后再长按，控件闪一下的问题
        mCurProgress = 0;
        invalidate();
    }

    @Override
    public void onAnimationCancel(Animator animation) {

    }

    @Override
    public void onAnimationRepeat(Animator animation) {

    }
}