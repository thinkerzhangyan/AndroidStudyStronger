package com.example.androidstudystronger.custom;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Interpolator;

import androidx.annotation.Nullable;

/**
 * @Description: 阅读家PKBarView    直播间PK、新专题PK、阅读家业务都在使用此控件
 * 只是PK条
 * @author: xiexiangyu
 * @data: 2019-01-22 上午11:37
 * @version: v54
 * @tips: 渐显动效 + 增长动效
 * @module vote
 * @blame xiexiangyu
 */
public class ReaderPkBarView extends AbstractPkBarView {

    private static int DEFAULT_INCREASE_ANIMATION_DURATION = 633;
    private static int DEFAULT_OVERSHOOT_ANIMATION_DURATION = 300;
    private static float DEFAULT_OVERSHOOT_INTERCEPTOR_VALUE = 0.33f;

    private static int ANIMATION_STEP_IDLE = 0;
    private static int ANIMATION_STEP_INCREASE = 1;
    private static int ANIMATION_STEP_ON_CLICK = 2;

    /**
     * 控制动画执行步骤 默认为0
     * 出现动画阶段为1
     * 点击动画阶段为2
     * 消失动画阶段为3   暂无使用
     */
    private int mAnimationStep;

    /**
     * PK条最小宽度
     */
    private int mMinWidth;

    /**
     * PK条总宽度
     */
    private int mTotalWidth;

    private float mLeftCurrentValue;
    private float mRightCurrentValue;

    private ValueAnimator mIncreaseAnimator;
    private ValueAnimator mOnClickAnimator;

    private boolean hasInCreaseAnimation;

    private boolean hasOnClickAnimation;

    protected boolean isSelectedLeft = true;

    public ReaderPkBarView(Context context) {
        this(context, null);
    }

    public ReaderPkBarView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ReaderPkBarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mAnimationStep = ANIMATION_STEP_IDLE;
    }

    /**
     * 是否设置增长动画
     *
     * @param hasIncreaseAnimation
     */
    public void hasInCreaseAnimation(boolean hasIncreaseAnimation) {
        hasInCreaseAnimation = hasIncreaseAnimation;
    }

    /**
     * 是否设置点击动画
     *
     * @param hasClickAnimation
     */
    public void hasOnClickAnimation(boolean hasClickAnimation) {
        hasOnClickAnimation = hasClickAnimation;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mMinWidth = getMeasuredHeight();
        mTotalWidth = getMeasuredWidth();
    }

    @Override
    protected void customPkOnDraw(Canvas canvas) {
        if (mAnimationStep == ANIMATION_STEP_IDLE || mAnimationStep == ANIMATION_STEP_INCREASE) {
            // 如果没有增长动画需要直接算出值
            if (!hasInCreaseAnimation) {
                mLeftCurrentValue = mRatio * getRatioTotalWidth() + mMinWidth;
                mRightCurrentValue = (1 - mRatio) * getRatioTotalWidth() + mMinWidth;
            }
            //画最左侧pk条
            paintSingleRoundRect(canvas, mLeftColor, 0, 0, mLeftCurrentValue, canvas.getHeight(), true);
            //画最右侧pk条
            paintSingleRoundRect(canvas, mRightColor, getWidth() - mRightCurrentValue, 0, canvas.getWidth(), canvas.getHeight(), false);
        }

        if (mAnimationStep == ANIMATION_STEP_ON_CLICK) {
            //画最左侧pk条
            paintSingleRoundRect(canvas, mLeftColor, 0, 0, mLeftCurrentValue, canvas.getHeight(), true);
            //画最右侧pk条
            paintSingleRoundRect(canvas, mRightColor, mLeftCurrentValue + mMiddleSpace, 0, canvas.getWidth(), canvas.getHeight(), false);
        }
    }

    /**
     * 获取与比例相关的总体可绘长度
     *
     * @return
     */
    private int getRatioTotalWidth() {
        return mTotalWidth - mMiddleSpace - 2 * mMinWidth;
    }

    private void paintSingleRoundRect(Canvas canvas, int color, float left, float top, float right, float bottom, boolean isLeft) {
        mPaint.setColor(color);
        mRect.left = left;
        mRect.top = top;
        mRect.right = right;
        mRect.bottom = bottom;
        LinearGradient linearGradient = null;
        Path path = new Path();
        if (isLeft) {
            mRect.right = bottom;
            path.addArc(mRect, 90, 180);
            path.lineTo(right, top);
            path.lineTo(right - mOffsetSpace, bottom);

            linearGradient = new LinearGradient(left, top, right, top, isSelectedLeft ? mLeftSelectedStartColor : mLeftUnSelectedStartColor, isSelectedLeft ? mLeftSelectedEndColor : mLeftUnSelectedEndColor, Shader.TileMode.CLAMP);
            path.close();
            if (mIsGradient) {
                mPaint.setShader(linearGradient);
            }
            canvas.drawPath(path, mPaint);

            Rect rect = new Rect();
            mLeftPaint.getTextBounds("2345", 0, "2345".length(), rect);

            float x = bottom;
            float y = bottom / 2 - rect.centerY();
            //把文本内容绘制在进度条的中间位置
            canvas.drawText("2345", x, y, mLeftPaint);
        } else {
            mRect.left = right - bottom;
            path.addArc(mRect, 90, -180);
            path.lineTo(left + mOffsetSpace, top);
            path.lineTo(left, bottom);

            linearGradient = new LinearGradient(left, top, right, top, isSelectedLeft ? mRightUnSelectedStartColor : mRightSelectedStartColor, isSelectedLeft ? mRightUnSelectedEndColor : mRightSelectedEndColor, Shader.TileMode.CLAMP);
            path.close();
            if (mIsGradient) {
                mPaint.setShader(linearGradient);
            }
            canvas.drawPath(path, mPaint);

            Rect rect = new Rect();
            mRightPaint.getTextBounds("2345", 0, "2345".length(), rect);

            float x = right - bottom;
            float y = bottom / 2 - rect.centerY();
            //把文本内容绘制在进度条的中间位置
            canvas.drawText("2345", x, y, mRightPaint);
        }
    }

    @Override
    public void initAnimation() {
        if (hasInCreaseAnimation) {
            initIncreaseAnimation();
        }
        if (hasOnClickAnimation) {
            initOnClickAnimation();
        }
    }

    public void startIncreaseAnimation() {
        if (null != mIncreaseAnimator) {
            mAnimationStep = ANIMATION_STEP_INCREASE;
            mIncreaseAnimator.start();
        }
    }

    public void startOnClickAnimation() {
        if (null != mOnClickAnimator) {
            mAnimationStep = ANIMATION_STEP_ON_CLICK;
            mOnClickAnimator.start();
        }
    }

    /**
     * 初始化PK条增长动效
     */
//    private void initIncreaseAnimation() {
//        mIncreaseAnimator = ValueAnimator.ofFloat(0, 1);
//        mIncreaseAnimator.setDuration(DEFAULT_INCREASE_ANIMATION_DURATION);
//        mIncreaseAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                mLeftCurrentValue = ((float) animation.getAnimatedValue() * mRatio) * getRatioTotalWidth() + mMinWidth;
//                mRightCurrentValue = ((float) animation.getAnimatedValue() * (1 - mRatio)) * getRatioTotalWidth() + mMinWidth;
//                invalidate();
//            }
//        });
//    }

    /**
     * 初始化PK条增长动效
     */
    private void initIncreaseAnimation() {
        mIncreaseAnimator = ValueAnimator.ofFloat(0, 633f);
        mIncreaseAnimator.setInterpolator(new AccelerateInterpolator());
        mIncreaseAnimator.setDuration(DEFAULT_INCREASE_ANIMATION_DURATION);
        mIncreaseAnimator.addUpdateListener(animation -> {
            if ((float) animation.getAnimatedValue() <= 433) {
                mLeftCurrentValue = ((float) ((float) animation.getAnimatedValue() / 433f) * mRatio * 1.14f) * getRatioTotalWidth() + mMinWidth;
                mRightCurrentValue = ((float) ((float) animation.getAnimatedValue() / 433f) * (1 - mRatio * 1.14f)) * getRatioTotalWidth() + mMinWidth;
            } else {

//                double a = (float) animation.getAnimatedValue() - 660;
//
//                double b = a / 2000f;
//
//                double c = b * 0.14;
//
//                double d = 1.14 - c;

                double d = 1.14 - (float) ((float) animation.getAnimatedValue() - 433) / 200f * 0.14f;

                mLeftCurrentValue = (float) (d * mRatio * getRatioTotalWidth() + mMinWidth);

                mRightCurrentValue = (float) ((1 - mRatio * d) * getRatioTotalWidth() + mMinWidth);

            }
            invalidate();
        });
    }

    /**
     * 初始化点击回弹动效
     */
    public void initOnClickAnimation() {
        float leftEndValue;
        if (mClickPositive) {
            mRatio = calculateRatio(mLeftCount + 1, mRightCount);
            leftEndValue = mRatio * getRatioTotalWidth() + mMinWidth;
            mOnClickAnimator = ValueAnimator.ofFloat(mLeftCurrentValue, leftEndValue + mMinWidth, leftEndValue);
        } else {
            mRatio = calculateRatio(mLeftCount, mRightCount + 1);
            leftEndValue = mRatio * getRatioTotalWidth() + mMinWidth;
            mOnClickAnimator = ValueAnimator.ofFloat(mLeftCurrentValue, leftEndValue - mMinWidth, leftEndValue);
        }
        mOnClickAnimator.setDuration(DEFAULT_OVERSHOOT_ANIMATION_DURATION);
        mOnClickAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                mLeftCurrentValue = (float) valueAnimator.getAnimatedValue();
                invalidate();
            }
        });

        mOnClickAnimator.setInterpolator(new Interpolator() {
            @Override
            public float getInterpolation(float input) {
                if (input < DEFAULT_OVERSHOOT_INTERCEPTOR_VALUE) {
                    return 1.5f * input;
                } else {
                    return 0.75f * input + 0.25f;
                }
            }
        });

    }

    @Override
    public void stopAnimation() {
        if (mIncreaseAnimator != null) {
            mIncreaseAnimator.cancel();
        }
        if (mOnClickAnimator != null) {
            mOnClickAnimator.cancel();
        }
        mAnimationStep = ANIMATION_STEP_IDLE;
        clearAnimation();
    }


    public void setInitNum(int leftNum, int rightNum, boolean left) {
        isSelectedLeft = left;
        mLeftPaint.setColor(isSelectedLeft ? mLeftTipSelectedColor : mLeftTipUnSelectedColor);
        mRightPaint.setColor(isSelectedLeft ? mRightTipUnSelectedColor : mRightTipSelectedColor);
        super.setInitNum(leftNum, rightNum);
    }
}
