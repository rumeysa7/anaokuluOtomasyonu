package com.example.anaokuluotomasyonu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.strictmode.WebViewMethodCalledOnWrongThreadViolation;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Etkinlik_Degistir_Activity extends AppCompatActivity {


    private FirebaseDatabase database;
    private DatabaseReference mDatabase;
    private FirebaseAuth auth;

    private EditText txtEtkinlikAdi, txtEtkinlikTarih, txtEtkinlikAciklama;
    private Button btnEtkinlikDuzenle;
    private String etkinlik_Adi, etkinlik_Tarihi, etkinlik_Aciklamasi, etkinlik_Key;

    public void init(){

        Bundle extras = getIntent().getExtras();
        etkinlik_Adi = extras.getString("etkinlik_Adi");
        etkinlik_Aciklamasi = extras.getString("etkinlik_Aciklamasi");
        etkinlik_Tarihi = extras.getString("etkinlik_Tarihi");
        etkinlik_Key = extras.getString("etkinlik_Key");

        txtEtkinlikAciklama = (EditText)findViewById(R.id.txtEventDescribe);
        txtEtkinlikAdi = (EditText)findViewById(R.id.txtEventName);
        txtEtkinlikTarih = (EditText)findViewById(R.id.txtEventDate);

        txtEtkinlikAciklama.setText(etkinlik_Aciklamasi);
        txtEtkinlikAdi.setText(etkinlik_Adi);
        txtEtkinlikTarih.setText(etkinlik_Tarihi);
        auth = FirebaseAuth.getInstance();

        database = FirebaseDatabase.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference("Etkinlik");
        btnEtkinlikDuzenle = (Button) findViewById(R.id.btnEventUpdate);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etkinlik__degistir_);

        init();

        btnEtkinlikDuzenle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etkinlik_Update();
            }
        });
    }

    private void etkinlik_Update()
    {
        final String Etkinlik_Adi = txtEtkinlikAdi.getText().toString();
        final String Etkinlik_Aciklama = txtEtkinlikAciklama.getText().toString();
        final String Etkinlik_Tarih = txtEtkinlikTarih.getText().toString();

        if(Etkinlik_Adi.trim().equals("") || Etkinlik_Aciklama.trim().equals("") || Etkinlik_Tarih.trim().equals("")){
            Toast.makeText(Etkinlik_Degistir_Activity.this,"Bütün alanları doldurunuz!", Toast.LENGTH_LONG).show();
        }
        else {
            btnEtkinlikDuzenle.setEnabled(false);//butona pes pese tıklanmasın diye pasif hale getirdim

            Etkinlik etkinlik = new Etkinlik();

            etkinlik.setEtkinlik_Ad(Etkinlik_Adi);
            etkinlik.setEtkinlik_Aciklama(Etkinlik_Aciklama);
            etkinlik.setEtkinlik_Tarih(Etkinlik_Tarih);


            //oluşturduğumuz modeli database e push ediyoruz
            //ddRef.push().setValue(yemek_listesi);
            updateEvent(etkinlik_Key, etkinlik);
            //push() metodu anahtar eklemek için kullanılır ve bu anahtarlar benzersiz (unique) idlere sahiptirler

            txtEtkinlikTarih.getText().clear();
            txtEtkinlikAciklama.getText().clear();
            txtEtkinlikAdi.getText().clear();

            btnEtkinlikDuzenle.setEnabled(true);
        }
    }

    private void updateEvent(String key, Etkinlik etkinlik) {
        final DatabaseReference ddRef = database.getReference().child("Etkinlik");
        ddRef.child(key).setValue(etkinlik).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(Etkinlik_Degistir_Activity.this,"Etkinlik başarıyla güncellendi.",Toast.LENGTH_LONG).show();
            }
        });
    }
}
