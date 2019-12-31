package com.example.anaokuludeneme1;

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

public class Etkinlik_Ekle_Activity extends AppCompatActivity {

    private Toolbar actionbarlogin;
    private EditText txtEtkinlikAdi, txtEtkinlikTarihi, txtEtkinlikAciklamasi;
    private Button btnEtkinlikEkle;

    FirebaseDatabase database;

    private String ogretmen = "Ogretmen";
    private String mudur = "Mudur";

    public void init() {

        //actionbarlogin = (Toolbar) findViewById(R.id.actionbarlogin);
        //setSupportActionBar(actionbarlogin);
        getSupportActionBar().setTitle("Öğretmen Ekle");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //geri oku gözükmesi için

        database = FirebaseDatabase.getInstance();

        txtEtkinlikAdi = (EditText) findViewById(R.id.txtEventName);
        txtEtkinlikTarihi = (EditText) findViewById(R.id.txtEventDate);
        txtEtkinlikAciklamasi = (EditText) findViewById(R.id.txtEventDescribe);
        btnEtkinlikEkle = (Button) findViewById(R.id.btnAddEvent);

        //AlertDialog.Builder builder = new AlertDialog.Builder(Ogretmen_Ekle_Activity.this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etkinlik__ekle_);

        init();



        btnEtkinlikEkle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addEvent();
            }
        });

    }


    private void addEvent(){

        Bundle extras = getIntent().getExtras();
        final String etkinlik_Kontrol = extras.getString("event_Check");

        final String etkinlik_Adi = txtEtkinlikAdi.getText().toString();
        final String etkinlik_Tarihi = txtEtkinlikTarihi.getText().toString();
        final String etkinlik_Aciklama = txtEtkinlikAciklamasi.getText().toString();


        if(etkinlik_Adi.trim().equals("") || etkinlik_Tarihi.trim().equals("") || etkinlik_Aciklama.trim().equals("")){
            Toast.makeText(Etkinlik_Ekle_Activity.this,"Bütün alanları doldurunuz!", Toast.LENGTH_LONG).show();
        }
        else {
            btnEtkinlikEkle.setEnabled(false);//butona pes pese tıklanmasın diye pasif hale getirdim
            DatabaseReference ddRef_Etkinlik = database.getReference().child("Etkinlik");

            Etkinlik etkinlik = new Etkinlik();

            etkinlik.setEtkinlik_Ad(etkinlik_Adi);
            etkinlik.setEtkinlik_Tarih(etkinlik_Tarihi);
            etkinlik.setEtkinlik_Aciklama(etkinlik_Aciklama);


            //oluşturduğumuz modeli database e push ediyoruz
            ddRef_Etkinlik.push().setValue(etkinlik);
            //push() metodu anahtar eklemek için kullanılır ve bu anahtarlar benzersiz (unique) idlere sahiptirler

            Toast.makeText(Etkinlik_Ekle_Activity.this,"Etkinlik başarıyla eklendi :)",Toast.LENGTH_LONG).show();


            AlertDialog.Builder builder = new AlertDialog.Builder(Etkinlik_Ekle_Activity.this);
            builder.setCancelable(false);
            builder.setTitle("Yeni Etkinlik");
            builder.setMessage("Yeni etkinlik eklemek ister misiniz ?");
            builder.setPositiveButton("Evet", null);
            builder.setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    if (etkinlik_Kontrol.equalsIgnoreCase(mudur)) {

                        Intent etkinlik_mudur = new Intent(Etkinlik_Ekle_Activity.this, Main_Yonetici_Activity.class);
                        startActivity(etkinlik_mudur);
                        finish();
                    }
                    else if(etkinlik_Kontrol.equalsIgnoreCase(ogretmen)){

                        Intent etkinlik_ogretmen = new Intent(Etkinlik_Ekle_Activity.this, Main_Ogretmen_Activity.class);
                        startActivity(etkinlik_ogretmen);
                        finish();
                    }

                }
            });
            builder.show();

            txtEtkinlikAdi.getText().clear();
            txtEtkinlikTarihi.getText().clear();
            txtEtkinlikAciklamasi.getText().clear();

            btnEtkinlikEkle.setEnabled(true);
        }

    }
}
