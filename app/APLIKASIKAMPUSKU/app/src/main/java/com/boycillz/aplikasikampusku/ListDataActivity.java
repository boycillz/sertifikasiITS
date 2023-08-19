package com.boycillz.aplikasikampusku;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.boycillz.aplikasikampusku.adapter.Adapter;
import com.boycillz.aplikasikampusku.db.DatabaseHelper;
import com.boycillz.aplikasikampusku.model.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ListDataActivity extends AppCompatActivity {

    public static final String TAG_ID = "id";
    public static final String TAG_NOMOR = "nomor";
    public static final String TAG_NAME = "name";
    public static final String TAG_TL = "tl";
    public static final String TAG_JK = "jk";
    public static final String TAG_ADDRESS = "address";

    //deklarasikan variabel
    ListView listView;
    List<Data> itemlist = new ArrayList<>();
    Adapter adapter;
    DatabaseHelper SQLite;
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_data);

        //show action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Data Mahasiswa");

        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);

        //koneksi antara java class&xml
        btnAdd = findViewById(R.id.btnInput);
        SQLite = new DatabaseHelper(this);
        listView = findViewById(R.id.list_view);
        adapter = new Adapter(ListDataActivity.this, itemlist);
        listView.setAdapter(adapter);

        //aksi ketika klik btnAdd
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListDataActivity.this,
                        InputActivity.class);
                intent.putExtra("halaman_sumber", "B");
                startActivity(intent);
                finish();
            }
        });

        //aksi popup dialog ketika klik lama pada listData
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override//deklarasi variabel&get data
            public boolean onItemLongClick(final AdapterView<?> parent, View view,
                                           final int position, long id) {
                final Data data = itemlist.get(position);
                final String idx = data.getId();
                final String name = data.getName();
                final String address = data.getAddress();

                //show text in dialoge popup
                final CharSequence[] dialogitem = {"Lihat Data", "Update Data", "Hapus"};
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(
                        ListDataActivity.this);
                dialogBuilder.setCancelable(true);
                dialogBuilder.setItems(dialogitem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //aksi ketika pilih lihat data
                        if (which == 0) {
                            Intent intent = new Intent(ListDataActivity.this,
                                    DataActivity.class);
                            intent.putExtra(TAG_NOMOR, data.getNomor());
                            intent.putExtra(TAG_NAME, name);
                            intent.putExtra(TAG_TL, data.getTl());
                            intent.putExtra(TAG_JK, data.getJk());
                            intent.putExtra(TAG_ADDRESS, address);
                            startActivity(intent);
                            finish();
                        }
                        //aksi ketika pilih update data
                        else if (which == 1) {
                            Intent intent = new Intent(ListDataActivity.this,
                                    InputActivity.class);
                            intent.putExtra(TAG_ID, idx);
                            intent.putExtra(TAG_NOMOR, data.getNomor());
                            intent.putExtra(TAG_NAME, name);
                            intent.putExtra(TAG_TL, data.getTl());
                            intent.putExtra(TAG_JK, data.getJk());
                            intent.putExtra(TAG_ADDRESS, address);
                            intent.putExtra("halaman_sumber", "B");
                            startActivity(intent);
                            finish();
                        }
                        //aksi ketika pilih hapus
                        else if (which == 2) {
                            if (!TextUtils.isEmpty(idx)) {
                                SQLite.delete(Integer.parseInt(idx));
                                itemlist.remove(position);  // Hapus data dari itemlist
                                adapter.notifyDataSetChanged();
                            }
                        }
                    }
                }).show();
                return false;
            }
        });
        getAllData();
    }

    //fungsi back pada actionbar
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void getAllData() {
        itemlist.clear();

        //get data from sqlite
        ArrayList<HashMap<String, String>> dataList = SQLite.getAllData();

        //get all data
        for (HashMap<String, String> dataMap : dataList) {
            String id = dataMap.get(DatabaseHelper.COLUMN_ID);
            String nomor = dataMap.get(DatabaseHelper.COLUMN_NOMOR);
            String name = dataMap.get(DatabaseHelper.COLUMN_NAME);
            String tl = dataMap.get(DatabaseHelper.COLUMN_TL);
            String jk = dataMap.get(DatabaseHelper.COLUMN_JK);
            String address = dataMap.get(DatabaseHelper.COLUMN_ADDRESS);

            //set all data
            Data data = new Data();
            data.setId(id);
            data.setNomor(nomor);
            data.setName(name);
            data.setTl(tl);
            data.setJk(jk);
            data.setAddress(address);

            //add data to list
            itemlist.add(data);
        }
        adapter.notifyDataSetChanged();
    }
}