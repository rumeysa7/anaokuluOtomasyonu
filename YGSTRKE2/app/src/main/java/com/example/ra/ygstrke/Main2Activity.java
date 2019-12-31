package com.example.ra.ygstrke;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Main2Activity extends AppCompatActivity {

    ListView liste;
    static final String[] konular = new String[]{
            "1.) Sözcük Anlamı",
            "2.) Söz Yorumu",
            "3.) Deyim ve Atasözü",
            "4.) Cümle Anlamı",
            "5.) Cümle Yorumu",
            "6.) Parağrafta Anlatım Teknikleri", "" +
            "7.) Parağrafta Konu-Ana Düşünce",
            "8.) Parağrafta Yapı",
            "9.) Parağrafta Yardımcı Düşünce",
            "10.) Ses Bilgisi",
            "11.) Yazım Kuralları",
            "12.) Noktalama İşaretleri",
            "13.) Sözcüğün Yapısı",
            "14.) Sözcük Türleri",
            "15.) Fiiller",
            "16.) Sözcük Grupları",
            "17.) Cümlenin Öğeleri",
            "18.) Cümle Türleri",
            "19.) Anlatım Bozukluğu"};


    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        liste = (ListView) findViewById(R.id.lvKonular);
        // ArrayAdapter<String> adapter = new ArrayAdapter<String>(
        // context,android.R.layout.simple_list_item_1,
        // android.R.id.text1,konular);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, R.layout.list_konu, konular);
        liste.setAdapter(adapter);

        liste.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (position == 0) {
                    Intent sözcükAnlamı = new Intent(view.getContext(), SozcukAnlami.class);
                    startActivityForResult(sözcükAnlamı, 0);
                }
                if (position == 1) {
                    Intent sözYorumu = new Intent(view.getContext(), SozYorumu.class);
                    startActivityForResult(sözYorumu, 1);
                }
                if (position == 2) {
                    Intent deyim = new Intent(view.getContext(), DeyimAtasozu.class);
                    startActivityForResult(deyim, 2);
                }
                if (position == 3) {
                    Intent cümleAnlamı = new Intent(view.getContext(), CumleAnlami.class);
                    startActivityForResult(cümleAnlamı, 3);
                }
                if (position == 4) {
                    Intent cümleYorumu = new Intent(view.getContext(), CumleYorumu.class);
                    startActivityForResult(cümleYorumu, 4);
                }
                if (position == 5) {
                    Intent anlatimTeknikleri = new Intent(view.getContext(), AnlatimTeknikleri.class);
                    startActivityForResult(anlatimTeknikleri, 5);
                }
                if (position == 6) {
                    Intent anaDüsünce = new Intent(view.getContext(), AnaDusunce.class);
                    startActivityForResult(anaDüsünce, 6);
                }
                if (position == 7) {
                    Intent yapi = new Intent(view.getContext(), Yapi.class);
                    startActivityForResult(yapi, 7);
                }
                if (position == 8) {
                    Intent yardımcıDüsünce = new Intent(view.getContext(), YardimciDusunce.class);
                    startActivityForResult(yardımcıDüsünce, 8);
                }
                if (position == 9) {
                    Intent sesBilgisi = new Intent(view.getContext(), SesBilgisi.class);
                    startActivityForResult(sesBilgisi, 9);
                }
                if (position == 10) {
                    Intent yazimKuralları = new Intent(view.getContext(), YazimKurallari.class);
                    startActivityForResult(yazimKuralları, 10);
                }
                if (position == 11) {
                    Intent noktalamaIsaretleri = new Intent(view.getContext(), NoktalamaIsaretleri.class);
                    startActivityForResult(noktalamaIsaretleri, 11);
                }
                if (position == 12) {
                    Intent sözcügünYapısı = new Intent(view.getContext(), SozcugunYapisi.class);
                    startActivityForResult(sözcügünYapısı, 12);
                }
                if (position == 13) {
                    Intent sözcükTürleri = new Intent(view.getContext(), SozcukTurleri.class);
                    startActivityForResult(sözcükTürleri, 13);
                }
                if (position == 14) {
                    Intent fiiller = new Intent(view.getContext(), Fiiller.class);
                    startActivityForResult(fiiller, 14);
                }
                if (position == 15) {
                    Intent sözcükGrupları = new Intent(view.getContext(), SozcukGruplari.class);
                    startActivityForResult(sözcükGrupları, 15);
                }
                if (position == 16) {
                    Intent cümleninOgeleri = new Intent(view.getContext(), CumleninOgeleri.class);
                    startActivityForResult(cümleninOgeleri, 16);
                }
                if (position == 17) {
                    Intent cümleTürleri = new Intent(view.getContext(), CumleTurleri.class);
                    startActivityForResult(cümleTürleri, 17);
                }
                if (position == 18) {
                    Intent anlatımBozuklugu = new Intent(view.getContext(), AnlatimBozuklugu.class);
                    startActivityForResult(anlatımBozuklugu, 18);
                }
            }
        });
    }
}
