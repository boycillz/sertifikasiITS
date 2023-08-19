package com.boycillz.aplikasikampusku.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.boycillz.aplikasikampusku.R;
import com.boycillz.aplikasikampusku.model.Data;

import java.util.List;

public class Adapter extends BaseAdapter {
    private Activity activity;
    private final LayoutInflater inflater;
    private final List<Data> items;

    public Adapter(Activity activity, List<Data> items) {
        this.activity = activity;
        this.items = items;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return items.size(); // Mengembalikan jumlah item dalam list
    }

    @Override
    public Object getItem(int position) {
        return items.get(position); // Mengembalikan objek Data pada posisi tertentu
    }

    @Override
    public long getItemId(int position) {
        return position; // Mengembalikan ID dari objek Data pada posisi tertentu
    }

    @SuppressLint("InflateParams")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null)
            convertView = inflater.inflate(R.layout.list_row, null);

        TextView titleTextView = convertView.findViewById(R.id.name);


        Data data = items.get(position);

        titleTextView.setText(data.getName());

        return convertView;
    }


    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }
}

