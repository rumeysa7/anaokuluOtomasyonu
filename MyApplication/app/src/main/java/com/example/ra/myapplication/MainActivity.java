package com.example.ra.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button gonder;
    RadioGroup radiogrubu;
    String kursAdi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        radiogrubu= (RadioGroup)findViewById(R.id.radioGroup1);

        gonder = (Button)findViewById(R.id.button1);
        gonder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int secilenHangiRadio = radiogrubu.getCheckedRadioButtonId();

                switch (secilenHangiRadio) {

                    case R.id.radioButton1: {
                        kursAdi = "Android";
                        break;
                    }
                    case R.id.radioButton2: {
                        kursAdi = "Phyton";
                        break;
                    }
                    case R.id.radioButton3: {
                        kursAdi = "C#";
                        break;
                    }
                    case R.id.radioButton4: {
                        kursAdi = "Matlab";
                        break;
                    }
                }
                Toast.makeText(MainActivity.this, kursAdi, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
