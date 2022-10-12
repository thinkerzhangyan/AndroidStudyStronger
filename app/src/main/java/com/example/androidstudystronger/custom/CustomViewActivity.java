package com.example.androidstudystronger.custom;

import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.example.androidstudystronger.R;

import androidx.appcompat.app.AppCompatActivity;

public class CustomViewActivity extends AppCompatActivity {

    private long mStartTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_custom_view);

        SectorView sectorView = findViewById(R.id.sector_view);

        findViewById(R.id.start_clock_wise).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    mStartTime = System.currentTimeMillis();
                    Log.d("CustomViewActivity", "MotionEvent.ACTION_DOWN");
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    mStartTime = System.currentTimeMillis();
                    sectorView.reset();
                    sectorView.startAntiClockWise();
                    Log.d("CustomViewActivity", "MotionEvent.ACTION_UP");
                } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
                    //认为是长按
                    if (System.currentTimeMillis() - mStartTime > 50) {
                        sectorView.startDrawClockWise();
                    }
                    Log.d("CustomViewActivity", "MotionEvent.ACTION_MOVE");
                }
                return true;
            }
        });

        findViewById(R.id.start_anim).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startIconScaleAnimRepeat(findViewById(R.id.start_clock_wise));
            }
        });

        findViewById(R.id.end_anim).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopIconScaleAnimRepeat(findViewById(R.id.start_clock_wise));
            }
        });

    }

    private ValueAnimator scaleValueAnimator = ValueAnimator.ofFloat(0, 800);
    private ValueAnimator alphaValueAnimator = ValueAnimator.ofFloat(0, 1);

    private void startIconScaleAnimRepeat(View view) {
        if (alphaValueAnimator.isRunning()) {
            alphaValueAnimator.end();
            view.setAlpha(1);
        }
        scaleValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                float percent;
                if (value >= 0 && value <= 400) {
                    percent = 1f - 0.14f * value / 400f;
                } else {
                    percent = 0.86f + 0.14f * (value - 400) / 400f;
                }
                view.setScaleX(percent);
                view.setScaleY(percent);
            }
        });
        scaleValueAnimator.setDuration(800);
        scaleValueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        scaleValueAnimator.start();
    }


    private void stopIconScaleAnimRepeat(ImageView view) {
        scaleValueAnimator.end();
        view.setScaleX(1);
        view.setScaleY(1);
        alphaValueAnimator.setDuration(66);
        view.setImageResource(R.drawable.middle);
        alphaValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float percent = animation.getAnimatedFraction();
                if (percent >= 0.3) {
                    view.setImageResource(R.drawable.end);
                    view.setAlpha(percent);
                }
            }
        });
        alphaValueAnimator.start();
    }

}
