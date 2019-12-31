package com.example.anaokuluotomasyonu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Mudur_Ekle_Activity extends AppCompatActivity {

    private Toolbar actionbarlogin;
    private EditText txtAdi, txtSoyadi, txtNo, txtEmail, txtSifre, txtGorev, txtTc;
    private Button btnMudurEkle;

    FirebaseDatabase database;


    public void init() {

        //actionbarlogin = (Toolbar) findViewById(R.id.actionbarlogin);
        //setSupportActionBar(actionbarlogin);
        getSupportActionBar().setTitle("Müdür Ekle");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //geri oku gözükmesi için

        database = FirebaseDatabase.getInstance();

        txtAdi = (EditText) findViewById(R.id.txtManagerName);
        txtSoyadi = (EditText) findViewById(R.id.txtManagerSurname);
        txtNo = (EditText) findViewById(R.id.txtManagerNo);
        txtEmail = (EditText) findViewById(R.id.txtManagerEmail);
        txtSifre = (EditText) findViewById(R.id.txtManagerPassword);
        txtGorev = (EditText) findViewById(R.id.txtManagerTask);
        txtTc = (EditText) findViewById(R.id.txtManagerTc);

        btnMudurEkle = (Button) findViewById(R.id.btnAddManager);

        //AlertDialog.Builder builder = new AlertDialog.Builder(Ogretmen_Ekle_Activity.this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mudur__ekle_);

        init();

        btnMudurEkle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mudurEkle();
            }
        });

    }

    private void mudurEkle(){

        final String m_Ad = txtAdi.getText().toString();
        final String m_Soyad = txtSoyadi.getText().toString();
        final String m_Tel = txtNo.getText().toString();
        final String m_Mail = txtEmail.getText().toString();
        final String m_Sifre = txtSifre.getText().toString();
        final String m_Gorev = txtGorev.getText().toString();
        final String m_Tc = txtTc.getText().toString();

        if(m_Ad.trim().equals("") || m_Soyad.trim().equals("") || m_Tel.trim().equals("") || m_Mail.trim().equals("") || m_Sifre.trim().equals("")
                || m_Gorev.trim().equals("") || m_Tc.trim().equals("")){
            Toast.makeText(Mudur_Ekle_Activity.this,"Bütün alanları doldurunuz!", Toast.LENGTH_LONG).show();
        }
        else {
            btnMudurEkle.setEnabled(false);//butona pes pese tıklanmasın diye pasif hale getirdim
            DatabaseReference ddRef = database.getReference().child("Yonetici");

            Yonetici mudur = new Yonetici();

            mudur.setY_Ad(m_Ad);
            mudur.setY_Soyad(m_Soyad);
            mudur.setY_Tel(m_Tel);
            mudur.setY_Mail(m_Mail);
            mudur.setY_Sifre(m_Sifre);
            mudur.setGorev(m_Gorev);
            mudur.setY_Tc_No(Long.parseLong(m_Tc));

            //oluşturduğumuz modeli database e push ediyoruz
            ddRef.push().setValue(mudur);
            //push() metodu anahtar eklemek için kullanılır ve bu anahtarlar benzersiz (unique) idlere sahiptirler

            Toast.makeText(Mudur_Ekle_Activity.this,"Müdür başarıyla eklendi :)",Toast.LENGTH_LONG).show();

            AlertDialog.Builder builder = new AlertDialog.Builder(Mudur_Ekle_Activity.this);
            builder.setCancelable(false);
            builder.setTitle("Yeni Müdür");
            builder.setMessage("Yeni müdür eklemek ister misiniz ?");
            builder.setPositiveButton("Evet", null);
            builder.setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    Intent main_yonetici = new Intent(Mudur_Ekle_Activity.this, Mudur_Giris_Activity.class);
                    startActivity(main_yonetici);
                    finish();
                }
            });
            builder.show();

            txtAdi.getText().clear();
            txtSoyadi.getText().clear();
            txtNo.getText().clear();
            txtEmail.getText().clear();
            txtSifre.getText().clear();
            txtGorev.getText().clear();
            txtTc.getText().clear();

            btnMudurEkle.setEnabled(true);
        }

    }
}