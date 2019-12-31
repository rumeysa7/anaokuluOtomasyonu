package com.example.anaokuluotomasyonu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Veli_Giris_Activity extends AppCompatActivity {

    private Toolbar actionbarlogin;
    private EditText txtVeliMail, txtVeliSifre;
    private Button btnVeliGiris;

    private FirebaseAuth auth;
    FirebaseDatabase database;

    public void init() {

        //actionbarlogin = (Toolbar) findViewById(R.id.actionbarlogin);
        //setSupportActionBar(actionbarlogin);
        //getSupportActionBar().setTitle("Veli Girişi");
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true); //geri oku gözükmesi için

        txtVeliMail = (EditText) findViewById(R.id.txtVeliMail);
        txtVeliSifre = (EditText) findViewById(R.id.txtVeliPassword);
        btnVeliGiris = (Button) findViewById(R.id.btnVeliLogin);

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_veli__giris_);
        init();

        btnVeliGiris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
            }
        });
    }


    private void loginUser() {

        final String email = txtVeliMail.getText().toString();
        final String password = txtVeliSifre.getText().toString();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Email alanı boş bırakılamaz!", Toast.LENGTH_LONG).show();
        } else if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Şifre alanı boş bırakılamaz!", Toast.LENGTH_LONG).show();
        } else {
            // iki alanımda doluysa burdaki işlemler gerçekleşecek

            DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("Veli");

            dbRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        Veli veli = ds.getValue(Veli.class);
                        Log.d("v_Mail", veli.getv_Mail());
                        Log.d("v_Sifre", veli.getv_Sifre());
                        //Log.d("v_Ad", veli.getO_Ad());

                        if (email.equalsIgnoreCase(veli.getv_Mail()) && password.equalsIgnoreCase(veli.getv_Sifre()))  //Mail ile şifre veritabanı ile eşleşirse
                        {
                            Toast.makeText(Veli_Giris_Activity.this, "Giriş başarılı :)", Toast.LENGTH_LONG).show();
                            Intent veli_Giris = new Intent(Veli_Giris_Activity.this, Main_Veli_Activity.class);
                            startActivity(veli_Giris);
                            finish();
                        } else {
                            Toast.makeText(Veli_Giris_Activity.this, "Email ya da Şifre Yanlış", Toast.LENGTH_LONG).show();
                            btnVeliGiris.setEnabled(true);
                        }

                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    System.out.println("The read failed: " + databaseError.getMessage());
                }
            });

            btnVeliGiris.setEnabled(false);//butona pes pese tıklanmasın diye pasif hale getirdim
        }
    }
}