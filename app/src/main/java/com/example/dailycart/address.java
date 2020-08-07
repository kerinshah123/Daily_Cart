package com.example.dailycart;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;

public class address extends AppCompatActivity {
    Button add,addaddress;
    RelativeLayout add_address;
    EditText address1,address2,address3,pincodeedit;
    Spinner city,state,area;
    RadioGroup grp;
    RadioButton address_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);

        add = findViewById(R.id.add);
        add_address = findViewById(R.id.add_address);
        address1 = findViewById(R.id.address1);
        address2 = findViewById(R.id.address2);
        address3 = findViewById(R.id.address3);
        pincodeedit = findViewById(R.id.pincodeedit);
        grp=findViewById(R.id.addresstyperg);
        addaddress=findViewById(R.id.addaddress);
        state=findViewById(R.id.spinnerstate);
        area=findViewById(R.id.spinnerarea);
        city=findViewById(R.id.spinnercity);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add_address.setVisibility(View.VISIBLE);
            }
        });

    }
}