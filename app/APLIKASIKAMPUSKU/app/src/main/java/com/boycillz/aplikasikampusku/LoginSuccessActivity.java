package com.boycillz.aplikasikampusku;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class LoginSuccessActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_success);

        ActionBar actionBar = getSupportActionBar();//show actionbar
        actionBar.setTitle("Login");

        new Handler().postDelayed(() -> {
            Intent intent = new Intent(LoginSuccessActivity.this,
                    DashboardActivity.class);
            startActivity(intent);
            finish();
        }, 5000); // Delay selama 5 detik
    }
}