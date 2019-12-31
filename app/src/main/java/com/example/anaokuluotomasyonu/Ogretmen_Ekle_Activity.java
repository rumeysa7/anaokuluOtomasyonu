package com.example.anaokuluotomasyonu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.math.BigInteger;

public class Ogretmen_Ekle_Activity extends AppCompatActivity {

    private Toolbar actionbarlogin;
    private EditText txtAdi, txtSoyadi, txtNo, txtEmail, txtSifre, txtSinif, txtTc;
    private Button btnOgretmenEkle;

    FirebaseDatabase database;


    public void init() {

        //actionbarlogin = (Toolbar) findViewById(R.id.actionbarlogin);
        //setSupportActionBar(actionbarlogin);
        getSupportActionBar().setTitle("Öğretmen Ekle");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //geri oku gözükmesi için

        database = FirebaseDatabase.getInstance();

        txtAdi = (EditText) findViewById(R.id.txtTeacherName);
        txtSoyadi = (EditText) findViewById(R.id.txtTeacherSurname);
        txtNo = (EditText) findViewById(R.id.txtTeacherNo);
        txtEmail = (EditText) findViewById(R.id.txtTeacherEmail);
        txtSifre = (EditText) findViewById(R.id.txtTeacherPassword);
        txtSinif = (EditText) findViewById(R.id.txtTeacherClass);
        txtTc = (EditText) findViewById(R.id.txtTeacherTc);
        btnOgretmenEkle = (Button) findViewById(R.id.btnAddTeacher);

        //AlertDialog.Builder builder = new AlertDialog.Builder(Ogretmen_Ekle_Activity.this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ogretmen__ekle_);

        init();

        btnOgretmenEkle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String o_Ad = txtAdi.getText().toString();
                final String o_Soyad = txtSoyadi.getText().toString();
                final String o_Tel = txtNo.getText().toString();
                final String o_Mail = txtEmail.getText().toString();
                final String o_Sifre = txtSifre.getText().toString();
                final String o_Sinif = txtSinif.getText().toString();
                final String o_Tc = txtTc.getText().toString();

                if(o_Ad.trim().equals("") || o_Soyad.trim().equals("") || o_Tel.trim().equals("") || o_Mail.trim().equals("") || o_Sifre.trim().equals("")
                        || o_Sinif.trim().equals("") || o_Tc.trim().equals("")){
                    Toast.makeText(Ogretmen_Ekle_Activity.this,"Bütün alanları doldurunuz!", Toast.LENGTH_LONG).show();
                }
                else {
                    btnOgretmenEkle.setEnabled(false);//butona pes pese tıklanmasın diye pasif hale getirdim
                    DatabaseReference ddRef = database.getReference().child("Ogretmen");

                    Ogretmen ogretmen = new Ogretmen();

                    ogretmen.setO_Ad(o_Ad);
                    ogretmen.setO_Soyad(o_Soyad);
                    ogretmen.setO_Tel(o_Tel);
                    ogretmen.setO_Mail(o_Mail);
                    ogretmen.setO_Sifre(o_Sifre);
                    ogretmen.setSinif(o_Sinif);
                    ogretmen.setO_Tc_No(Long.parseLong(o_Tc));

                    //oluşturduğumuz modeli database e push ediyoruz
                    ddRef.push().setValue(ogretmen);
                    //push() metodu anahtar eklemek için kullanılır ve bu anahtarlar benzersiz (unique) idlere sahiptirler

                    Toast.makeText(Ogretmen_Ekle_Activity.this,"Öğretmen başarıyla eklendi :)",Toast.LENGTH_LONG).show();

                    AlertDialog.Builder builder = new AlertDialog.Builder(Ogretmen_Ekle_Activity.this);
                    builder.setCancelable(false);
                    builder.setTitle("Yeni Öğretmen");
                    builder.setMessage("Yeni öğretmen eklemek ister misiniz ?");
                    builder.setPositiveButton("Evet", null);
                    builder.setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            Intent main_yonetici = new Intent(Ogretmen_Ekle_Activity.this, Main_Yonetici_Activity.class);
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
                    txtSinif.getText().clear();
                    txtTc.getText().clear();
                    btnOgretmenEkle.setEnabled(true);
                }
            }
        });
    }
}
