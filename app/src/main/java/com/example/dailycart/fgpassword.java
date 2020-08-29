package com.example.dailycart;
/**
 *
 * @author Keyur  Mistry
 */
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;

/**
 * fgpassword class for the forgot password
 */
public class fgpassword extends AppCompatActivity {

    Button updatepw;
    TextInputLayout new_password,con_password;
    EditText new_pass,con_pass;

    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fgpassword);

        updatepw=findViewById(R.id.updatepw);
        new_password=findViewById(R.id.new_password_field);
        con_password=findViewById(R.id.con_password_field);

        new_pass=findViewById(R.id.newpassword);
        con_pass=findViewById(R.id.con_password);

        updatepw.setOnClickListener(new View.OnClickListener() {
            /**
             *
             * @param v
             * onclick method to set clcik event for password sending
             */
            @Override
            public void onClick(View v) {
                if(con_pass.getText().toString().trim() != ""){
                    FirebaseAuth.getInstance().sendPasswordResetEmail(con_pass.getText().toString().trim())
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                /**
                                 *
                                 * @param task
                                 */
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(fgpassword.this, "Email Sent Successfully", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                                    }
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                /**
                                 *
                                 * @param e
                                 */
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(fgpassword.this, e.getMessage(), Toast.LENGTH_LONG).show();
                                }
                            });
                }

            }
        });

    }
}