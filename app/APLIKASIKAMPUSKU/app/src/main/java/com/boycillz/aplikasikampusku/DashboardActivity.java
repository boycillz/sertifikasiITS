package com.boycillz.aplikasikampusku;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class DashboardActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        //show actionbar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Dashboard");

        //deklarasi dan hubungkan btnLihatData java class&xml
        Button btnLihatData = findViewById(R.id.lihatDataButton);
        btnLihatData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigasi ke halaman list data mahasiswa
                Intent intent = new Intent(DashboardActivity.this,
                        ListDataActivity.class);
                startActivity(intent);
            }
        });

        //deklarasi dan hubungkan btnInputData java class&xml
        Button btnInputData = findViewById(R.id.inputDataButton);
        btnInputData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigasi ke halaman input data mahasiswa
                Intent intent = new Intent(DashboardActivity.this,
                        InputActivity.class);
                startActivity(intent);
            }
        });

        //deklarasi dan hubungkan btnDetailInfo java class&xml
        Button btnInfo = findViewById(R.id.informasiButton);
        btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigasi ke halaman detail data mahasiswa
                Intent intent = new Intent(DashboardActivity.this,
                        InformationActivity.class);
                startActivity(intent);
            }
        });
    }

}