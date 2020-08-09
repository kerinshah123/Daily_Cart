package com.example.dailycart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.os.StrictMode;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONObject;

import java.io.InputStream;


public class MainActivity extends AppCompatActivity {
    TextInputLayout username,regemail,regusername,regnumber,PasswordLayout,etPasswordLayout;
    TextView textlogin,textsignup,newone,txtlogin,fpw;
    Button signup,login;
    EditText name,email,password,number,emaillogin,etpassword;
    LinearLayout l1,l2,open,llorder;
    CheckBox loginyes;
    String namepattern="[a-zA-Z]+";
    String emailpattern="[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    InputStream inputStream=null;
    public String result=null;
    public String resultreg=null;
    static InputStream is = null;
    static String json = "";
    static JSONObject jobj = null;
    public static boolean loggedIn = false;
    public static final String SHARED_PREF_NAME="hello,sign in";
    public static final String loginyes_no="userlogin";
    public static final String User_shared ="username";
    public static final String Email_shared="email";
    String loginskip="";
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View view1 = findViewById(R.id.view1);
        newone = findViewById(R.id.newone);
        textlogin = findViewById(R.id.textlogin);
        textsignup = findViewById(R.id.textsignup);
        l1 = findViewById(R.id.l1);
        l2 = findViewById(R.id.l2);
        signup = findViewById(R.id.signup);
        login = findViewById(R.id.login);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        // com.example.kerin.daily_cart.ipadd=findViewById(R.id.com.example.kerin.daily_cart.ipadd);
        number = findViewById(R.id.number);
        password = findViewById(R.id.Password);
        emaillogin = findViewById(R.id.emaillogin);
        etpassword = findViewById(R.id.etPassword);
        //dclogo=findViewById(R.id.dclogo);
        loginyes = findViewById(R.id.loginyes);
        username = findViewById(R.id.username);
        regemail = findViewById(R.id.regemail);
        open = findViewById(R.id.openll);
        llorder = findViewById(R.id.llorder);
        txtlogin = findViewById(R.id.txtlogin);
        fpw = findViewById(R.id.fpw);
        regusername = findViewById(R.id.regusername);
        regnumber = findViewById(R.id.regnumber);
        etPasswordLayout = findViewById(R.id.etPasswordLayout);
        PasswordLayout = findViewById(R.id.PasswordLayout);
        fpw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (emaillogin.getText().toString().equals("")) {
                    username.setError("Plz enter Number for forget password Activity");
                } else {
                    // fgpassword fg = new fgpassword();
                    //fg.execute();
                    finish();
                    //Intent intent=new Intent(MainActivity.this,otp.class);
                    //startActivity(intent);
                }
            }
        });
        if (getIntent().getExtras() != null) {
            loginskip = getIntent().getStringExtra("login");
            Toast.makeText(this, "" + loginskip, Toast.LENGTH_SHORT).show();
        }
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        newone.setText(getIntent().getStringExtra("name"));
        if (newone.getText().toString().equals("Log In")) {
            textlogin.setAlpha(1);
            textsignup.setAlpha((float) 0.7);
            l1.setVisibility(View.VISIBLE);
            Animation animation = new AlphaAnimation(1.0f, 0.5f);
            animation.setDuration(100);
            l2.startAnimation(animation);
            Animation animation1 = new AlphaAnimation(0.5f, 1.0f);
            animation1.setDuration(100);
            l1.startAnimation(animation1);
            l2.setVisibility(View.GONE);
        } else {
            textlogin.setAlpha((float) 0.7);
            textsignup.setAlpha(1);
            l2.setVisibility(View.VISIBLE);
            Animation animation = new AlphaAnimation(1.0f, 0.5f);
            animation.setDuration(100);
            l1.startAnimation(animation);
            Animation animation1 = new AlphaAnimation(0.5f, 1.0f);
            animation1.setDuration(100);
            l2.startAnimation(animation1);
            l1.setVisibility(View.GONE);
        }
        textsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textlogin.setAlpha((float) 0.7);
                textsignup.setAlpha(1);
                l2.setVisibility(View.VISIBLE);
                Animation animation = new AlphaAnimation(1.0f, 0.5f);
                animation.setDuration(100);
                l1.startAnimation(animation);
                Animation animation1 = new AlphaAnimation(0.5f, 1.0f);
                animation1.setDuration(100);
                l2.startAnimation(animation1);
                l1.setVisibility(View.GONE);
            }
        });
        textlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textlogin.setAlpha(1);
                textsignup.setAlpha((float) 0.7);
                l1.setVisibility(View.VISIBLE);
                Animation animation = new AlphaAnimation(1.0f, 0.5f);
                animation.setDuration(100);
                l2.startAnimation(animation);
                Animation animation1 = new AlphaAnimation(0.5f, 1.0f);
                animation1.setDuration(100);
                l1.startAnimation(animation1);
                l2.setVisibility(View.GONE);
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (password.getText().toString().trim().length() < 6) {
                    PasswordLayout.setError("Enter 6 character");
                }
                if (password.getText().toString().trim().length() > 10) {
                    PasswordLayout.setError("your password is too big");
                }
                if (name.getText().toString().equals("")) {
                    regusername.setError("Enter Name");
                    regusername.requestFocus();
                } else if (email.getText().toString().equals("")) {
                    regemail.setError("Enter Email");
                    regemail.requestFocus();
                } else if (number.getText().toString().equals("")) {
                    regnumber.setError("Enter Number");
                    regnumber.requestFocus();
                } else if (password.getText().toString().equals("")) {
                    PasswordLayout.setError("Enter Password");
                    PasswordLayout.requestFocus();
                } else if (!(number.length() == 10)) {
                    number.setError("enter Valid Number");
                    number.requestFocus();
                } else {
                    //registration r = new registration();
                    //r.execute();
                    Intent i = new Intent(getApplicationContext(),homeActivity.class);
                    startActivity(i);
                }
            }
        });
        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (name.getText().toString().matches(namepattern) && editable.length() > 0) {
                    regusername.setError(null);
                } else {
                    regusername.setError("Invalid Name");
                }
            }
        });
        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (email.getText().toString().matches(emailpattern) && editable.length() > 0) {
                    regemail.setError(null);
                } else {
                    regemail.setError("Invalid Email");
                }
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (emaillogin.getText().toString().equals("")) {
                    username.setError("Enter number or email");
                    username.requestFocus();
                }
                if (etpassword.getText().toString().equals("")) {
                    etPasswordLayout.setError("Enter you password");
                    etPasswordLayout.requestFocus();
                } else {
                    //loginc l = new loginc();
                    //l.execute();
             Intent i = new Intent(getApplicationContext(),homeActivity.class);
            startActivity(i);
                }
            }
        });
    }
}