package com.example.shopdreamteam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
public class dangky extends AppCompatActivity {
    TextInputEditText inputfullname, inputemail, inputusername, inputPassword, inputconfimpassword;
    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangky);
        inputfullname = findViewById(R.id.fullname);
        inputemail = findViewById(R.id.email);
        inputusername = findViewById(R.id.username);
        inputPassword = findViewById(R.id.Password);
//        inputconfimpassword = findViewById(R.id.confimpassword);
        register = findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullname, email, username, password;
                fullname = String.valueOf(inputfullname.getText());
                email = String.valueOf(inputemail.getText());
                username = String.valueOf(inputusername.getText());
                password = String.valueOf(inputPassword.getText());
                if (!fullname.equals("") && !username.equals("") && !password.equals("") && !email.equals("")) {


                    //Start ProgressBar first (Set visibility VISIBLE)
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            //Starting Write and Read data with URL
                            //Creating array for parameters
                            String[] field = new String[4];
                            field[0] = "fullname";
                            field[1] = "username";
                            field[2] = "email";
                            field[3] = "password";
                            //Creating array for data
                            String[] data = new String[4];
                            data[0] = fullname;
                            data[1] = username;
                            data[2] = email;
                            data[3] = password;
                            PutData putData = new PutData("http://192.168.4.103/login-android/signup.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    String result = putData.getResult();
                                    if (result.equals("Sign Up Success")) {
                                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(), dangnhap.class);
                                        startActivity(intent);
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


        TextView coTK = findViewById(R.id.cotkroi);
        coTK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentlink = new Intent();
                intentlink.setClass(dangky.this, dangnhap.class);
                startActivity(intentlink);
            }
        });


    }

}