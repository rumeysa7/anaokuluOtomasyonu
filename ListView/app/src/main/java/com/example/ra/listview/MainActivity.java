package com.example.ra.listview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView) findViewById(R.id.lv);
        final ArrayList<String> list = new ArrayList<>();
        list.add("konya");
        list.add("istanbul <3");
        list.add("izmir");
        list.add("antalya");


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.support_simple_spinner_dropdown_item,list);
        listView.setAdapter(adapter);
        final Intent intent

    }
}
