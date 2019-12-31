package com.example.ra.ygstrke;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ViewFlipper;

public class zorTest extends AppCompatActivity implements View.OnClickListener{

    Button geriGit, ileriGit, otomatik;
    ViewFlipper viewFlipper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zor_test);

        geriGit = (Button)findViewById(R.id.btnGeri);
        ileriGit = (Button)findViewById(R.id.btnİleri);
        otomatik = (Button)findViewById(R.id.btnOtomatik);
        viewFlipper = (ViewFlipper)findViewById(R.id.vf1);

        geriGit.setOnClickListener(this);
        ileriGit.setOnClickListener(this);
        otomatik.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch(view.getId()){

            case R.id.btnGeri:
                if(viewFlipper.isFlipping())
                    viewFlipper.stopFlipping();

                viewFlipper.showPrevious();
                break;

            case R.id.btnİleri:
                if(viewFlipper.isFlipping())
                    viewFlipper.stopFlipping();

                viewFlipper.showNext();
                break;

            case R.id.btnOtomatik:
                viewFlipper.setFlipInterval(2000);
                viewFlipper.startFlipping();
                break;


        }
    }
}

