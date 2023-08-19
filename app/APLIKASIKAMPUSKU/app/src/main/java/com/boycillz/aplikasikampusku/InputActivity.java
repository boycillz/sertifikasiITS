package com.boycillz.aplikasikampusku;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.boycillz.aplikasikampusku.db.DatabaseHelper;

public class InputActivity extends AppCompatActivity {

    EditText et_nomor, et_nama, et_tgl, et_jk, et_alamat, et_id;
    DatabaseHelper SQLite = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        //show actionBar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Input Data Mahasiswa");

        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);

        //deklarasi dan hubungakan variabel java class&xml
        Button btnSubmit = findViewById(R.id.btnSave);
        TextView judul = findViewById(R.id.tv_nomor);

        //koneksi antara variabel java class dan xml
        et_alamat = findViewById(R.id.et_alamat);
        et_jk = findViewById(R.id.et_jeniskelamin);
        et_nama = findViewById(R.id.et_nama);
        et_nomor = findViewById(R.id.et_nomor);
        et_tgl = findViewById(R.id.et_tgllahir);
        et_id = findViewById(R.id.et_idid);

        //get intentdata from ListDataAcritivty
        String id = getIntent().getStringExtra(ListDataActivity.TAG_ID);
        String nomor = getIntent().getStringExtra(ListDataActivity.TAG_NOMOR);
        String nama = getIntent().getStringExtra(ListDataActivity.TAG_NAME);
        String tgl = getIntent().getStringExtra(ListDataActivity.TAG_TL);
        String jk = getIntent().getStringExtra(ListDataActivity.TAG_JK);
        String alamat = getIntent().getStringExtra(ListDataActivity.TAG_ADDRESS);

        //seleksi untuk input data atau update data
        if (id == null || id.equals("")) {
            judul.setText("Input Data");
        } else {
            judul.setText("Update Data");
            et_id.setText(id);//set data variabel in java class
            et_nomor.setText(nomor);
            et_nama.setText(nama);
            et_tgl.setText(tgl);
            et_jk.setText(jk);
            et_alamat.setText(alamat);
        }

        //aksi ketika btnSubmit di klik
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (et_id.getText().toString().equals("")) {
                        save();//lakukan fungsi simpan
                    } else {
                        edit();//lakukan fungsi edit/update
                    }
                } catch (Exception e) {
                    Log.e("Submit", e.toString());
                }
            }
        });


    }

    private void save() {
        if (et_id.getText() == null || et_id.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), "Tolong isi nama terlebih dahulu..",
                    Toast.LENGTH_SHORT).show();//jika data diisi kosong
        }
        else {
            String nomor = et_nomor.getText().toString().trim();//get data variabel
            String nama = et_nama.getText().toString().trim();
            String tgl = et_tgl.getText().toString().trim();
            String jk = et_jk.getText().toString().trim();
            String alamat = et_alamat.getText().toString().trim();

            SQLite.insert(nomor, nama, tgl, jk, alamat);//masukan data ke SQLite
            blank();
            Intent intent = new Intent(InputActivity.this,
                    ListDataActivity.class);//ketika selesai input, back to listDataActivity
            startActivity(intent);
            finish();
        }
    }

    private void edit() {
        if (et_nama.getText() == null || et_nama.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Tolong isi nama terlebih dahulu..",
                    Toast.LENGTH_SHORT).show();//jika data diisi kosong
        } else {
            int id = Integer.parseInt(et_id.getText().toString().trim());
            String nomor = et_nomor.getText().toString().trim();//get data variabel
            String nama = et_nama.getText().toString().trim();
            String tgl = et_tgl.getText().toString().trim();
            String jk = et_jk.getText().toString().trim();
            String alamat = et_alamat.getText().toString().trim();

            SQLite.update(id, nomor, nama, tgl, jk, alamat);//update data ke SQLite
            blank();
            Intent intent = new Intent(InputActivity.this,
                    ListDataActivity.class);//ketika selesai input, back to listDataActivity
            startActivity(intent);
            finish();
        }
    }

    private void blank() {
        et_id.setText(null);//set data kosong/null
        et_nomor.setText(null);
        et_nama.setText(null);
        et_tgl.setText(null);
        et_jk.setText(null);
        et_alamat.setText(null);
    }

    //aksi untuk backspce
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}