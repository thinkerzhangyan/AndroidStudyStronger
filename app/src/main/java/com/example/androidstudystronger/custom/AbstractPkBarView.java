package com.example.androidstudystronger.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.example.androidstudystronger.R;

import androidx.annotation.Nullable;

/**
 * @Description: PK条抽象类，负责初始化和处理共有逻辑
 * @author: xiexiangyu
 * @data: 2019-01-22 上午10:26
 * @version: v54
 * @tips: 动画 分割线属性等放在子类做处理
 * @module vote
 * @blame 盛惠珍
 */
public abstract class AbstractPkBarView extends View {

    /**
     * 默认圆角角度
     */
    public static final int DEFAULT_CORNER = 12;
    /**
     * 默认pk条比例
     */
    public static final float DEFAULT_RATIO = 0.5f;
    /**
     * 默认中间间隔
     */
    public static final int DEFAULT_MIDDLE_SPACE = 6;
    /**
     * 默认上下两边的差值
     */
    public static final int DEFAULT_OFFSET_SPACE = 0;
    /**
     * 默认提示文字大小
     */
    public static final int DEFAULT_TIP_SIZE = 11;

    /**
     * 默认左侧颜色
     */
    public static final int DEFAULT_LEFT_COLOR = Color.RED;
    /**
     * 默认右侧颜色
     */
    public static final int DEFAULT_RIGHT_COLOR = Color.BLUE;

    protected Paint mPaint;

    protected Paint mLeftPaint;

    protected Paint mRightPaint;
    /**
     * 使用圆角矩形当作pk条
     */
    protected RectF mRect;
    /**
     * pk条的圆角角度
     */
    protected int mCorner;

    /**
     * 左侧pk条所占比例 0~1之间
     */
    protected float mRatio;

    /**
     * 两个pk条的间隔
     */
    protected int mMiddleSpace;

    /**
     * pk条上下两边的差值
     */
    protected int mOffsetSpace;

    /**
     * 左侧pk条颜色
     */
    protected int mLeftColor;
    /**
     * 右侧pk条颜色
     */
    protected int mRightColor;

    /**
     * 左侧pk条提示文字颜色
     */
    protected int mLeftTipSelectedColor;

    /**
     * 右侧pk条提示文字颜色
     */
    protected int mRightTipUnSelectedColor;

    /**
     * 左侧pk条提示文字颜色
     */
    protected int mLeftTipUnSelectedColor;

    /**
     * 右侧pk条提示文字颜色
     */
    protected int mRightTipSelectedColor;

    /**
     * 左侧pk条提示文字大小
     */
    protected int mLeftTipSize;

    /**
     * 右侧pk条提示文字大小
     */
    protected int mRightTipSize;

    /**
     * 是否开启进度条的背景色渐变
     */
    protected boolean mIsGradient;

    /**
     * 左侧pk条渐变开始颜色
     */
    protected int mLeftSelectedStartColor;

    /**
     * 左侧pk条渐变结束颜色
     */
    protected int mLeftSelectedEndColor;

    /**
     * 右侧pk条渐变开始颜色
     */
    protected int mRightSelectedStartColor;

    /**
     * 右侧pk条渐变结束颜色
     */
    protected int mRightSelectedEndColor;

    /**
     * 左侧pk条渐变开始颜色
     */
    protected int mLeftUnSelectedStartColor;

    /**
     * 左侧pk条渐变结束颜色
     */
    protected int mLeftUnSelectedEndColor;

    /**
     * 右侧pk条渐变开始颜色
     */
    protected int mRightUnSelectedStartColor;

    /**
     * 右侧pk条渐变结束颜色
     */
    protected int mRightUnSelectedEndColor;

    /**
     * 扫光的Image
     */
    protected int mLightImage;

    protected int mLeftCount;

    protected int mRightCount;

    protected boolean mClickPositive;

    public AbstractPkBarView(Context context) {
        this(context, null);
    }

    public AbstractPkBarView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AbstractPkBarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    /**
     * 初始化view
     *
     * @param context
     * @param attrs
     */
    private void initView(Context context, AttributeSet attrs) {
        mPaint = new Paint();
        //设置画笔无锯齿
        mPaint.setAntiAlias(true);

        mRect = new RectF();
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.AbstractPkBarView);

        try {
            mCorner = ta.getDimensionPixelSize(R.styleable.AbstractPkBarView_corners, DEFAULT_CORNER);
            mRatio = ta.getFloat(R.styleable.AbstractPkBarView_ratio, DEFAULT_RATIO);
            mMiddleSpace = ta.getDimensionPixelSize(R.styleable.AbstractPkBarView_middle_space, DEFAULT_MIDDLE_SPACE);
            mOffsetSpace = ta.getDimensionPixelSize(R.styleable.AbstractPkBarView_offset_space, DEFAULT_OFFSET_SPACE);
            mLeftColor = ta.getColor(R.styleable.AbstractPkBarView_left_color, DEFAULT_LEFT_COLOR);
            mRightColor = ta.getColor(R.styleable.AbstractPkBarView_right_color, DEFAULT_RIGHT_COLOR);
            mLeftTipSelectedColor = ta.getColor(R.styleable.AbstractPkBarView_left_tip_selected_color, DEFAULT_LEFT_COLOR);
            mRightTipSelectedColor = ta.getColor(R.styleable.AbstractPkBarView_right_tip_selected_color, DEFAULT_RIGHT_COLOR);
            mLeftTipUnSelectedColor = ta.getColor(R.styleable.AbstractPkBarView_left_tip_unselected_color, DEFAULT_LEFT_COLOR);
            mRightTipUnSelectedColor = ta.getColor(R.styleable.AbstractPkBarView_right_tip_unselected_color, DEFAULT_RIGHT_COLOR);
            mLeftTipSize = ta.getDimensionPixelSize(R.styleable.AbstractPkBarView_left_tip_size, DEFAULT_TIP_SIZE);
            mRightTipSize = ta.getDimensionPixelSize(R.styleable.AbstractPkBarView_right_tip_size, DEFAULT_TIP_SIZE);
            mIsGradient = ta.getBoolean(R.styleable.AbstractPkBarView_is_gradient, false);
            mLeftSelectedStartColor = ta.getColor(R.styleable.AbstractPkBarView_left_selected_start_color, DEFAULT_LEFT_COLOR);
            mLeftSelectedEndColor = ta.getColor(R.styleable.AbstractPkBarView_left_selected_end_color, DEFAULT_LEFT_COLOR);
            mRightSelectedStartColor = ta.getColor(R.styleable.AbstractPkBarView_right_selected_start_color, DEFAULT_RIGHT_COLOR);
            mRightSelectedEndColor = ta.getColor(R.styleable.AbstractPkBarView_right_selected_end_color, DEFAULT_RIGHT_COLOR);
            mLeftUnSelectedStartColor = ta.getColor(R.styleable.AbstractPkBarView_left_unselected_start_color, DEFAULT_LEFT_COLOR);
            mLeftUnSelectedEndColor = ta.getColor(R.styleable.AbstractPkBarView_left_unselected_end_color, DEFAULT_LEFT_COLOR);
            mRightUnSelectedStartColor = ta.getColor(R.styleable.AbstractPkBarView_right_unselected_start_color, DEFAULT_RIGHT_COLOR);
            mRightUnSelectedEndColor = ta.getColor(R.styleable.AbstractPkBarView_right_unselected_end_color, DEFAULT_RIGHT_COLOR);
            mLightImage = ta.getResourceId(R.styleable.AbstractPkBarView_lightImage, R.drawable.saoguang);
        } finally {
            ta.recycle();
        }

        mLeftPaint = new Paint();
        mLeftPaint.setAntiAlias(true);
        mLeftPaint.setColor(mLeftTipSelectedColor);
        mLeftPaint.setTextSize(mLeftTipSize);

        mRightPaint = new Paint();
        mRightPaint.setAntiAlias(true);
        mRightPaint.setColor(mRightTipSelectedColor);
        mRightPaint.setTextSize(mRightTipSize);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        this.customPkOnDraw(canvas);
    }

    /**
     * 子类决定onDraw的逻辑
     *
     * @param canvas
     */
    protected abstract void customPkOnDraw(Canvas canvas);

    /**
     * 同时替换左右pk条颜色，目前仅在切换夜间模式时调用，减少重绘次数
     *
     * @param leftColor
     * @param rightColor
     */
    public void setPkBarColor(int leftColor, int rightColor) {
        this.mLeftColor = leftColor;
        this.mRightColor = rightColor;
        invalidate();
    }

    /**
     * 设置pk条左右比例
     *
     * @param leftNum  左侧pk条投票数
     * @param rightNum 右侧pk条投票数
     */
    public void setInitNum(int leftNum, int rightNum) {
        this.mLeftCount = leftNum;
        this.mRightCount = rightNum;
        this.mRatio = calculateRatio(leftNum, rightNum);
        invalidate();
    }

    public void setClickPositive(boolean isPositive) {
        mClickPositive = isPositive;
    }

    protected float calculateRatio(int leftNum, int rightNum) {
        float sum = leftNum + rightNum;
        if (leftNum == rightNum || leftNum < 0 || rightNum < 0 || sum <= 0) {
            return DEFAULT_RATIO;
        }

        return leftNum / sum;
    }

    @Override
    protected void onDetachedFromWindow() {
        stopAnimation();
        super.onDetachedFromWindow();
    }

    /**
     * 初始化动画
     */
    public abstract void initAnimation();

    /**
     * 结束动画
     */
    public abstract void stopAnimation();

}
