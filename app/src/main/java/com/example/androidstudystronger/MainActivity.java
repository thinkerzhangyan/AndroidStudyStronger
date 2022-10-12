package com.example.androidstudystronger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.androidstudystronger.canvas.CanvasActivity;
import com.example.androidstudystronger.custom.CustomViewActivity;
import com.example.androidstudystronger.popupwindow.PopupActivity;
import com.example.androidstudystronger.recyclerview.RecyclerViewActivity;
import com.example.androidstudystronger.span.SpanActivity;
import com.example.androidstudystronger.svga.PkSvgaActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.text_recycler_view).setOnClickListener(view -> startActivity(new Intent(MainActivity.this, RecyclerViewActivity.class)));
        findViewById(R.id.text_popup).setOnClickListener(view -> startActivity(new Intent(MainActivity.this, PopupActivity.class)));
        findViewById(R.id.text_svga).setOnClickListener(view -> startActivity(new Intent(MainActivity.this, PkSvgaActivity.class)));
        findViewById(R.id.text_span).setOnClickListener(view -> startActivity(new Intent(MainActivity.this, SpanActivity.class)));
        findViewById(R.id.text_canvas).setOnClickListener(view -> startActivity(new Intent(MainActivity.this, CanvasActivity.class)));
        findViewById(R.id.custom_view).setOnClickListener(view -> startActivity(new Intent(MainActivity.this, CustomViewActivity.class)));


    }
}