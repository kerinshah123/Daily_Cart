package com.example.dailycart;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;

public class fgpassword extends AppCompatActivity {

    Button updatepw;
    TextInputLayout new_password,con_password;
    EditText new_pass,con_pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fgpassword);

        updatepw=findViewById(R.id.updatepw);
        new_password=findViewById(R.id.new_password_field);
        con_password=findViewById(R.id.con_password_field);

        new_pass=findViewById(R.id.newpassword);
        con_pass=findViewById(R.id.con_password);

    }
}