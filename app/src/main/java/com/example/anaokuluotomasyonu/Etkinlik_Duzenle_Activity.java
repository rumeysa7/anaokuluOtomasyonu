package com.example.anaokuluotomasyonu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.annotation.IntegerRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Etkinlik_Duzenle_Activity extends AppCompatActivity {

    private FirebaseDatabase database;
    private DatabaseReference mDatabase;
    private FirebaseAuth auth;
    private ListView lv_etkinlik;

    private String etkinlik_Adi, etkinlik_Tarihi, etkinlik_Aciklamasi, etkinlik_Key;

    public void init(){

        auth = FirebaseAuth.getInstance();

        database = FirebaseDatabase.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference("Etkinlik");
        lv_etkinlik = (ListView)findViewById(R.id.activity_listview);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etkinlik__duzenle_);

        init();
        final ArrayAdapter<Etkinlik> aa_etkinlik_liste = new ArrayAdapter<Etkinlik>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1);

        final ArrayAdapter<String> aa_etkinlik_goster = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1);
        final ArrayAdapter<String> aa_etkinlik_key = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1);

        //Bundle extras = getIntent().getExtras();
        //final String ogr_adi = extras.getString("ogretmen_Adi");

        lv_etkinlik.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Etkinlik elde = new Etkinlik();
                elde = aa_etkinlik_liste.getItem(position);
                etkinlik_Adi = elde.getEtkinlik_Ad();
                etkinlik_Aciklamasi = elde.getEtkinlik_Aciklama();
                etkinlik_Tarihi = elde.getEtkinlik_Tarih();
                etkinlik_Key = aa_etkinlik_key.getItem(position);

                Intent etkinlik_duzenle = new Intent(Etkinlik_Duzenle_Activity.this, Etkinlik_Degistir_Activity.class);
                etkinlik_duzenle.putExtra("etkinlik_Adi", etkinlik_Adi);
                etkinlik_duzenle.putExtra("etkinlik_Aciklamasi", etkinlik_Aciklamasi);
                etkinlik_duzenle.putExtra("etkinlik_Tarihi", etkinlik_Tarihi);
                etkinlik_duzenle.putExtra("etkinlik_Key", etkinlik_Key);

                startActivity(etkinlik_duzenle);
                finish();

                //Toast.makeText(Etkinlik_Duzenle_Activity.this, aa_etkinlik_key.getItem(position) , Toast.LENGTH_LONG).show();

            }
        });




        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    Etkinlik etkinlik = postSnapshot.getValue(Etkinlik.class);
                    Log.d("etkinlik_Ad",etkinlik.getEtkinlik_Ad());
                    Log.d("etkinlik_Tarih",etkinlik.getEtkinlik_Tarih());
                    Log.d("etkinlik_Aciklama",etkinlik.getEtkinlik_Aciklama());
                    String key = postSnapshot.getKey();
                    aa_etkinlik_goster.add(etkinlik.getEtkinlik_Ad() + "  |  " + etkinlik.getEtkinlik_Tarih() + "  |  " +
                            etkinlik.getEtkinlik_Aciklama());

                    aa_etkinlik_liste.add(etkinlik);

                    aa_etkinlik_key.add(key);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Etkinlik_Duzenle_Activity.this,"Hata oluştu!", Toast.LENGTH_LONG).show();
            }
        });


        //Listviewin içine adapteri set edip adaptere verdiğimiz ismi yazıyoruz
        lv_etkinlik.setAdapter(aa_etkinlik_goster);


        /*btnEtkinlikDuzenle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/
    }

}
