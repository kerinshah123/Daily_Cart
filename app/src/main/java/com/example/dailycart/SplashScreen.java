package com.example.dailycart;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window. FEATURE_NO_TITLE );
        getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN ,
                WindowManager.LayoutParams. FLAG_FULLSCREEN );
        setContentView(R.layout.activity_splashscreen);

        Thread myThread = new Thread()
        {
            @Override
            public void run() {
                try
                {
                    sleep ( 3000 );
                    //Intent i = new Intent(getApplicationContext(),new_login. class );
                   // startActivity(i);
                   // finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        myThread.start();

    }
}