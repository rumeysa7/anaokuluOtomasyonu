package com.example.anaokuludeneme1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Main_Yonetici_Activity extends AppCompatActivity {

    private Toolbar actionbar;
    private ViewPager vpMain;
    private TabLayout tabsMain;


    private FirebaseAuth auth;
    private FirebaseUser currentUser;

    private String event_Check = "Mudur";

    public void init(){

        //actionbar = (Toolbar) findViewById(R.id.actionBar);
        //setSupportActionBar(actionbar);
        getSupportActionBar().setTitle("Müdür Ana Sayfa");

        auth = FirebaseAuth.getInstance();
        currentUser = auth.getCurrentUser();//aktif kullanıcıyı currentUser değişkenine atadık

        vpMain = (ViewPager) findViewById(R.id.vpMain);


        tabsMain = (TabLayout) findViewById(R.id.tabsMain);
        tabsMain.setupWithViewPager(vpMain); //yani tablarımızın viewpager ile birlikte yüklenmesini sağladık.*/
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__yonetici_);

        init();
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
