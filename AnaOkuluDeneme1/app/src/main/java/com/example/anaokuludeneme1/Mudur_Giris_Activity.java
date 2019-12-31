package com.example.anaokuludeneme1;

import androidx.annotation.MainThread;
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

public class Mudur_Giris_Activity extends AppCompatActivity {

    Button btnManagerLogin, btnManagerKaydol;
    Toolbar actionbarlogin;
    EditText txtManagerEmailLogin, txtManagerPasswordLogin;

    private String email_Check = "administrator";
    private String password_Check = "1q2w3e4r";

    private FirebaseAuth auth;

    public void init() {

        //actionbarlogin = (Toolbar) findViewById(R.id.actionbarlogin);
        //setSupportActionBar(actionbarlogin);
        getSupportActionBar().setTitle("Müdür Giriş");
        auth = FirebaseAuth.getInstance();

        txtManagerEmailLogin = (EditText) findViewById(R.id.txtManagerEmailLogin);
        txtManagerPasswordLogin = (EditText) findViewById(R.id.txtManagerPasswordLogin);
        btnManagerLogin = (Button) findViewById(R.id.btnManagerLogin);
        btnManagerKaydol = (Button) findViewById(R.id.btnManagerKaydol);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mudur__giris_);

        init();

        btnManagerLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();

                /*
                DatabaseReference ddRef= FirebaseDatabase.getInstance().getReference().child("Yonetici");
                Yonetici mudur= new Yonetici();
                mudur.setY_Mail(txtManagerEmailLogin.getText().toString());
                mudur.setY_Sifre(txtManagerPasswordLogin.getText().toString());
                //oluşturduğumuz modeli database e push ediyoruz
                ddRef.push().setValue(mudur);

                 */
            }
        });

        btnManagerKaydol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String email = txtManagerEmailLogin.getText().toString();
                final String password = txtManagerPasswordLogin.getText().toString();

                if(email.equalsIgnoreCase(email_Check) && password.equalsIgnoreCase(password_Check))  //Mail ile şifre kontrol ile eşleşirse
                {
                    txtManagerEmailLogin.getText().clear();
                    txtManagerPasswordLogin.getText().clear();

                    Toast.makeText(Mudur_Giris_Activity.this,"Admin girişi başarılı :)",Toast.LENGTH_LONG).show();
                    Intent yonetici_Kayit = new Intent(Mudur_Giris_Activity.this, Mudur_Ekle_Activity.class);
                    startActivity(yonetici_Kayit);
                    finish();
                }
                else {
                    Toast.makeText(Mudur_Giris_Activity.this,"Geçersiz email veya şifre",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void loginUser() {

        final String email = txtManagerEmailLogin.getText().toString();
        final String password = txtManagerPasswordLogin.getText().toString();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Email alanı boş bırakılamaz!",Toast.LENGTH_LONG).show();
        }else if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Şifre alanı boş bırakılamaz!",Toast.LENGTH_LONG).show();
        }
        else{
            // iki alanımda doluysa burdaki işlemler gerçekleşecek

            DatabaseReference dbRef= FirebaseDatabase.getInstance().getReference("Yonetici");

            dbRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    for(DataSnapshot ds:dataSnapshot.getChildren()){
                        Yonetici yonetici = ds.getValue(Yonetici.class);
                        Log.d("y_Mail",yonetici.getY_Mail());
                        Log.d("y_Sifre",yonetici.getY_Sifre());

                        if(email.equalsIgnoreCase(yonetici.getY_Mail()) && password.equalsIgnoreCase(yonetici.getY_Sifre()))  //Mail ile şifre veritabanı ile eşleşirse
                        {
                            Toast.makeText(Mudur_Giris_Activity.this,"Giriş başarılı :)",Toast.LENGTH_LONG).show();
                            Intent yonetici_Giris = new Intent(Mudur_Giris_Activity.this, Main_Yonetici_Activity.class);
                            startActivity(yonetici_Giris);
                            finish();
                        }
                        else {
                            Toast.makeText(Mudur_Giris_Activity.this,"Email ya da Şifre Yanlış",Toast.LENGTH_LONG).show();
                            btnManagerLogin.setEnabled(true);
                        }
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    System.out.println("The read failed: " + databaseError.getMessage());
                }
            });

            btnManagerKaydol.setEnabled(false);//butona pes pese tıklanmasın diye pasif hale getirdim
/*
            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if(task.isSuccessful()){
                        //giris basarılı ise
                        Toast.makeText(Mudur_Giris_Activity.this,"Giriş başarılı :)",Toast.LENGTH_LONG).show();
                        Intent mainIntent = new Intent(Mudur_Giris_Activity.this, Main_Yonetici_Activity.class);
                        startActivity(mainIntent);
                        finish();
                    }
                    else{
                        //Toast.makeText(Mudur_Giris_Activity.this,"İşlem tamamlanamadı. Email ya da Şifre Yanlış",Toast.LENGTH_LONG).show();
                        btnManagerLogin.setEnabled(true);
                    }
                }
            });*/
        }
    }
}
