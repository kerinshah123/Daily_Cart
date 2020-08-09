
package com.example.dailycart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StartActivity extends AppCompatActivity {
    Button login , signin ;
    TextView skip ;

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