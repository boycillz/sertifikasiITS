package com.boycillz.crudapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class InputDataActivity extends AppCompatActivity {
    private EditText nomorEditText, namaEditText, tanggalLahirEditText, jenisKelaminEditText, alamatEditText;
    private Button saveButton;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_data);

        nomorEditText = findViewById(R.id.nomorEditText);
        namaEditText = findViewById(R.id.namaEditText);
        tanggalLahirEditText = findViewById(R.id.tanggalLahirEditText);
        jenisKelaminEditText = findViewById(R.id.jenisKelaminEditText);
        alamatEditText = findViewById(R.id.alamatEditText);
        saveButton = findViewById(R.id.saveButton);
        databaseHelper = new DatabaseHelper(this);

        saveButton.setOnClickListener(v -> saveData());
    }

    private void saveData() {
        String nomor = nomorEditText.getText().toString().trim();
        String nama = namaEditText.getText().toString().trim();
        String tanggalLahir = tanggalLahirEditText.getText().toString().trim();
        String jenisKelamin = jenisKelaminEditText.getText().toString().trim();
        String alamat = alamatEditText.getText().toString().trim();

        if (!nomor.isEmpty() && !nama.isEmpty() && !tanggalLahir.isEmpty() &&
                !jenisKelamin.isEmpty() && !alamat.isEmpty()) {
            Mahasiswa mahasiswa = new Mahasiswa();
            mahasiswa.setNomor(nomor);
            mahasiswa.setNama(nama);
            mahasiswa.setTanggalLahir(tanggalLahir);
            mahasiswa.setJenisKelamin(jenisKelamin);
            mahasiswa.setAlamat(alamat);

            databaseHelper.addMahasiswa(mahasiswa);
            clearInputFields();
        }
    }

    private void clearInputFields() {
        nomorEditText.setText("");
        namaEditText.setText("");
        tanggalLahirEditText.setText("");
        jenisKelaminEditText.setText("");
        alamatEditText.setText("");
    }
}
