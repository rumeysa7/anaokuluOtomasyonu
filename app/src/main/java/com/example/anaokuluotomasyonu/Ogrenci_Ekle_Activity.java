package com.example.anaokuluotomasyonu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.chip.ChipGroup;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Ogrenci_Ekle_Activity extends AppCompatActivity {

    // region Değişkenler
    private Toolbar actionbarlogin;
    private EditText txtStudentNo, txtStudentName, txtStudentSurname, txtStudentTc, txtStudentParent, txtStudentParent2, txtStudentHeight,
            txtStudentWeight, txtStudentBirthday, txtStudentClass, txtStudentTeacher, txtStudentMedicine,
            txtParentName, txtParentSurname, txtParentPhone, txtParentMail, txtParentPassword, txtParentAddress, txtParentChildName,
            txtParentChildSurname, txtParentChildNo, txtParentTc,
            txtParentName2, txtParentSurname2, txtParentPhone2, txtParentMail2, txtParentPassword2, txtParentAddress2, txtParentChildName2,
            txtParentChildSurname2, txtParentChildNo2, txtParentTc2;
    private Button btnOgrenciEkle;
    private CheckBox cb_ikinci_Veli;
    private TextView textParent2;
    private Boolean kontrol = false;

    FirebaseDatabase database;
    //endregion

    public void init() {

        //actionbarlogin = (Toolbar) findViewById(R.id.actionbarlogin);
        //setSupportActionBar(actionbarlogin);
        getSupportActionBar().setTitle("Öğrenci Ekle");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //geri oku gözükmesi için

        database = FirebaseDatabase.getInstance();

        textParent2 = (TextView) findViewById(R.id.textParent2);

        txtStudentNo = (EditText) findViewById(R.id.txtStudentNo);
        txtStudentName = (EditText) findViewById(R.id.txtStudentName);
        txtStudentSurname = (EditText) findViewById(R.id.txtStudentSurname);
        txtStudentTc = (EditText) findViewById(R.id.txtStudentTc);
        txtStudentParent = (EditText) findViewById(R.id.txtStudentParent1);
        txtStudentParent2 = (EditText) findViewById(R.id.txtStudentParent2);
        txtStudentHeight = (EditText) findViewById(R.id.txtStudentHeight);
        txtStudentWeight = (EditText) findViewById(R.id.txtStudentWeight);
        txtStudentBirthday = (EditText) findViewById(R.id.txtStudentBirthday);
        txtStudentClass = (EditText) findViewById(R.id.txtStudentClass);
        txtStudentTeacher = (EditText) findViewById(R.id.txtStudentTeacher);
        txtStudentMedicine = (EditText) findViewById(R.id.txtStudentMedicine);

        txtParentName = (EditText) findViewById(R.id.txtParentName);
        txtParentSurname = (EditText) findViewById(R.id.txtParentSurname);
        txtParentPhone = (EditText) findViewById(R.id.txtParentPhone);
        txtParentMail = (EditText) findViewById(R.id.txtParentMail);
        txtParentPassword = (EditText) findViewById(R.id.txtParentPassword);
        txtParentAddress = (EditText) findViewById(R.id.txtParentAddress);
        txtParentChildName = (EditText) findViewById(R.id.txtParentChildName);
        txtParentChildSurname = (EditText) findViewById(R.id.txtParentChildSurname);
        txtParentChildNo = (EditText) findViewById(R.id.txtParentChildNo);
        txtParentTc = (EditText) findViewById(R.id.txtParentTc);

        txtParentName2 = (EditText) findViewById(R.id.txtParentName2);
        txtParentSurname2 = (EditText) findViewById(R.id.txtParentSurname2);
        txtParentPhone2 = (EditText) findViewById(R.id.txtParentPhone2);
        txtParentMail2 = (EditText) findViewById(R.id.txtParentMail2);
        txtParentPassword2 = (EditText) findViewById(R.id.txtParentPassword2);
        txtParentAddress2 = (EditText) findViewById(R.id.txtParentAddress2);
        txtParentChildName2 = (EditText) findViewById(R.id.txtParentChildName2);
        txtParentChildSurname2 = (EditText) findViewById(R.id.txtParentChildSurname2);
        txtParentChildNo2 = (EditText) findViewById(R.id.txtParentChildNo2);
        txtParentTc2 = (EditText) findViewById(R.id.txtParentTc2);

        btnOgrenciEkle = (Button)findViewById(R.id.btnAddStudent);
        cb_ikinci_Veli = (CheckBox) findViewById(R.id.parent_2);
        //AlertDialog.Builder builder = new AlertDialog.Builder(Ogretmen_Ekle_Activity.this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ogrenci__ekle_);

        init();

        cb_ikinci_Veli.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {    //Cb duruma göre enable açıp kapatıyor

                if (cb_ikinci_Veli.isChecked()) {
                    txtParentName2.setEnabled(true);
                    txtParentSurname2.setEnabled(true);
                    txtParentPhone2.setEnabled(true);
                    txtParentMail2.setEnabled(true);
                    txtParentPassword2.setEnabled(true);
                    txtParentAddress2.setEnabled(true);
                    txtParentChildName2.setEnabled(true);
                    txtParentChildSurname2.setEnabled(true);
                    txtParentChildNo2.setEnabled(true);
                    txtParentTc2.setEnabled(true);
                    kontrol = true;
                }   //Enable true
                else{
                    txtParentName2.setEnabled(false);
                    txtParentSurname2.setEnabled(false);
                    txtParentPhone2.setEnabled(false);
                    txtParentMail2.setEnabled(false);
                    txtParentPassword2.setEnabled(false);
                    txtParentAddress2.setEnabled(false);
                    txtParentChildName2.setEnabled(false);
                    txtParentChildSurname2.setEnabled(false);
                    txtParentChildNo2.setEnabled(false);
                    txtParentTc2.setEnabled(false);
                    kontrol = false;
                }       //Enable false
            }
        });

        btnOgrenciEkle.setOnClickListener(new View.OnClickListener() { //Kayıt metodu çalışıyor
            @Override
            public void onClick(View v) {
                registerStudent();
            }
        });

    }

    private void registerStudent(){
        //---------------------------------Burası YAPILACAK---------------------------------------
//region Ogrenci bilgileri alma
        final String ogrenci_No = (txtStudentNo.getText().toString());
        final String ogrenci_Ad = txtStudentName.getText().toString();
        final String ogrenci_Soyad = txtStudentSurname.getText().toString();
        final String ogrenci_Tc = (txtStudentTc.getText().toString());
        final String ogrenci_Veli_1 = txtStudentParent.getText().toString();
        final String ogrenci_Veli_2 = txtStudentParent2.getText().toString();
        final String ogrenci_Boy = txtStudentHeight.getText().toString();
        final String ogrenci_Kilo = txtStudentWeight.getText().toString();
        final String ogrenci_Dogum = txtStudentBirthday.getText().toString();
        final String ogrenci_Sinif = txtStudentClass.getText().toString();
        final String ogrenci_Ogretmen_Ad = txtStudentTeacher.getText().toString();
        final String ogrenci_Ilac = txtStudentMedicine.getText().toString();
// endregion
// region Veli 1 bilgileri alma
        final String v_Ad_1 = txtParentName.getText().toString();
        final String v_Soyad_1 = txtParentSurname.getText().toString();
        final String v_Tel_1 = txtParentPhone.getText().toString();
        final String v_Mail_1 = txtParentMail.getText().toString();
        final String v_Sifre_1 = txtParentPassword.getText().toString();
        final String v_Adres_1 = txtParentAddress.getText().toString();
        final String ogrenci_Ad_1 = txtParentChildName.getText().toString();
        final String ogrenci_Soyad_1 = txtParentChildSurname.getText().toString();
        final String ogrenci_Numara_1 = (txtParentChildNo.getText().toString());
        final String v_Tc_No_1 = (txtParentTc.getText().toString());
//endregion
// region Veli 2 bilgileri alma
        final String v_Ad_2 = txtParentName2.getText().toString();
        final String v_Soyad_2 = txtParentSurname2.getText().toString();
        final String v_Tel_2 = txtParentPhone2.getText().toString();
        final String v_Mail_2 = txtParentMail2.getText().toString();
        final String v_Sifre_2 = txtParentPassword2.getText().toString();
        final String v_Adres_2 = txtParentAddress2.getText().toString();
        final String ogrenci_Ad_2 = txtParentChildName2.getText().toString();
        final String ogrenci_Soyad_2 = txtParentChildSurname2.getText().toString();
        final String ogrenci_Numara_2 = (txtParentChildNo2.getText().toString());
        final String v_Tc_No_2 = (txtParentTc2.getText().toString());
//endregion

        if(ogrenci_No.toString().trim().equals("") || TextUtils.isEmpty(ogrenci_Ad) || TextUtils.isEmpty(ogrenci_Soyad) || ogrenci_Tc.toString().trim().equals("")
                || TextUtils.isEmpty(ogrenci_Veli_1) || TextUtils.isEmpty(ogrenci_Veli_2) || TextUtils.isEmpty(ogrenci_Boy) || TextUtils.isEmpty(ogrenci_Kilo)
                || TextUtils.isEmpty(ogrenci_Dogum) || TextUtils.isEmpty(ogrenci_Sinif) || TextUtils.isEmpty(ogrenci_Ogretmen_Ad) || TextUtils.isEmpty(ogrenci_Ilac)){
            Toast.makeText(this,"Öğrenci bilgilerindeki boş alanları doldurunuz!",Toast.LENGTH_LONG).show();
        }else if(TextUtils.isEmpty(v_Ad_1) || TextUtils.isEmpty(v_Soyad_1) || TextUtils.isEmpty(v_Tel_1) || TextUtils.isEmpty(v_Mail_1)
                || TextUtils.isEmpty(v_Sifre_1) || TextUtils.isEmpty(v_Adres_1) || TextUtils.isEmpty(ogrenci_Ad_1) || TextUtils.isEmpty(ogrenci_Soyad_1)
                || TextUtils.isEmpty(ogrenci_Numara_1.toString()) || TextUtils.isEmpty(v_Tc_No_1.toString())){
            Toast.makeText(this,"Veli 1 bilgilerindeki boş alanları doldurunuz!",Toast.LENGTH_LONG).show();
        }else{
            btnOgrenciEkle.setEnabled(false);//butona pes pese tıklanmasın diye pasif hale getirdim

            // region veritabanı değişkenleri tanım ve yeni ogrenci veli nesneleri
            DatabaseReference ddRef_Ogrenci = database.getReference().child("Ogrenci");
            DatabaseReference ddRef_Veli = database.getReference().child("Veli");
            DatabaseReference ddRef_Veli_2 = database.getReference().child("Veli");

            Ogrenci ogrenci = new Ogrenci();
            Veli veli_1 = new Veli();
            Veli veli_2 = new Veli();
            //endregion

// region  --   Ogrenci bilgileri sınıf değişkenlerine set edilir
            ogrenci.setOgrenci_No(Integer.parseInt(ogrenci_No));
            ogrenci.setOgrenci_Ad(ogrenci_Ad);
            ogrenci.setOgrenci_Soyad(ogrenci_Soyad);
            ogrenci.setOgrenci_Tc_No(Long.parseLong(ogrenci_Tc));
            ogrenci.setOgrenci_Veli1(ogrenci_Veli_1);
            ogrenci.setOgrenci_Veli2(ogrenci_Veli_2);
            ogrenci.setOgrenci_Boy(ogrenci_Boy);
            ogrenci.setOgrenci_Kilo(ogrenci_Kilo);
            ogrenci.setOgrenci_Dogum_Tarih(ogrenci_Dogum);
            ogrenci.setOgrenci_Sinif(ogrenci_Sinif);
            ogrenci.setO_Ad(ogrenci_Ogretmen_Ad);
            ogrenci.setOgrenci_Ilac(ogrenci_Ilac);
            //endregion

// region  --   Veli 1 bilgileri sınıf değişkenlerine set edilir
            veli_1.setV_Ad(v_Ad_1);
            veli_1.setv_Soyad(v_Soyad_1);
            veli_1.setv_Tel(v_Tel_1);
            veli_1.setv_Mail(v_Mail_1);
            veli_1.setv_Sifre(v_Sifre_1);
            veli_1.setV_Adres(v_Adres_1);
            veli_1.setOgrenci_Ad(ogrenci_Ad_1);
            veli_1.setOgrenci_Soyad(ogrenci_Soyad_1);
            veli_1.setOgrenci_Numara(Integer.parseInt(ogrenci_Numara_1));
            veli_1.setv_Tc_No(Long.parseLong(v_Tc_No_1));
            //endregion

            //oluşturduğumuz modeli database e push ediyoruz

            if(kontrol) //Veli 2 cb true ise veli 2 bilgi çekip database atıyor
            {
                if(TextUtils.isEmpty(v_Ad_2) || TextUtils.isEmpty(v_Soyad_2) || TextUtils.isEmpty(v_Tel_2) || TextUtils.isEmpty(v_Mail_2)
                        || TextUtils.isEmpty(v_Sifre_2) || TextUtils.isEmpty(v_Adres_2) || TextUtils.isEmpty(ogrenci_Ad_2) || TextUtils.isEmpty(ogrenci_Soyad_2)
                        || TextUtils.isEmpty(ogrenci_Numara_2.toString()) || TextUtils.isEmpty(v_Tc_No_2.toString())){
                    Toast.makeText(this,"Veli 2 bilgilerindeki boş alanları doldurunuz!",Toast.LENGTH_LONG).show();
                }
                else {              //cb true ve veriler doluysa
                    // region  --   Veli 2 bilgileri sınıf değişkenlerine set edilir
                    veli_2.setV_Ad(v_Ad_2);
                    veli_2.setv_Soyad(v_Soyad_2);
                    veli_2.setv_Tel(v_Tel_2);
                    veli_2.setv_Mail(v_Mail_2);
                    veli_2.setv_Sifre(v_Sifre_2);
                    veli_2.setV_Adres(v_Adres_2);
                    veli_2.setOgrenci_Ad(ogrenci_Ad_2);
                    veli_2.setOgrenci_Soyad(ogrenci_Soyad_2);
                    veli_2.setOgrenci_Numara(Integer.parseInt(ogrenci_Numara_2));
                    veli_2.setv_Tc_No(Long.parseLong(v_Tc_No_2));
                    //endregion

                    ddRef_Ogrenci.push().setValue(ogrenci);
                    ddRef_Veli.push().setValue(veli_1);
                    ddRef_Veli_2.push().setValue(veli_2);

                    Toast.makeText(Ogrenci_Ekle_Activity.this,"Öğrenci ve veliler başarıyla eklendi :)",Toast.LENGTH_LONG).show();
// region ----------yeni ogrenci ekleme ekranı
                    AlertDialog.Builder builder = new AlertDialog.Builder(Ogrenci_Ekle_Activity.this);
                    builder.setCancelable(false);
                    builder.setTitle("Yeni Öğrenci");
                    builder.setMessage("Yeni öğrenci ve veli eklemek ister misiniz ?");
                    builder.setPositiveButton("Evet", null);
                    builder.setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            Intent main_yonetici = new Intent(Ogrenci_Ekle_Activity.this, Main_Ogretmen_Activity.class);
                            startActivity(main_yonetici);
                            finish();
                        }
                    });
                    builder.show();
                    // endregion

                }
            }
            else {  // cb false ve öğrenci veli 1 ekleme
                ddRef_Ogrenci.push().setValue(ogrenci);
                ddRef_Veli.push().setValue(veli_1);

                Toast.makeText(Ogrenci_Ekle_Activity.this,"Öğrenci ve velibaşarıyla eklendi :)",Toast.LENGTH_LONG).show();
// region ----------yeni ogrenci ekleme ekranı
                AlertDialog.Builder builder = new AlertDialog.Builder(Ogrenci_Ekle_Activity.this);
                builder.setCancelable(false);
                builder.setTitle("Yeni Öğrenci");
                builder.setMessage("Yeni öğrenci ve veli eklemek ister misiniz ?");
                builder.setPositiveButton("Evet", null);
                builder.setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Intent main_yonetici = new Intent(Ogrenci_Ekle_Activity.this, Main_Ogretmen_Activity.class);
                        startActivity(main_yonetici);
                        finish();
                    }
                });
                builder.show();
                // endregion

            }
            //push() metodu anahtar eklemek için kullanılır ve bu anahtarlar benzersiz (unique) idlere sahiptirler

            btnOgrenciEkle.setEnabled(true);
        }
    }
}
