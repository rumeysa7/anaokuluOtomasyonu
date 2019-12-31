package com.example.anaokuludeneme1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Kayit_Ol_Activity extends AppCompatActivity {

    private Toolbar actionbarRegister;
    private EditText txtUsername, txtEmail, txtPassword;
    private Button btnRegister, btnLoginRegister;

    private FirebaseAuth auth;
    private FirebaseUser currentUser;

    public void init(){

        //actionbarRegister = (Toolbar) findViewById(R.id.actionbarRegister);
        //setSupportActionBar(actionbarRegister);
        getSupportActionBar().setTitle("Hesap Oluştur");
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);//geri okunu aktif hale getirdik

        auth = FirebaseAuth.getInstance();

        txtUsername = (EditText) findViewById(R.id.txtUsernameRegister);
        txtEmail = (EditText) findViewById(R.id.txtEmailRegister);
        txtPassword = (EditText) findViewById(R.id.txtPasswordRegister);
        btnRegister = (Button) findViewById(R.id.btnRegister);
        //btnLoginRegister = (Button) findViewById(R.id.btnLoginRegister);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kayit__ol_);

        init();

            // --------------------------------------BU ACTİVİTY KULLANILMIYOR!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewAccount();
            }
        });

        /*btnLoginRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToLoginActivity();
            }
        });*/
    }


    private void createNewAccount() {

        final String username = txtUsername.getText().toString().trim();
        String email = txtEmail.getText().toString().trim();
        String password = txtPassword.getText().toString().trim();

        if(TextUtils.isEmpty(email)){ //hangi kontrolün boş olup olmadığını kontrol etmek istiyorsak isEmpty içine yazarız.

            //email alanı boş ise
            Toast.makeText(this, "Email alanı boş bırakılamaz!",Toast.LENGTH_LONG).show();
        }
        else if(TextUtils.isEmpty(password)){
            Toast.makeText(this, "Şifre alanı boş bırakılamaz!",Toast.LENGTH_LONG).show();
        }
        else{
            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                //email ve şifre ile kullanıcı oluşturma işlemi sistem tarafından(addOnCompleteListener) dinleniyor.
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    //tamamlanma durumundaki(onComplete) işlemleri burda yazıcaz.
                    DatabaseReference ddRef= FirebaseDatabase.getInstance().getReference().child("Ogretmen");
                    Ogretmen ogretmen= new Ogretmen();
                    ogretmen.setO_Mail(txtEmail.getText().toString());
                    ogretmen.setO_Sifre(txtPassword.getText().toString());
                    //oluşturduğumuz modeli database e push ediyoruz
                    ddRef.push().setValue(ogretmen);


                    if(task.isSuccessful()){ //email ve parola ile kullanıcı oluşturulduysa
                        Toast.makeText(Kayit_Ol_Activity.this,"Hesabınız başarılı bir şekilde oluşturuldu !",Toast.LENGTH_LONG).show();
                        Intent loginIntent= new Intent(Kayit_Ol_Activity.this, MainActivity.class);
                        startActivity(loginIntent);
                        finish(); //diyerek RegisterActivity'yi sonlandırıyorum. Kullanıcı loginActivity'ye gittikten sonra geri
                        //butonuna basıp RegisterActivity'ye geri dönemeyecek.
                    }else{
                        Toast.makeText(Kayit_Ol_Activity.this,"Bir hata oluştu!",Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }
}
