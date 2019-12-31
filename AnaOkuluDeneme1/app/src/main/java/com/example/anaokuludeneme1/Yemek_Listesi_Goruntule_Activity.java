package com.example.anaokuludeneme1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Yemek_Listesi_Goruntule_Activity extends AppCompatActivity {

    private Toolbar actionbarlogin;
    private TextView txtYemek1, txtYemek2, txtMeyve, txtIcecek;
    private Button btnYemekListesi;

    private List<String> gunler;
    private Spinner sp_Gunler;
    private Context context = this;
    private ArrayAdapter<String> adapter;

    private FirebaseAuth auth;
    FirebaseDatabase database;
    private DatabaseReference mDatabase;

    public void init(){
        getSupportActionBar().setTitle("Yemek Listesi");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //geri oku gözükmesi için

        txtYemek1 = (TextView) findViewById(R.id.edttxtFood1);
        txtYemek2 = (TextView) findViewById(R.id.edttxtFood2);
        txtMeyve = (TextView) findViewById(R.id.edttxtFruit);
        txtIcecek = (TextView) findViewById(R.id.edttxtDrink);
        btnYemekListesi = (Button) findViewById(R.id.btnFoodList);

        gunler = new ArrayList<>();

        gunler.add("Pazartesi");
        gunler.add("Salı");
        gunler.add("Çarşamba");
        gunler.add("Perşembe");
        gunler.add("Cuma");

        sp_Gunler = (Spinner)findViewById(R.id.spDay);
        adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, gunler);
        sp_Gunler.setAdapter(adapter);

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference("Yemek_Listesi");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yemek__listesi__goruntule_);

        init();

        btnYemekListesi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yemekListele();
            }
        });
    }

    private void yemekListele(){

        final String gun = String.valueOf(sp_Gunler.getSelectedItem());

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    Yemek_Listesi yemek_listesi = postSnapshot.getValue(Yemek_Listesi.class);
                    Log.d("gün",yemek_listesi.getGün());
                    Log.d("yemek1",yemek_listesi.getYemek1());
                    Log.d("yemek2",yemek_listesi.getYemek2());
                    Log.d("meyve",yemek_listesi.getMeyve());
                    Log.d("icecek",yemek_listesi.getIcecek());

                    if (gun.equalsIgnoreCase(yemek_listesi.getGün()))
                    {
                        txtYemek1.setText(yemek_listesi.getYemek1());
                        txtYemek2.setText(yemek_listesi.getYemek2());
                        txtMeyve.setText(yemek_listesi.getMeyve());
                        txtIcecek.setText(yemek_listesi.getIcecek());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Yemek_Listesi_Goruntule_Activity.this,"Hata oluştu!", Toast.LENGTH_LONG).show();
            }
        });

    }
}
