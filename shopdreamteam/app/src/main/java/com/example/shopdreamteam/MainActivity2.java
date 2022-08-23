package com.example.shopdreamteam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Button btndn = findViewById(R.id.btndn);
        btndn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent DN = new Intent(MainActivity2.this,dangnhap.class);
                startActivity(DN);
            }
        });
        Button btndky = findViewById(R.id.btndky);
        btndky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dky = new Intent(MainActivity2.this,dangky.class);
                startActivity(dky);
            }
        });
    }
}