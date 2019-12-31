package com.example.anaokuluotomasyonu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Yemek_Listesi_Duzenle_Activity extends AppCompatActivity {

    private Toolbar actionbarlogin;
    private EditText edtYemek1, edtYemek2, edtMeyve, edtIcecek;
    private Button btnKaydet, btnYemekListesi;

    private List<String> gunler;
    private Spinner sp_Gunler;
    private Context context = this;
    private ArrayAdapter<String> adapter;

    private FirebaseAuth auth;
    FirebaseDatabase database;

    public void init(){
        getSupportActionBar().setTitle("Yemek Listesi Düzenle ");
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true); //geri oku gözükmesi için

        edtYemek1 = (EditText) findViewById(R.id.edtFood1);
        edtYemek2 = (EditText) findViewById(R.id.edtFood2);
        edtMeyve = (EditText) findViewById(R.id.edtFruit);
        edtIcecek = (EditText) findViewById(R.id.edtDrink);
        btnKaydet = (Button) findViewById(R.id.btnSave);
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

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yemek__listesi__duzenle_);

        init();

        btnKaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yemekKayit();
            }
        });

        btnYemekListesi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent yemek_Listesi = new Intent(Yemek_Listesi_Duzenle_Activity.this, Yemek_Listesi_Goruntule_Activity.class);
                startActivity(yemek_Listesi);
            }
        });

    }

    private void yemekKayit(){

        final String yemek1 = edtYemek1.getText().toString();
        final String yemek2 = edtYemek2.getText().toString();
        final String meyve = edtMeyve.getText().toString();
        final String icecek = edtIcecek.getText().toString();
        final String gun = String.valueOf(sp_Gunler.getSelectedItem());

        //Bundle extras = getIntent().getExtras();
        //final String ogr_adi = extras.getString("ogretmen_Adi");


        if(yemek1.trim().equals("") || yemek2.trim().equals("") || meyve.trim().equals("") || icecek.trim().equals("") || gun.trim().equals("")){
            Toast.makeText(Yemek_Listesi_Duzenle_Activity.this,"Bütün alanları doldurunuz!", Toast.LENGTH_LONG).show();
        }
        else {
            btnKaydet.setEnabled(false);//butona pes pese tıklanmasın diye pasif hale getirdim

            Yemek_Listesi yemek_listesi = new Yemek_Listesi();

            yemek_listesi.setGün(gun);
            yemek_listesi.setYemek1(yemek1);
            yemek_listesi.setYemek2(yemek2);
            yemek_listesi.setMeyve(meyve);
            yemek_listesi.setIcecek(icecek);

            //oluşturduğumuz modeli database e push ediyoruz
            //ddRef.push().setValue(yemek_listesi);
            updateFood(gun, yemek_listesi);
            //push() metodu anahtar eklemek için kullanılır ve bu anahtarlar benzersiz (unique) idlere sahiptirler

            AlertDialog.Builder builder = new AlertDialog.Builder(Yemek_Listesi_Duzenle_Activity.this);
            builder.setCancelable(false);
            builder.setTitle("Yeni Yemek Listesi");
            builder.setMessage("Yeni yemek listesi eklemek ister misiniz ?");
            builder.setPositiveButton("Evet", null);
            builder.setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    Intent yemek_listesi = new Intent(Yemek_Listesi_Duzenle_Activity.this, Main_Ogretmen_Activity.class);
                    //yemek_listesi.putExtra("ogretmen_Adi", ogr_adi);
                    startActivity(yemek_listesi);
                    finish();
                }
            });
            builder.show();

            edtYemek1.getText().clear();
            edtYemek2.getText().clear();
            edtMeyve.getText().clear();
            edtIcecek.getText().clear();

            btnKaydet.setEnabled(true);
        }
    }

    private void updateFood(String key, Yemek_Listesi yemekListesi) {
        final DatabaseReference ddRef = database.getReference().child("Yemek_Listesi");
        ddRef.child(key).setValue(yemekListesi).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(Yemek_Listesi_Duzenle_Activity.this,"Yemek listesi başarıyla güncellendi.",Toast.LENGTH_LONG).show();
            }
        });
    }
}
