package com.example.anaokuluotomasyonu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.annotation.NonNull;

import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Etkinlik_Liste_Activity extends AppCompatActivity {


    private FirebaseDatabase database;
    private DatabaseReference mDatabase;
    private ListView lv_etkinlik;
    private FirebaseAuth auth;

    public void init(){

        auth = FirebaseAuth.getInstance();

        database = FirebaseDatabase.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference("Etkinlik");
        lv_etkinlik = (ListView)findViewById(R.id.activity_listview);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etkinlik__liste_);

        init();

        //Bundle extras = getIntent().getExtras();
        //final String ogr_adi = extras.getString("ogretmen_Adi");


        final ArrayAdapter<String> aa_etkinlik_liste = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1);

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    Etkinlik etkinlik = postSnapshot.getValue(Etkinlik.class);
                    Log.d("etkinlik_Ad",etkinlik.getEtkinlik_Ad());
                    Log.d("etkinlik_Tarih",etkinlik.getEtkinlik_Tarih());
                    Log.d("etkinlik_Aciklama",etkinlik.getEtkinlik_Aciklama());
                    //Log.d("o_Ad",ogrenci.getO_Ad());

                    /*if (ogr_adi.equalsIgnoreCase(ogrenci.getO_Ad()))
                    {
                        aa_ogrenci_liste.add(ogrenci.getOgrenci_No().toString() + "     | " + ogrenci.getOgrenci_Ad() + " | " +
                                ogrenci.getOgrenci_Soyad().toUpperCase());
                    }
                    */
                    aa_etkinlik_liste.add(etkinlik.getEtkinlik_Ad() + "  |  " + etkinlik.getEtkinlik_Tarih() + "  |  " +
                            etkinlik.getEtkinlik_Aciklama());

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Etkinlik_Liste_Activity.this,"Hata oluştu!", Toast.LENGTH_LONG).show();
            }
        });


        //Listviewin içine adapteri set edip adaptere verdiğimiz ismi yazıyoruz
        lv_etkinlik.setAdapter(aa_etkinlik_liste);
    }

}