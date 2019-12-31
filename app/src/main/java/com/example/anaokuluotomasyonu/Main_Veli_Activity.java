package com.example.anaokuluotomasyonu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Main_Veli_Activity extends AppCompatActivity {


    private Toolbar actionbarlogin;
    private Button btnEtkinlikler, btnYemekListesi;

    private FirebaseAuth auth;
    FirebaseDatabase database;

    public void init() {

        //actionbarlogin = (Toolbar) findViewById(R.id.actionbarlogin);
        //setSupportActionBar(actionbarlogin);
        getSupportActionBar().setTitle("Veli Girişi");
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true); //geri oku gözükmesi için

        btnEtkinlikler = (Button) findViewById(R.id.btnEvents);
        btnYemekListesi = (Button) findViewById(R.id.btnFoodList);

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__veli_);

        init();

        btnYemekListesi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent yemekListesi = new Intent(Main_Veli_Activity.this, Yemek_Listesi_Goruntule_Activity.class);
                startActivity(yemekListesi);
            }
        });


        btnEtkinlikler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent etkinlikler = new Intent(Main_Veli_Activity.this, Etkinlik_Liste_Activity.class);
                startActivity(etkinlikler);
            }
        });

    }

}
