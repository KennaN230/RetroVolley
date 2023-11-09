package com.example.retrovolley.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.retrovolley.R;

import java.util.ArrayList;

public class Adaptor extends BaseAdapter {
    Context context;
    LayoutInflater inflater;
    ArrayList<GetData> model;

    public Adaptor(Context context, ArrayList<GetData> model) {
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.model = model;
    }

    @Override
    public int getCount() {
        return model.size();
    }

    @Override
    public Object getItem(int position) {
        return model.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView nama, alamat, jk;
        View view1 = inflater.inflate(R.layout.listmahasiswa, null);
        if (view1 != null) {
            nama = view1.findViewById(R.id.nama);
            alamat = view1.findViewById(R.id.alamat);
            jk = view1.findViewById(R.id.jk);

            nama.setText(model.get(position).getNama());
            alamat.setText(model.get(position).getAlamat());
            jk.setText(model.get(position).getJeniskelamin());
        }
        return null;
    }
}
