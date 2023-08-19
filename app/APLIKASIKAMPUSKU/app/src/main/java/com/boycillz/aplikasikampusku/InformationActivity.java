package com.boycillz.aplikasikampusku;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class InformationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        //show actionBar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Tentang Aplikasi");

        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);
    }


    @Override//aksi back ketika di klik
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}