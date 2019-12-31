package com.example.anaokuluotomasyonu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Ogrenci_Duzenle_Activity extends AppCompatActivity {

    private FirebaseDatabase database;
    private DatabaseReference mDatabase;
    private FirebaseAuth auth;

    private EditText txtOgrenciAdi, txtOgrenciSoyadi, txtOgrenciNo, txtOgrenciTcNo, txtOgrenciVeli1, txtOgrenciVeli2, txtOgrenciBoy, txtOgrenciKilo,
            txtOgrenciDogumGunu, txtOgrenciSinif, txtOgrenciOgretmen, txtOgrenciIlac;
    private Button btnOgrenciDuzenle;

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

    private String ogrenci_ogr_Adi;

    public void init() {

        Bundle extras = getIntent().getExtras();
        ogrenci_No = extras.getInt("ogrenci_No");
        ogrenci_Ad = extras.getString("ogrenci_Ad");
        ogrenci_Soyad = extras.getString("ogrenci_Soyad");
        ogrenci_Tc_No = extras.getLong("ogrenci_Tc_No");
        ogrenci_Veli1 = extras.getString("ogrenci_Veli1");
        ogrenci_Veli2 = extras.getString("ogrenci_Veli2");
        ogrenci_Boy = extras.getString("ogrenci_Boy");
        ogrenci_Kilo = extras.getString("ogrenci_Kilo");
        ogrenci_Dogum_Tarih = extras.getString("ogrenci_Dogum_Tarih");
        ogrenci_Sinif = extras.getString("ogrenci_Sinif");
        o_Ad = extras.getString("o_Ad");
        ogrenci_Ilac = extras.getString("ogrenci_Ilac");
        ogrenci_Key = extras.getString("ogrenci_Key");

        ogrenci_ogr_Adi = extras.getString("ogr_Adi");

        txtOgrenciAdi = (EditText) findViewById(R.id.txtStudentName);
        txtOgrenciSoyadi = (EditText) findViewById(R.id.txtStudentSurname);
        txtOgrenciNo = (EditText) findViewById(R.id.txtStudentNo);
        txtOgrenciTcNo = (EditText) findViewById(R.id.txtStudentTcNo);
        txtOgrenciVeli1 = (EditText) findViewById(R.id.txtStudentParent1);
        txtOgrenciVeli2 = (EditText) findViewById(R.id.txtStudentParent2);
        txtOgrenciBoy = (EditText) findViewById(R.id.txtStudentHeight);
        txtOgrenciKilo = (EditText) findViewById(R.id.txtStudentWeight);
        txtOgrenciDogumGunu = (EditText) findViewById(R.id.txtStudentBirthday);
        txtOgrenciSinif = (EditText) findViewById(R.id.txtStudentClass);
        txtOgrenciOgretmen = (EditText) findViewById(R.id.txtStudentTeacher);
        txtOgrenciIlac = (EditText) findViewById(R.id.txtStudentMedicine);

        btnOgrenciDuzenle = (Button) findViewById(R.id.btnStudentUpdate);

        txtOgrenciAdi.setText(ogrenci_Ad);
        txtOgrenciSoyadi.setText(ogrenci_Soyad);
        txtOgrenciNo.setText(ogrenci_No.toString());
        txtOgrenciTcNo.setText(ogrenci_Tc_No.toString());
        txtOgrenciVeli1.setText(ogrenci_Veli1);
        txtOgrenciVeli2.setText(ogrenci_Veli2);
        txtOgrenciBoy.setText(ogrenci_Boy);
        txtOgrenciKilo.setText(ogrenci_Kilo);
        txtOgrenciDogumGunu.setText(ogrenci_Dogum_Tarih);
        txtOgrenciSinif.setText(ogrenci_Sinif);
        txtOgrenciOgretmen.setText(o_Ad);
        txtOgrenciIlac.setText(ogrenci_Ilac);

        auth = FirebaseAuth.getInstance();

        database = FirebaseDatabase.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference("Ogrenci");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ogrenci__duzenle_);

        init();

        btnOgrenciDuzenle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ogrenci_Update();
            }
        });
    }

    private void ogrenci_Update() {
        final String Ogrenci_No = txtOgrenciNo.getText().toString();
        final String Ogrenci_Ad = txtOgrenciAdi.getText().toString();
        final String Ogrenci_Soyad = txtOgrenciSoyadi.getText().toString();
        final String Ogrenci_Tc_No = txtOgrenciTcNo.getText().toString();
        final String Ogrenci_Veli1 = txtOgrenciVeli1.getText().toString();
        final String Ogrenci_Veli2 = txtOgrenciVeli2.getText().toString();
        final String Ogrenci_Boy = txtOgrenciBoy.getText().toString();
        final String Ogrenci_Kilo = txtOgrenciKilo.getText().toString();
        final String Ogrenci_Dogum_Tarih = txtOgrenciDogumGunu.getText().toString();
        final String Ogrenci_Sinif = txtOgrenciSinif.getText().toString();
        final String O_Ad = txtOgrenciOgretmen.getText().toString();
        final String Ogrenci_Ilac = txtOgrenciIlac.getText().toString();

        if (Ogrenci_No.trim().equals("") || Ogrenci_Ad.trim().equals("") || Ogrenci_Soyad.trim().equals("") || Ogrenci_Tc_No.trim().equals("")
                || Ogrenci_Veli1.trim().equals("") || Ogrenci_Veli2.trim().equals("") || Ogrenci_Boy.trim().equals("") || Ogrenci_Kilo.trim().equals("")
                || Ogrenci_Dogum_Tarih.trim().equals("") || Ogrenci_Sinif.trim().equals("") || O_Ad.trim().equals("")
                || Ogrenci_Ilac.trim().equals("")) {
            Toast.makeText(Ogrenci_Duzenle_Activity.this, "Bütün alanları doldurunuz!", Toast.LENGTH_LONG).show();
        } else {
            btnOgrenciDuzenle.setEnabled(false);//butona pes pese tıklanmasın diye pasif hale getirdim

            Ogrenci ogrenci = new Ogrenci();

            ogrenci.setOgrenci_No(Integer.parseInt(Ogrenci_No));
            ogrenci.setOgrenci_Ad(Ogrenci_Ad);
            ogrenci.setOgrenci_Soyad(Ogrenci_Soyad);
            ogrenci.setOgrenci_Tc_No(Long.parseLong(Ogrenci_Tc_No));
            ogrenci.setOgrenci_Veli1(Ogrenci_Veli1);
            ogrenci.setOgrenci_Veli2(Ogrenci_Veli2);
            ogrenci.setOgrenci_Boy(Ogrenci_Boy);
            ogrenci.setOgrenci_Kilo(Ogrenci_Kilo);
            ogrenci.setOgrenci_Dogum_Tarih(Ogrenci_Dogum_Tarih);
            ogrenci.setOgrenci_Sinif(Ogrenci_Sinif);
            ogrenci.setO_Ad(O_Ad);
            ogrenci.setOgrenci_Ilac(Ogrenci_Ilac);

            //oluşturduğumuz modeli database e push ediyoruz
            updateStudent(ogrenci_Key, ogrenci);

            Intent ogretmen_Liste = new Intent(Ogrenci_Duzenle_Activity.this, Ogrenci_Liste_Activity.class);
            ogretmen_Liste.putExtra("ogretmen_Adi", ogrenci_ogr_Adi);
            startActivity(ogretmen_Liste);
            finish();

            btnOgrenciDuzenle.setEnabled(true);
        }
    }

    private void updateStudent(String key, Ogrenci ogrenci) {
        final DatabaseReference ddRef = database.getReference().child("Ogrenci");
        ddRef.child(key).setValue(ogrenci).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(Ogrenci_Duzenle_Activity.this, "Öğrenci  başarıyla güncellendi.", Toast.LENGTH_LONG).show();
            }
        });
    }
}
