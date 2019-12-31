package com.example.anaokuluotomasyonu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Ogretmen_Duzenle_Activity extends AppCompatActivity {

    private FirebaseDatabase database;
    private DatabaseReference mDatabase;
    private FirebaseAuth auth;

    private EditText txtOgretmenAdi, txtOgretmenSoyadi, txtOgretmenTel, txtOgretmenMail, txtOgretmenSinif, txtOgretmenTc;
    private Button btnOgretmenDuzenle;
    private String ogretmen_Adi, ogretmenSoyadi, ogretmenTel, ogretmenMail, ogretmenSinif, ogretmenSifre, ogretmenKey;
    private Long ogretmenTc;

    public void init(){

        Bundle extras = getIntent().getExtras();
        ogretmen_Adi = extras.getString("o_Ad");
        ogretmenSoyadi = extras.getString("o_Soyad");
        ogretmenTel = extras.getString("o_Tel");
        ogretmenMail = extras.getString("o_Mail");
        ogretmenSifre = extras.getString("o_Sifre");
        ogretmenSinif = extras.getString("sinif");
        ogretmenTc = extras.getLong("o_Tc_No");
        ogretmenKey = extras.getString("ogretmen_Key");

        txtOgretmenAdi = (EditText)findViewById(R.id.txtTeacherName);
        txtOgretmenSoyadi = (EditText)findViewById(R.id.txtTeacherSurname);
        txtOgretmenTel = (EditText)findViewById(R.id.txtTeacherPhone);
        txtOgretmenMail = (EditText)findViewById(R.id.txtTeacherEmail);
        txtOgretmenSinif = (EditText)findViewById(R.id.txtTeacherClass);
        txtOgretmenTc = (EditText)findViewById(R.id.txtTeacherTcNo);

        btnOgretmenDuzenle = (Button) findViewById(R.id.btnTeacherUpdate);

        txtOgretmenAdi.setText(ogretmen_Adi);
        txtOgretmenSoyadi.setText(ogretmenSoyadi);
        txtOgretmenTel.setText(ogretmenTel);
        txtOgretmenMail.setText(ogretmenMail);
        txtOgretmenSinif.setText(ogretmenSinif);
        txtOgretmenTc.setText(ogretmenTc.toString());

        auth = FirebaseAuth.getInstance();

        database = FirebaseDatabase.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference("Ogretmen");


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ogretmen__duzenle_);

        init();

        btnOgretmenDuzenle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ogretmen_Update();
            }
        });
    }

    private void ogretmen_Update()
    {
        final String Ogretmen_Adi = txtOgretmenAdi.getText().toString();
        final String OgretmenSoyadi = txtOgretmenSoyadi.getText().toString();
        final String OgretmenTel = txtOgretmenTel.getText().toString();
        final String OgretmenMail = txtOgretmenMail.getText().toString();
        final String OgretmenSifre = ogretmenSifre;
        final String OgretmenSinif = txtOgretmenSinif.getText().toString();
        final String OgretmenTc = txtOgretmenTc.getText().toString();


        if(Ogretmen_Adi.trim().equals("") || OgretmenSoyadi.trim().equals("") || OgretmenTel.trim().equals("") || OgretmenMail.trim().equals("")
                || OgretmenSinif.trim().equals("") || OgretmenTc.trim().equals("")){
            Toast.makeText(Ogretmen_Duzenle_Activity.this,"Bütün alanları doldurunuz!", Toast.LENGTH_LONG).show();
        }
        else {
            btnOgretmenDuzenle.setEnabled(false);//butona pes pese tıklanmasın diye pasif hale getirdim

            Ogretmen ogretmen = new Ogretmen();

            ogretmen.setO_Ad(Ogretmen_Adi);
            ogretmen.setO_Soyad(OgretmenSoyadi);
            ogretmen.setO_Tel(OgretmenTel);
            ogretmen.setO_Mail(OgretmenMail);
            ogretmen.setO_Sifre(OgretmenSifre);
            ogretmen.setSinif(OgretmenSinif);
            ogretmen.setO_Tc_No(Long.parseLong(OgretmenTc));

            //oluşturduğumuz modeli database e push ediyoruz
            //ddRef.push().setValue(yemek_listesi);
            updateTeacher(ogretmenKey, ogretmen);
            //push() metodu anahtar eklemek için kullanılır ve bu anahtarlar benzersiz (unique) idlere sahiptirler

            Intent ogretmen_Liste = new Intent(Ogretmen_Duzenle_Activity.this, Ogretmen_Listele_Activity.class);
            startActivity(ogretmen_Liste);
            finish();

            txtOgretmenAdi.getText().clear();
            txtOgretmenSoyadi.getText().clear();
            txtOgretmenTel.getText().clear();
            txtOgretmenMail.getText().clear();
            txtOgretmenSinif.getText().clear();
            txtOgretmenTc.getText().clear();

            btnOgretmenDuzenle.setEnabled(true);
        }
    }

    private void updateTeacher(String key, Ogretmen ogretmen) {
        final DatabaseReference ddRef = database.getReference().child("Ogretmen");
        ddRef.child(key).setValue(ogretmen).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(Ogretmen_Duzenle_Activity.this,"Öğretmen  başarıyla güncellendi.", Toast.LENGTH_LONG).show();
            }
        });
    }
}
