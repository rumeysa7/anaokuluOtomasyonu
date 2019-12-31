package com.example.anaokuludeneme1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
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
    //private HashMap<Integer,String> hp_ogrenci_list = new HashMap<Integer,String>();

    public void init() {

        //actionbarlogin = (Toolbar) findViewById(R.id.actionbarlogin);
        //setSupportActionBar(actionbarlogin);
        getSupportActionBar().setTitle("Müdür Giriş");

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


        final ArrayAdapter<String> aa_ogrenci_liste = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1);

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    Ogrenci ogrenci = postSnapshot.getValue(Ogrenci.class);
                    Log.d("ogrenci_No",ogrenci.getOgrenci_No().toString());
                    Log.d("ogrenci_Ad",ogrenci.getOgrenci_Ad());
                    Log.d("ogrenci_Soyad",ogrenci.getOgrenci_Soyad());
                    Log.d("o_Ad",ogrenci.getO_Ad());

                    if (ogr_adi.equalsIgnoreCase(ogrenci.getO_Ad()))
                    {
                        aa_ogrenci_liste.add(ogrenci.getOgrenci_No().toString() + "     | " + ogrenci.getOgrenci_Ad() + " | " +
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
        lv_ogrenci_Liste.setAdapter(aa_ogrenci_liste);
    }
}
