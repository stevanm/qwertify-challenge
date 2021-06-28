package com.qwertify.project.presentation.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.qwertify.project.R;

public class SplashActivity extends AppCompatActivity {

    private static int MSEC = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //remove action bar
        if (null != getSupportActionBar()) {
            getSupportActionBar().hide();
        }

        new Handler().postDelayed(() -> {
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }, MSEC);

    }

}