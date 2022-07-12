package com.example.androidstudystronger.canvas;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class CanvasLayerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new CanvasLayer(this));
    }
}
