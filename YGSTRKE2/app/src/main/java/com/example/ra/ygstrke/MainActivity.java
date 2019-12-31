package com.example.ra.ygstrke;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button b = (Button)findViewById(R.id.button3);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("https://sinavtime.com/2018-ygs-turkce-testleri-coz/"));
                startActivity(i);
            }
        });
    }



    public void gonder(View view) {

        Intent ıntent = new Intent(getApplicationContext(),Main2Activity.class);
        startActivity(ıntent);
    }

    public void testler(View view) {

        Intent ıntent = new Intent(getApplicationContext(),Main3Activity.class);
        startActivity(ıntent);
    }
}


























