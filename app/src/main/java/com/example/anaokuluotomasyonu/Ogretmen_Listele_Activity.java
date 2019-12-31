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
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.math.BigInteger;

public class Ogretmen_Listele_Activity extends AppCompatActivity {

    private FirebaseDatabase database;
    private DatabaseReference mDatabase;
    private FirebaseAuth auth;
    private ListView lv_ogretmen;

    private String o_Ad;
    private String o_Soyad;
    private String o_Tel;
    private String o_Mail;
    private String o_Sifre;
    private String sinif;
    private Long o_Tc_No;

    private String ogretmen_Key;

    public void init(){

        getSupportActionBar().setTitle("Öğretmen Liste");

        auth = FirebaseAuth.getInstance();

        database = FirebaseDatabase.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference("Ogretmen");
        lv_ogretmen = (ListView)findViewById(R.id.teacher_listview);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ogretmen__listele_);

        init();
        final ArrayAdapter<Ogretmen> aa_ogretmen_liste = new ArrayAdapter<Ogretmen>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1);

        final ArrayAdapter<String> aa_ogretmen_goster = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1);
        final ArrayAdapter<String> aa_ogretmen_key = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1);

        //Bundle extras = getIntent().getExtras();
        //final String ogr_adi = extras.getString("ogretmen_Adi");

        lv_ogretmen.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Ogretmen secilen_ogretmen = new Ogretmen();
                secilen_ogretmen = aa_ogretmen_liste.getItem(position);
                o_Ad = secilen_ogretmen.getO_Ad();
                o_Soyad = secilen_ogretmen.getO_Soyad();
                o_Tel = secilen_ogretmen.getO_Tel();
                o_Mail = secilen_ogretmen.getO_Mail();
                o_Sifre = secilen_ogretmen.getO_Sifre();
                sinif = secilen_ogretmen.getSinif();
                o_Tc_No = secilen_ogretmen.getO_Tc_No();

                ogretmen_Key = aa_ogretmen_key.getItem(position);

                Intent ogretmen_duzenle = new Intent(Ogretmen_Listele_Activity.this, Ogretmen_Duzenle_Activity.class);
                ogretmen_duzenle.putExtra("o_Ad", o_Ad);
                ogretmen_duzenle.putExtra("o_Soyad", o_Soyad);
                ogretmen_duzenle.putExtra("o_Tel", o_Tel);
                ogretmen_duzenle.putExtra("o_Mail", o_Mail);
                ogretmen_duzenle.putExtra("o_Sifre", o_Sifre);
                ogretmen_duzenle.putExtra("sinif", sinif);
                ogretmen_duzenle.putExtra("o_Tc_No", o_Tc_No);
                ogretmen_duzenle.putExtra("ogretmen_Key", ogretmen_Key);

                startActivity(ogretmen_duzenle);
                finish();

                //Toast.makeText(Ogretmen_Listele_Activity.this, aa_ogretmen_key.getItem(position) , Toast.LENGTH_LONG).show();
            }
        });

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    Ogretmen ogretmen = postSnapshot.getValue(Ogretmen.class);
                    Log.d("o_Ad",ogretmen.getO_Ad());
                    Log.d("o_Soyad",ogretmen.getO_Soyad());
                    Log.d("o_Tel",ogretmen.getO_Tel());
                    Log.d("o_Mail",ogretmen.getO_Mail());
                    Log.d("o_Sifre",ogretmen.getO_Sifre());
                    Log.d("sinif",ogretmen.getSinif());
                    Log.d("o_Tc_No",ogretmen.getO_Tc_No().toString());

                    String key = postSnapshot.getKey();
                    aa_ogretmen_goster.add(ogretmen.getO_Ad() + " " + ogretmen.getO_Soyad().toUpperCase() + "  | Sınıf : " +
                            ogretmen.getSinif());

                    aa_ogretmen_liste.add(ogretmen);

                    aa_ogretmen_key.add(key);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Ogretmen_Listele_Activity.this,"Hata oluştu!", Toast.LENGTH_LONG).show();
            }
        });

        //Listviewin içine adapteri set edip adaptere verdiğimiz ismi yazıyoruz
        lv_ogretmen.setAdapter(aa_ogretmen_goster);
    }
}
