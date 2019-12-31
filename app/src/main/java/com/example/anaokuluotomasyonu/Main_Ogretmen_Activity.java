package com.example.anaokuluotomasyonu;

import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Main_Ogretmen_Activity extends AppCompatActivity {

    private Toolbar actionbar;
    private ViewPager vpMain;
    private TabLayout tabsMain;
    private Button btn_ogrenci_listele, btn_yemek_listesi, btn_etkinlik_duzenle;


    private FirebaseAuth auth;
    private FirebaseUser currentUser;

    private String event_Check = "Ogretmen";

    public void init(){

        //actionbar = (Toolbar) findViewById(R.id.actionBar);
        //setSupportActionBar(actionbar);
        getSupportActionBar().setTitle("Öğretmen Anasayfa");

        auth = FirebaseAuth.getInstance();
        currentUser = auth.getCurrentUser();//aktif kullanıcıyı currentUser değişkenine atadık

        vpMain = (ViewPager) findViewById(R.id.vpMain);

        btn_ogrenci_listele = (Button)findViewById(R.id.btnstudentlist);
        btn_yemek_listesi = (Button)findViewById(R.id.btnstudentfoodlist);
        btn_etkinlik_duzenle = (Button)findViewById(R.id.btnEventEdit);

        //tabsMain = (TabLayout) findViewById(R.id.tabsMain);
        //tabsMain.setupWithViewPager(vpMain); //yani tablarımızın viewpager ile birlikte yüklenmesini sağladık.*/
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__ogretmen_);

        Bundle extras = getIntent().getExtras();
        final String ogr_adi = extras.getString("ogretmen_Adi");

        //Toast.makeText(Main_Ogretmen_Activity.this,ogr_adi,Toast.LENGTH_LONG).show();
        init();

        btn_ogrenci_listele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ogrenci_listele = new Intent(Main_Ogretmen_Activity.this, Ogrenci_Liste_Activity.class);
                ogrenci_listele.putExtra("ogretmen_Adi", ogr_adi);
                startActivity(ogrenci_listele);
            }
        });

        btn_yemek_listesi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent yemek_listesi = new Intent(Main_Ogretmen_Activity.this, Yemek_Listesi_Duzenle_Activity.class);
                yemek_listesi.putExtra("ogretmen_Adi", ogr_adi);
                startActivity(yemek_listesi);
            }
        });

        btn_etkinlik_duzenle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent etkinlik_duzenle = new Intent(Main_Ogretmen_Activity.this, Etkinlik_Liste_Activity.class);
                startActivity(etkinlik_duzenle);
            }
        });
    }

    @Override
    protected void onStart() { //aktif kullanıcı yoksa yapılacaklar
        if(currentUser == null){
            Intent welcomeIntent = new Intent(Main_Ogretmen_Activity.this, Ogretmen_Giris_Activity.class);
            //startActivity(welcomeIntent);
            //finish();
        }
        super.onStart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        getMenuInflater().inflate(R.menu.ogretmen_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        if(item.getItemId() == R.id.menuOgrenciEkle){
            Intent intent = new Intent(Main_Ogretmen_Activity.this,Ogrenci_Ekle_Activity.class);
            startActivity(intent);
        }

        if(item.getItemId() == R.id.menuYemekEkle){
            Intent intent = new Intent(Main_Ogretmen_Activity.this, Yemek_Listesi_Duzenle_Activity.class);
            startActivity(intent);
        }

        if(item.getItemId() == R.id.menuEtkinlikEkle){
            Intent intent = new Intent(Main_Ogretmen_Activity.this,Etkinlik_Ekle_Activity.class);
            intent.putExtra("event_Check", event_Check);
            startActivity(intent);
        }

        if(item.getItemId() == R.id.mainLogout){
            auth.signOut(); //authentication oturumunu kapattım
            Intent loginIntent = new Intent(Main_Ogretmen_Activity.this,MainActivity.class);
            startActivity(loginIntent);
            finish();
        }
        return true;
    }


}
