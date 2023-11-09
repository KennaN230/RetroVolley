package com.example.retrovolley;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.retrovolley.Adapter.Adaptor;
import com.example.retrovolley.Adapter.GetData;

import java.util.ArrayList;

public class Dashboard extends AppCompatActivity {
    ListView listView;
    ArrayList<GetData> model;
    Adaptor adaptor; // Mengganti nama variabel menjadi 'adaptor'

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        listView = findViewById(R.id.list);
        LoadData();
        adaptor = new Adaptor(getApplicationContext(), model); // Mengganti 'adapter1' menjadi 'adaptor'
        listView.setAdapter(adaptor); // Mengganti 'adapter1' menjadi 'adaptor'
    }

    void LoadData() {
        model = new ArrayList<>();
        for (int i = 0; i < 15; i++) { // Mengubah 'i <= 15' menjadi 'i < 15'
            model.add(new GetData("Aditya", "Probolinggo", "Laki - Laki"));
        }
    }
}
