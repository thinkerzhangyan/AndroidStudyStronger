package com.example.androidstudystronger.jetpack;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.androidstudystronger.R;

import org.jetbrains.annotations.NotNull;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;

public class LiveDataActivity extends AppCompatActivity {

    private static final String TAG = LiveDataActivity.class.getSimpleName();

    private MutableLiveData<String> mLiveData = new MutableLiveData<>();

    private boolean mFlag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_data);

        findViewById(R.id.text_live_data).setOnClickListener(view -> mLiveData.setValue("1"));

        findViewById(R.id.text_register_observer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLiveData.observe(LiveDataActivity.this, s -> {
                    Log.d(TAG, "LiveDataActivity D " + s);
                });
            }
        });


        mLiveData.observe(this, s -> {
            Log.d(TAG, "LiveDataActivity A " + s);
            if (mFlag) {
                mFlag = false;
                mLiveData.setValue("2");
            }
        });

        mLiveData.observe(this, s -> {
            Log.d(TAG, "LiveDataActivity B "+s);
        });

        mLiveData.observe(this, s -> {
            Log.d(TAG, "LiveDataActivity C "+s);
        });


        getLifecycle().addObserver((LifecycleEventObserver) (source, event) -> {

        });




    }
}