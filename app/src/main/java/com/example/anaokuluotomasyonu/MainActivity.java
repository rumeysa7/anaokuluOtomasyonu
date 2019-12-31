package com.example.anaokuluotomasyonu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    Button mudur, ogretmen, veli, cikis;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mudur = (Button) findViewById(R.id.mudur);
        ogretmen = (Button) findViewById(R.id.ogretmen);
        veli = (Button) findViewById(R.id.veli);
        //cikis = (Button) findViewById(R.id.cikis);

        mudur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mudur_Giris = new Intent(MainActivity.this,Mudur_Giris_Activity.class);
                startActivity(mudur_Giris);
            }
        });

        ogretmen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ogretmen_Giris = new Intent(MainActivity.this, Ogretmen_Giris_Activity.class);
                startActivity(ogretmen_Giris);
            }
        });

        veli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent veli_Giris = new Intent(MainActivity.this, Veli_Giris_Activity.class);
                startActivity(veli_Giris);
            }
        });

       /* cikis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);
            }
        });
*/
        /*button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference ddRef = FirebaseDatabase.getInstance().getReference().child("Kullanıcılar");
                DatabaseReference ddRef2 = FirebaseDatabase.getInstance().getReference().child("Detay");

                final Yonetici yonetici = new Yonetici();
                //model classı oluşturup bi model nesne oluşturuyoruz her ayrı kullanıcı için
                yonetici.setY_Ad(text.getText().toString());

                ddRef.push().setValue(yonetici);
                ddRef2.push().setValue(yonetici);

                ddRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            Yonetici yonetici1 = ds.getValue(Yonetici.class);
                            Log.d("y_Ad", yonetici.getY_Ad());
                            text2.setText(yonetici1.getY_Ad());

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        System.out.println("The read failed: " + databaseError.getMessage());
                    }
                });

            }
        });*/

    }
}
