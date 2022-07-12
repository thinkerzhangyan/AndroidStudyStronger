package com.example.androidstudystronger.canvas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.androidstudystronger.R;

import androidx.appcompat.app.AppCompatActivity;

public class CanvasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_canvas);

        findViewById(R.id.bt_one).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(CanvasActivity.this, CanvasAndPaintActivity.class);
                startActivity(intent);

            }
        });

        findViewById(R.id.bt_two).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(CanvasActivity.this, CanvasLayerActivity.class);
                startActivity(intent);

            }
        });

        findViewById(R.id.bt_three).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(CanvasActivity.this, CanvasSaveScaleActivity.class);
                startActivity(intent);

            }
        });

        findViewById(R.id.bt_four).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(CanvasActivity.this, ClipRectActivity.class);
                startActivity(intent);

            }
        });

        findViewById(R.id.bt_five).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(CanvasActivity.this, ClockActivity.class);
                startActivity(intent);

            }
        });

        findViewById(R.id.bt_six).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(CanvasActivity.this, OtherActivity.class);
                startActivity(intent);

            }
        });

        findViewById(R.id.bt_one).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(CanvasActivity.this, CanvasAndPaintActivity.class);
                startActivity(intent);

            }
        });

        findViewById(R.id.bt_seven).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(CanvasActivity.this, ClipPathActivity.class);
                startActivity(intent);

            }
        });

        findViewById(R.id.bt_eight).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });

        findViewById(R.id.bt_nine).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CanvasActivity.this, DrawTextActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.bt_ten).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CanvasActivity.this, XferModeActivity.class);
                startActivity(intent);
            }
        });

    }
}
