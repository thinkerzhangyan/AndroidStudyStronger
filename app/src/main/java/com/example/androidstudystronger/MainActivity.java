package com.example.androidstudystronger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.androidstudystronger.recyclerview.RecyclerViewActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.text_recycler_view).setOnClickListener(view -> startActivity(new Intent(MainActivity.this, RecyclerViewActivity.class)));
    }
}