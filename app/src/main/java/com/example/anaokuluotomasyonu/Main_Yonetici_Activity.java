package com.example.anaokuluotomasyonu;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

public class Main_Yonetici_Activity extends AppCompatActivity {

    private Toolbar actionbar;
    private ViewPager vpMain;
    private Button btnOgretmenListele, btnEtkinlikListele, btnYemekListele;

    private FirebaseAuth auth;
    private FirebaseUser currentUser;

    private String event_Check = "Mudur";

    public void init(){

        //actionbar = (Toolbar) findViewById(R.id.actionBar);
        //setSupportActionBar(actionbar);
        getSupportActionBar().setTitle("Müdür Ana Sayfa");

        btnOgretmenListele = (Button)findViewById(R.id.btnOgretmenListele);
        btnEtkinlikListele = (Button)findViewById(R.id.btnEtkinlikListele);
        btnYemekListele = (Button)findViewById(R.id.btnYemekListele);

        auth = FirebaseAuth.getInstance();
        currentUser = auth.getCurrentUser();//aktif kullanıcıyı currentUser değişkenine atadık

        vpMain = (ViewPager) findViewById(R.id.vpMain);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__yonetici_);

        init();
        btnOgretmenListele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ogretmen_Listele = new Intent(Main_Yonetici_Activity.this, Ogretmen_Listele_Activity.class);
                startActivity(ogretmen_Listele);
            }
        });

        btnEtkinlikListele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent etkinlik_Listele = new Intent(Main_Yonetici_Activity.this, Etkinlik_Liste_Activity.class);
                startActivity(etkinlik_Listele);
            }
        });

        btnYemekListele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent yemek_Listele = new Intent(Main_Yonetici_Activity.this, Yemek_Listesi_Goruntule_Activity.class);
                startActivity(yemek_Listele);
            }
        });
    }

    @Override
    protected void onStart() { //aktif kullanıcı yoksa yapılacaklar
        if(currentUser == null){
            Intent welcomeIntent = new Intent(Main_Yonetici_Activity.this, Mudur_Giris_Activity.class);
            //startActivity(welcomeIntent);
            //finish();
        }
        super.onStart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        getMenuInflater().inflate(R.menu.yonetici_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        if(item.getItemId() == R.id.menuOgretmenEkle){
            Intent intent = new Intent(Main_Yonetici_Activity.this,Ogretmen_Ekle_Activity.class);
            startActivity(intent);
        }

        if(item.getItemId() == R.id.menuMudurEtkinlikEkle){
            Intent intent = new Intent(Main_Yonetici_Activity.this,Etkinlik_Ekle_Activity.class);
            intent.putExtra("event_Check",event_Check);
            startActivity(intent);
        }

        if(item.getItemId() == R.id.mainMudurLogout){
            auth.signOut(); //authentication oturumunu kapattım
            Intent intent = new Intent(Main_Yonetici_Activity.this,Mudur_Giris_Activity.class);
            startActivity(intent);
            finish();
        }
        return true;
    }

}
