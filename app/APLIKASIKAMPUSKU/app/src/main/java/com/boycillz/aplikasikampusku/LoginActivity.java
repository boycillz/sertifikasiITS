package com.boycillz.aplikasikampusku;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameEditText, passwordEditText;//deklarasi variabel


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ActionBar actionBar = getSupportActionBar();//tampilkan actionbar
        actionBar.setTitle("Login");

        usernameEditText = findViewById(R.id.usernameEditText);//hubungkan login xml dan java class
        passwordEditText = findViewById(R.id.passwordEditText);

        Button loginButton = findViewById(R.id.loginButton);//deklarasi&hubungkan xml dan java class

        loginButton.setOnClickListener(view -> {
            String username = usernameEditText.getText().toString();//get data from input
            String password = passwordEditText.getText().toString();

            if (username.equals("username") && password.equals("password")) {//check data user&pass
                Intent intent = new Intent(LoginActivity.this,
                        LoginSuccessActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(LoginActivity.this, "User/Password salah",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}