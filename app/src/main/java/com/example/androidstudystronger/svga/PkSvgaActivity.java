package com.example.androidstudystronger.svga;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.androidstudystronger.R;
import com.example.androidstudystronger.custom.ReaderPkBarView;
import com.example.androidstudystronger.utils.ScreenUtils;
import com.opensource.svgaplayer.SVGACallback;
import com.opensource.svgaplayer.SVGADrawable;
import com.opensource.svgaplayer.SVGADynamicEntity;
import com.opensource.svgaplayer.SVGAImageView;
import com.opensource.svgaplayer.SVGAParser;
import com.opensource.svgaplayer.SVGAVideoEntity;

import org.jetbrains.annotations.NotNull;

import androidx.appcompat.app.AppCompatActivity;

public class PkSvgaActivity extends AppCompatActivity implements View.OnClickListener {

    private SVGAImageView mRedSVGAImageView;
    private SVGAImageView mBlueSVGAImageView;

    private View mSelectedOptionView;

    private LinearLayout mOptionTipLayout;

    private TextView mLeftOptionText;
    private TextView mRightOptionText;

    private ReaderPkBarView mPkBarView;

    private final ValueAnimator mOptionViewAlphaAnimator = ValueAnimator.ofFloat(1, 0).setDuration(100);
    private final ValueAnimator mOptionTextAlphaAnimator = ValueAnimator.ofFloat(0, 1).setDuration(100);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_svga);
        initSvgaView();
        initOptionTextView();
        initPkView();
    }

    private void initSvgaView() {
        mRedSVGAImageView = findViewById(R.id.red_svga_view);
        mBlueSVGAImageView = findViewById(R.id.blue_svga_view);
        mRedSVGAImageView.setOnClickListener(this);
        mBlueSVGAImageView.setOnClickListener(this);
        mRedSVGAImageView.setCallback(new DefaultSvgaAnimCallBack());
        mBlueSVGAImageView.setCallback(new DefaultSvgaAnimCallBack());
        parseSvga();
    }

    private void initOptionTextView() {
        mOptionTipLayout = findViewById(R.id.option_tip_layout);
        mLeftOptionText = findViewById(R.id.text_left_option);
        mRightOptionText = findViewById(R.id.text_right_option);
    }

    private void initPkView() {
        mPkBarView = findViewById(R.id.reader_pk_bar_view);
        mPkBarView.setPkBarColor(this.getResources().getColor(R.color.milk_Red),
                this.getResources().getColor(R.color.milk_Blue));
        mPkBarView.hasInCreaseAnimation(true);
    }

    private void parseSvga() {
        parseSvga("svga/red.svga", "会结束", "#EE1A1A", mRedSVGAImageView, "red_key1", "red_key2");
        parseSvga("svga/blue.svga", "不会结束", "#1B88EE", mBlueSVGAImageView, "blue_key1", "blue_key2");
    }

    private void setOptionAnimViewClickNull() {
        mRedSVGAImageView.setOnClickListener(null);
        mBlueSVGAImageView.setOnClickListener(null);
    }

    private void setOptionAnimViewGone() {
        mRedSVGAImageView.setVisibility(View.INVISIBLE);
        mBlueSVGAImageView.setVisibility(View.INVISIBLE);
    }

    private void hideOptionAnimViewWithAnim() {
        mOptionViewAlphaAnimator.addUpdateListener(valueAnimator -> {
            float alpha = (float) valueAnimator.getAnimatedValue();
            if (alpha < 0.01) {
                setOptionAnimViewGone();
            }
            setOptionAnimViewAlpha(alpha);
        });
        mOptionViewAlphaAnimator.addListener(new DefaultAnimListener() {
            @Override
            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                showProgWithAnim();
            }
        });
        mOptionViewAlphaAnimator.start();
    }


    private void setOptionAnimViewAlpha(float alpha) {
        mRedSVGAImageView.setAlpha(alpha);
        mBlueSVGAImageView.setAlpha(alpha);
    }

    private void setOptionTextAnimViewAlpha(float alpha) {
        mLeftOptionText.setAlpha(alpha);
        mRightOptionText.setAlpha(alpha);
    }


    private void showProgWithAnim() {
        mPkBarView.setVisibility(View.VISIBLE);
        mPkBarView.hasInCreaseAnimation(true);
        mPkBarView.setInitNum(30, 70, true);
        mPkBarView.post(() -> {
            showOptionTextWithAnim();
            mPkBarView.initAnimation();
            mPkBarView.startIncreaseAnimation();
        });
    }

    private void showOptionTextWithAnim() {
        mOptionTipLayout.setVisibility(View.VISIBLE);
        mOptionTextAlphaAnimator.addUpdateListener(valueAnimator -> {
            float alpha = (float) valueAnimator.getAnimatedValue();
            setOptionTextAnimViewAlpha(alpha);
        });
        mOptionTextAlphaAnimator.start();
    }

    private void parseSvga(String filePath, String tip, String tipColor, SVGAImageView svgaImageView, String firstKey, String secondKey) {
        try {
            svgaImageView.setLoops(1);
            new SVGAParser(this)
                    .decodeFromAssets(filePath, new SVGAParser.ParseCompletion() {
                        @Override
                        public void onComplete(@NotNull SVGAVideoEntity svgaVideoEntity) {
                            SVGADynamicEntity dynamicEntity = getSVGADynamicEntity(tip, tipColor, firstKey, secondKey);
                            SVGADrawable drawable = new SVGADrawable(svgaVideoEntity, dynamicEntity);
                            drawable.setScaleType(ImageView.ScaleType.CENTER_CROP);
                            svgaImageView.setImageDrawable(drawable);
                            svgaImageView.stepToFrame(0, false);
                        }

                        @Override
                        public void onError() {
                        }
                    });
        } catch (Throwable throwable) {

        }
    }

    @NotNull
    private SVGADynamicEntity getSVGADynamicEntity(String tip, String tipColor, String firstKey, String secondKey) {
        SVGADynamicEntity dynamicEntity = new SVGADynamicEntity();
        TextPaint textPaint = new TextPaint();
        textPaint.setColor(Color.parseColor(tipColor));
        textPaint.setTextSize(ScreenUtils.INSTANCE.sp2px(13.44f));
        dynamicEntity.setDynamicText(new StaticLayout(tip,
                0,
                tip.length(),
                textPaint,
                0,
                Layout.Alignment.ALIGN_CENTER,
                1.0f,
                0.0f,
                false), firstKey);
        dynamicEntity.setDynamicText(new StaticLayout(tip,
                0,
                tip.length(),
                textPaint,
                0,
                Layout.Alignment.ALIGN_CENTER,
                1.0f,
                0.0f,
                false), secondKey);
        return dynamicEntity;
    }


    @Override
    public void onClick(View v) {
        if (v == mRedSVGAImageView) {
            mRedSVGAImageView.stepToFrame(0, true);
            mSelectedOptionView = mRedSVGAImageView;
        } else if (v == mBlueSVGAImageView) {
            mBlueSVGAImageView.stepToFrame(0, true);
            mSelectedOptionView = mBlueSVGAImageView;
        }
    }


    private class DefaultAnimListener implements Animator.AnimatorListener {

        @Override
        public void onAnimationStart(Animator animator) {

        }

        @Override
        public void onAnimationEnd(Animator animator) {

        }

        @Override
        public void onAnimationCancel(Animator animator) {

        }

        @Override
        public void onAnimationRepeat(Animator animator) {

        }

    }

    private class DefaultSvgaAnimCallBack implements SVGACallback {

        @Override
        public void onFinished() {
            hideOptionAnimViewWithAnim();
        }

        @Override
        public void onPause() {

        }

        @Override
        public void onRepeat() {

        }

        @Override
        public void onStep(int i, double v) {
            setOptionAnimViewClickNull();
        }

    }

}