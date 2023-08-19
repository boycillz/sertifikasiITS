package com.boycillz.aplikasikampusku;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class DataActivity extends AppCompatActivity {

    EditText et_nomor, et_nama, et_tgl, et_jk, et_alamat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        //show actionbar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Data Lengkap");

        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);

        //hubungkan variabel java class&xml
        et_alamat = findViewById(R.id.et_alamat);
        et_jk = findViewById(R.id.et_jeniskelamin);
        et_nama = findViewById(R.id.et_nama);
        et_nomor = findViewById(R.id.et_nomor);
        et_tgl = findViewById(R.id.et_tgllahir);

        //get data variabel from ListDataActivity
        String nomor = getIntent().getStringExtra(ListDataActivity.TAG_NOMOR);
        String nama = getIntent().getStringExtra(ListDataActivity.TAG_NAME);
        String tgl = getIntent().getStringExtra(ListDataActivity.TAG_TL);
        String jk = getIntent().getStringExtra(ListDataActivity.TAG_JK);
        String alamat = getIntent().getStringExtra(ListDataActivity.TAG_ADDRESS);

        //set data to variabel java class
        et_nomor.setText(nomor);
        et_nama.setText(nama);
        et_tgl.setText(tgl);
        et_jk.setText(jk);
        et_alamat.setText(alamat);
    }

    //aksi untuk backspace
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}