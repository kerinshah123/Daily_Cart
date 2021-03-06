package com.example.dailycart;
/**
 *
 * @author Arshdeep  Kaur
 */
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {


    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window. FEATURE_NO_TITLE );
        getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN ,
                WindowManager.LayoutParams. FLAG_FULLSCREEN );
        setContentView(R.layout.activity_splashscreen);


        //running thread for 3 second and forward to StartActivity
        Thread myThread = new Thread()
        {

            @Override
            public void run() {
                try
                {

                    sleep ( 3000 );
                    Intent i = new Intent(getApplicationContext(),StartActivity.class );
                      startActivity(i);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        myThread.start();

    }
}