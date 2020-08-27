
package com.example.dailycart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StartActivity extends AppCompatActivity {
    Button login , signin ;
    TextView skip ;
    SharedPreferences sharedPreferences;

    @Override
    protected void onStart() {
        super.onStart();
        sharedPreferences = getApplicationContext().getSharedPreferences("PREFERENCE", Context.MODE_PRIVATE);
        if(sharedPreferences.getString("user","") != ""){
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
        skip =findViewById(R.id. skip );

        login .setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getApplicationContext(),MainActivity. class );
                startActivity(i);
            }
        });
        signin .setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getApplicationContext(),MainActivity. class );
                startActivity(i);
            }
        });
        skip .setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(),homeActivity. class );
                startActivity(intent);
            }
        });
    }

}