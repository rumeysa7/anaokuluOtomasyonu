package com.example.ra.ygstrke;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Main3Activity extends AppCompatActivity {

    ListView testlerim;
    static final String[] testler = new String[]{
            "1.) KOLAY",
            "2.) ORTA",
            "3.) ZOR",};
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        testlerim = (ListView) findViewById(R.id.lvTestler);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, R.layout.list_konu, testler);
        testlerim.setAdapter(adapter);

        testlerim.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (position == 0) {
                    Intent sözcükAnlamı = new Intent(view.getContext(), kolayTest.class);
                    startActivityForResult(sözcükAnlamı, 0);
                }
                if (position == 1) {
                    Intent sözYorumu = new Intent(view.getContext(), ortaTest.class);
                    startActivityForResult(sözYorumu, 1);
                }
                if (position == 2) {
                    Intent deyim = new Intent(view.getContext(), zorTest.class);
                    startActivityForResult(deyim, 2);
                }}
        });}}
