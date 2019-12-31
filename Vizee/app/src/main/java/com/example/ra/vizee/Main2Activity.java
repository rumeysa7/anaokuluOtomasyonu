package com.example.ra.vizee;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Bundle extras = getIntent().getExtras();
        String miktar = extras.getString("miktar");
        Toast.makeText(this,"main aktivity'den gelen miktar degeri: " +miktar, Toast.LENGTH_LONG).show();
    }
}
