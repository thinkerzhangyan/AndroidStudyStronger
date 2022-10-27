package com.example.androidstudystronger.jetpack;

import android.content.Intent;
import android.os.Bundle;

import com.example.androidstudystronger.MainActivity;
import com.example.androidstudystronger.R;

import androidx.appcompat.app.AppCompatActivity;

public class JetpackActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jetpack);

        findViewById(R.id.text_live_data).setOnClickListener(view -> startActivity(new Intent(JetpackActivity.this, LiveDataActivity.class)));

    }
}