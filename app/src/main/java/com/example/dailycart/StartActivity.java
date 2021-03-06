
package com.example.dailycart;
/**
 *
 * @author Arshdeep  Kaur
 */
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StartActivity extends AppCompatActivity {
    /**
     * @param
     */
    Button login , signin ;
    TextView skip ;
    SharedPreferences sharedPreferences;




    @Override
    protected void onStart() {
        super.onStart();

        //checking for exixting user

        sharedPreferences = getApplicationContext().getSharedPreferences("PREFERENCE", Context.MODE_PRIVATE);
        if(sharedPreferences.getString("user","user") != "user"){
            Intent i = new Intent(getApplicationContext(),homeActivity.class);
            startActivity(i);

        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        login =findViewById(R.id. login );
        signin =findViewById(R.id. signup );
      //  skip =findViewById(R.id. skip );

        //go to MainActivity for login

        login .setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getApplicationContext(),MainActivity. class );
                startActivity(i);
            }
        });

        //go to MainActivity for signin

        signin .setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getApplicationContext(),MainActivity. class );
                startActivity(i);
            }
        });

    }

}