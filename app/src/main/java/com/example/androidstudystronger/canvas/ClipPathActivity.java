package com.example.androidstudystronger.canvas;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class ClipPathActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new ClipPathDemo(this));
    }
}
