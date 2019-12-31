package com.example.anaokuludeneme1;

import androidx.annotation.NonNull;
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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Ogretmen_Giris_Activity extends AppCompatActivity {


    private Toolbar actionbarlogin;
    private EditText txtOgretmenMail, txtOgretmenSifre;
    private Button btnOgretmenGiris, btnOgretmenListele;

    private FirebaseAuth auth;

    FirebaseDatabase database;

    public void init(){

        //actionbarlogin = (Toolbar) findViewById(R.id.actionbarlogin);
        //setSupportActionBar(actionbarlogin);
        getSupportActionBar().setTitle("Öğretmen Girişi");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //geri oku gözükmesi için

        txtOgretmenMail = (EditText) findViewById(R.id.txtEmailLogin);
        txtOgretmenSifre = (EditText) findViewById(R.id.txtPasswordLogin);
        btnOgretmenGiris = (Button) findViewById(R.id.btnTeacherLogin);
        btnOgretmenListele = (Button) findViewById(R.id.btnOgretmenListele);

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ogretmen__giris_);


        init();

        btnOgretmenGiris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
            }
        });
        btnOgretmenListele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intentRegister = new Intent(Ogretmen_Giris_Activity.this, Kayit_Ol_Activity.class);
                //startActivity(intentRegister);
            }
        });
    }


    private void loginUser() {

        final String email = txtOgretmenMail.getText().toString();
        final String password = txtOgretmenSifre.getText().toString();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Email alanı boş bırakılamaz!",Toast.LENGTH_LONG).show();
        }else if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Şifre alanı boş bırakılamaz!",Toast.LENGTH_LONG).show();
        }
        else{
            // iki alanımda doluysa burdaki işlemler gerçekleşecek

            DatabaseReference dbRef= FirebaseDatabase.getInstance().getReference("Ogretmen");

            dbRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    for(DataSnapshot ds:dataSnapshot.getChildren()){
                        Ogretmen ogretmen = ds.getValue(Ogretmen.class);
                        Log.d("o_Mail",ogretmen.getO_Mail());
                        Log.d("o_Sifre",ogretmen.getO_Sifre());
                        Log.d("o_Ad", ogretmen.getO_Ad());

                        if(email.equalsIgnoreCase(ogretmen.getO_Mail()) && password.equalsIgnoreCase(ogretmen.getO_Sifre()))  //Mail ile şifre veritabanı ile eşleşirse
                        {
                            Toast.makeText(Ogretmen_Giris_Activity.this,"Giriş başarılı :)",Toast.LENGTH_LONG).show();
                            Intent ogretmen_Giris = new Intent(Ogretmen_Giris_Activity.this, Main_Ogretmen_Activity.class);
                            ogretmen_Giris.putExtra("ogretmen_Adi",ogretmen.getO_Ad());
                            startActivity(ogretmen_Giris);
                            finish();
                        }
                        else {
                            Toast.makeText(Ogretmen_Giris_Activity.this,"Email ya da Şifre Yanlış",Toast.LENGTH_LONG).show();
                            btnOgretmenGiris.setEnabled(true);
                        }

                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    System.out.println("The read failed: " + databaseError.getMessage());
                }
            });


            btnOgretmenGiris.setEnabled(false);//butona pes pese tıklanmasın diye pasif hale getirdim
            /*
            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if(task.isSuccessful()){
                        //giris basarılı ise
                        Toast.makeText(Ogretmen_Giris_Activity.this,"Giriş başarılı :)",Toast.LENGTH_LONG).show();
                        Intent mainIntent = new Intent(Ogretmen_Giris_Activity.this,Main_Ogretmen_Activity.class);
                        startActivity(mainIntent);
                        finish();
                    }
                    else{
                        //Toast.makeText(Ogretmen_Giris_Activity.this,"İşlem tamamlanamadı. Email ya da Şifre Yanlış",Toast.LENGTH_LONG).show();
                        btnOgretmenGiris.setEnabled(true);
                    }
                }
            });

             */
        }
    }
}


