package com.example.ra.fragment;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends FragmentActivity {

    Button btnLeft,btnRight;
    Fragment fragment1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnLeft = (Button) findViewById(R.id.btnLeft);
        Button btnRight =(Button) findViewById(R.id.btnRight);
        ImageView imageView = (ImageView) findViewById(R.id.imageView);

        fragment1 = new Fragment;
        final Fragment fragment2 = new Fragment();

        FragmentManager fragmentManager = MainActivity.this.getSupportFragmentManager();
        final android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fR,fragment1);
        fragmentTransaction.commit();

        assert btnLeft !=null;
        btnLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager =getSupportFragmentManager();
                final android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fR,fragment1);
                fragmentTransaction.commit();

            }
        }};
};
