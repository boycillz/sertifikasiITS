package com.boycillz.aplikasikampusku;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        getSupportActionBar().hide();//sumbinyakan actionbar

        new Handler().postDelayed(() -> {
            Intent intent = new Intent(SplashScreen.this,
                    LoginActivity.class);
            startActivity(intent);
            finish();
        }, 5000); // Delay selama 5 detik
    }
}