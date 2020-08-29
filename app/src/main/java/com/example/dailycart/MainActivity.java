package com.example.dailycart;
/**
 *
 * @author Amandeep  Kaur
 */
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

/**
 * mainactvity class for lofin and signup of the application
 */
public class MainActivity extends AppCompatActivity {
    TextInputLayout username,
    regemail,
    regusername,
    regnumber,
    PasswordLayout,
    etPasswordLayout;
    TextView textlogin,
    textsignup,
    newone,
    txtlogin,
    fpw;
    Button signup,
    login;
    EditText name,
    email,
    password,
    number,
    emaillogin,
    etpassword;
    LinearLayout l1,
    l2,
    open,
    llorder;
    CheckBox loginyes;
    //name pattern
    String namepattern="[a-zA-Z]+";
    //email pattern
    String emailpattern="[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    String loginskip="";
    //Firebase authentication instance
    private FirebaseAuth mAuth;

    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(final Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        newone = findViewById(R.id.newone);
        textlogin = findViewById(R.id.textlogin);
        textsignup = findViewById(R.id.textsignup);
        l1 = findViewById(R.id.l1);
        l2 = findViewById(R.id.l2);
        signup = findViewById(R.id.signup);
        login = findViewById(R.id.login);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        number = findViewById(R.id.number);
        password = findViewById(R.id.Password);
        emaillogin = findViewById(R.id.emaillogin);
        etpassword = findViewById(R.id.etPassword);
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


        mAuth = FirebaseAuth.getInstance();

        //Firestore instance
        final FirebaseFirestore db = FirebaseFirestore.getInstance();

        fpw.setOnClickListener(new View.OnClickListener() {
            /**
             *
             * @param view
             */
            @Override
            public void onClick(View view) {


                    Intent intent=new Intent(MainActivity.this,fgpassword.class);
                    startActivity(intent);

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
            /**
             *
             * @param view
             */
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
            /**
             *
             * @param view
             */
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
            /**
             *
             * @param view
             */
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

                    mAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                            .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                                /**
                                 *
                                 * @param task
                                 */
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information

                                        FirebaseUser user = mAuth.getCurrentUser();
                                        // Create a new user with a first and last name
                                        Map<String, Object> user1 = new HashMap<>();
                                        user1.put("name", name.getText().toString());
                                        user1.put("email", email.getText().toString());
                                        user1.put("password", password.getText().toString());
                                        user1.put("number", number.getText().toString());

                                        // Add a new document with a generated ID
                                        db.collection("users")
                                                .add(user1)
                                                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                                    @Override
                                                    public void onSuccess(DocumentReference documentReference) {

                                                        Toast.makeText(MainActivity.this, "Successfully Registered", Toast.LENGTH_SHORT).show();
                                                        Intent i = new Intent(getApplicationContext(),homeActivity.class);
                                                        startActivity(i);
                                                    }
                                                })
                                                .addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                                    }
                                                });

                                    } else {
                                        // If sign in fails, display a message to the user.

                                        Toast.makeText(MainActivity.this, "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();

                                    }

                                    // ...
                                }
                            });


                }
            }
        });
        name.addTextChangedListener(new TextWatcher() {
            /**
             *
             * @param charSequence
             * @param i
             * @param i1
             * @param i2
             */

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            /**
             *
             * @param charSequence
             * @param i
             * @param i1
             * @param i2
             */
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            /**
             *
             * @param editable
             */
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
            /**
             *
             * @param charSequence
             * @param i
             * @param i1
             * @param i2
             */
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            /**
             *
             * @param charSequence
             * @param i
             * @param i1
             * @param i2
             */
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            /**
             *
             * @param editable
             */
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

                    mAuth.signInWithEmailAndPassword(emaillogin.getText().toString(),etpassword.getText().toString())
                            .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                                /**
                                 *
                                 * @param task
                                 */
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information

                                        FirebaseUser user = mAuth.getCurrentUser();
                                        Intent i = new Intent(getApplicationContext(),homeActivity.class);
                                        startActivity(i);

                                        if(loginyes.isChecked()){
                                            SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("PREFERENCE", Context.MODE_PRIVATE);
                                            SharedPreferences.Editor editor = sharedPreferences.edit();
                                            editor.putString("user", String.valueOf(user));
                                            editor.commit();
                                        }

                                    } else {
                                        // If sign in fails, display a message to the user.

                                        Toast.makeText(MainActivity.this, "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();

                                    }


                                }
                            });


                }
            }
        });
    }
}