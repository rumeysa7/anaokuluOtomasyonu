package com.example.anaokuluotomasyonu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Console;
import java.util.HashMap;

public class Ogrenci_Liste_Activity extends AppCompatActivity {

    private FirebaseDatabase database;
    private DatabaseReference mDatabase;
    private ListView lv_ogrenci_Liste;
    private FirebaseAuth auth;

    private Integer ogrenci_No;
    private String ogrenci_Ad;
    private String ogrenci_Soyad;
    private Long ogrenci_Tc_No;
    private String ogrenci_Veli1;
    private String ogrenci_Veli2;
    private String ogrenci_Boy;
    private String ogrenci_Kilo;
    private String ogrenci_Dogum_Tarih;
    private String ogrenci_Sinif;
    private String o_Ad;
    private String ogrenci_Ilac;

    private String ogrenci_Key;


    public void init() {

        //actionbarlogin = (Toolbar) findViewById(R.id.actionbarlogin);
        //setSupportActionBar(actionbarlogin);
        getSupportActionBar().setTitle("Öğrenci Bilgi Düzenle");

        auth = FirebaseAuth.getInstance();

        database = FirebaseDatabase.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference("Ogrenci");
        lv_ogrenci_Liste = (ListView)findViewById(R.id.student_listview);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ogrenci__liste_);

        init();

        Bundle extras = getIntent().getExtras();
        final String ogr_adi = extras.getString("ogretmen_Adi");


        final ArrayAdapter<Ogrenci> aa_ogrenci_liste = new ArrayAdapter<Ogrenci>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1);

        final ArrayAdapter<String> aa_ogrenci_goster = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1);
        final ArrayAdapter<String> aa_ogrenci_key = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1);

        lv_ogrenci_Liste.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Ogrenci ogrenci = new Ogrenci();
                ogrenci = aa_ogrenci_liste.getItem(position);

                ogrenci_No = ogrenci.getOgrenci_No();
                ogrenci_Ad = ogrenci.getOgrenci_Ad();
                ogrenci_Soyad = ogrenci.getOgrenci_Soyad();
                ogrenci_Tc_No = ogrenci.getOgrenci_Tc_No();
                ogrenci_Veli1 = ogrenci.getOgrenci_Veli1();
                ogrenci_Veli2 = ogrenci.getOgrenci_Veli2();
                ogrenci_Boy = ogrenci.getOgrenci_Boy();
                ogrenci_Kilo = ogrenci.getOgrenci_Kilo();
                ogrenci_Dogum_Tarih = ogrenci.getOgrenci_Dogum_Tarih();
                ogrenci_Sinif = ogrenci.getOgrenci_Sinif();
                o_Ad = ogrenci.getO_Ad();
                ogrenci_Ilac = ogrenci.getOgrenci_Ilac();

                ogrenci_Key = aa_ogrenci_key.getItem(position);

                Intent ogrenci_duzenle = new Intent(Ogrenci_Liste_Activity.this, Ogrenci_Duzenle_Activity.class);
                ogrenci_duzenle.putExtra("ogrenci_No", ogrenci_No);
                ogrenci_duzenle.putExtra("ogrenci_Ad", ogrenci_Ad);
                ogrenci_duzenle.putExtra("ogrenci_Soyad", ogrenci_Soyad);
                ogrenci_duzenle.putExtra("ogrenci_Tc_No", ogrenci_Tc_No);
                ogrenci_duzenle.putExtra("ogrenci_Veli1", ogrenci_Veli1);
                ogrenci_duzenle.putExtra("ogrenci_Veli2", ogrenci_Veli2);
                ogrenci_duzenle.putExtra("ogrenci_Boy", ogrenci_Boy);
                ogrenci_duzenle.putExtra("ogrenci_Kilo", ogrenci_Kilo);
                ogrenci_duzenle.putExtra("ogrenci_Dogum_Tarih", ogrenci_Dogum_Tarih);
                ogrenci_duzenle.putExtra("ogrenci_Sinif", ogrenci_Sinif);
                ogrenci_duzenle.putExtra("o_Ad", o_Ad);
                ogrenci_duzenle.putExtra("ogrenci_Ilac", ogrenci_Ilac);
                ogrenci_duzenle.putExtra("ogrenci_Key", ogrenci_Key);
                ogrenci_duzenle.putExtra("ogr_Adi", ogr_adi);

                startActivity(ogrenci_duzenle);
                finish();

                Toast.makeText(Ogrenci_Liste_Activity.this, aa_ogrenci_key.getItem(position) , Toast.LENGTH_LONG).show();
            }
        });

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    Ogrenci ogrenci = postSnapshot.getValue(Ogrenci.class);
                    Log.d("ogrenci_No",ogrenci.getOgrenci_No().toString());
                    Log.d("ogrenci_Ad",ogrenci.getO_Ad());
                    Log.d("ogrenci_Soyad",ogrenci.getOgrenci_Soyad());
                    Log.d("ogrenci_Tc_No",ogrenci.getOgrenci_Tc_No().toString());
                    Log.d("ogrenci_Veli1",ogrenci.getOgrenci_Veli1());
                    Log.d("ogrenci_Veli2",ogrenci.getOgrenci_Veli2());
                    Log.d("ogrenci_Boy",ogrenci.getOgrenci_Boy());
                    Log.d("ogrenci_Kilo",ogrenci.getOgrenci_Kilo());
                    Log.d("ogrenci_Dogum_Tarih",ogrenci.getOgrenci_Dogum_Tarih());
                    Log.d("ogrenci_Sinif",ogrenci.getOgrenci_Sinif());
                    Log.d("o_Ad",ogrenci.getO_Ad());
                    Log.d("ogrenci_Ilac",ogrenci.getOgrenci_Ilac());

                    String key = postSnapshot.getKey();

                    aa_ogrenci_liste.add(ogrenci);

                    aa_ogrenci_key.add(key);

                    if (ogr_adi.equalsIgnoreCase(ogrenci.getO_Ad()))
                    {
                        aa_ogrenci_goster.add("Numara : " + ogrenci.getOgrenci_No().toString() + "     | " + ogrenci.getOgrenci_Ad() + " " +
                                ogrenci.getOgrenci_Soyad().toUpperCase());
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Ogrenci_Liste_Activity.this,"Hata oluştu!", Toast.LENGTH_LONG).show();
            }
        });


        //Listviewin içine adapteri set edip adaptere verdiğimiz ismi yazıyoruz
        lv_ogrenci_Liste.setAdapter(aa_ogrenci_goster);
    }
}