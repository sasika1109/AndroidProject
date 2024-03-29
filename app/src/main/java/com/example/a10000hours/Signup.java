package com.example.a10000hours;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import Database.DBHelper;

public class Signup extends AppCompatActivity {
    EditText txt_userName, txt_password,txt_email;
    DBHelper db;
    String userName,password,email;
    private Button addUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singup);

        Toolbar toolbar = findViewById(R.id.historyToolbar);
        setSupportActionBar(toolbar);
        setTitle("Sign up");

        txt_userName = findViewById(R.id.txtUserName);
        txt_email = findViewById(R.id.txtEmail);
        txt_password = findViewById(R.id.txtPassword);

        addUser = findViewById(R.id.btnAdd);

        db = new DBHelper(this);

        addUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               userName = txt_userName.getText().toString();
               password = txt_password.getText().toString();
               email = txt_email.getText().toString();

               if ((userName.equals(""))||(email.equals(""))||(password.equals("")))
               {
//                  AlertDialog alertDialog = new AlertDialog.Builder(this).create();
                   Toast.makeText(getApplicationContext(),"Filed must Filled",Toast.LENGTH_LONG).show();

               }
               else {

                   boolean result = db.addUser(userName, password, email);

                   if (result) {
                       Toast.makeText(getApplicationContext(), "Registered success", Toast.LENGTH_LONG).show();
                       userProfile();
                   } else {
                       Toast.makeText(getApplicationContext(), "Error in Registering", Toast.LENGTH_LONG).show();
                   }
               }

            }
        });



    }
    public void  userProfile(){
        Intent intent = new Intent(this,SignIn.class);
        startActivity(intent);
    }
}