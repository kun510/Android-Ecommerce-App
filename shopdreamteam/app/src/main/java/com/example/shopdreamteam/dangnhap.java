package com.example.shopdreamteam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class dangnhap extends AppCompatActivity {
    TextInputEditText inputusername,inputPassword;
    Button Login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangnhap);
        inputusername = findViewById(R.id.username);
        inputPassword = findViewById(R.id.Password);
//        inputconfimpassword = findViewById(R.id.confimpassword);
        Login = findViewById(R.id.Login);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String  username, password;
                username = String.valueOf(inputusername.getText());
                password = String.valueOf(inputPassword.getText());
                if (!username.equals("") && !password.equals("")) {
                    //Start ProgressBar first (Set visibility VISIBLE)
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            //Starting Write and Read data with URL
                            //Creating array for parameters
                            String[] field = new String[2];
                            field[0] = "username";
                            field[1] = "password";
                            //Creating array for data
                            String[] data = new String[2];
                            data[0] = username;
                            data[1] = password;
                            PutData putData = new PutData("http://192.168.4.103/login-android/login.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    String result = putData.getResult();
                                    if (result.equals("Login Success")) {
                                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                        Intent chuyen = new Intent(getApplicationContext(),trangchu.class);
                                        startActivity(chuyen);
                                        finish();
                                    } else {
                                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                    }
                                    //End ProgressBar (Set visibility to GONE)

                                }
                            }
                            //End Write and Read data with URL
                        }
                    });
                } else {
                    Toast.makeText(getApplicationContext(), "All fiels requied", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //link chuyen dky
        TextView linkDky = findViewById(R.id.linkDky);
        linkDky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent linktodky = new Intent();
                linktodky.setClass(dangnhap.this,dangky.class);
                startActivity(linktodky);
            }
        });

    }
}