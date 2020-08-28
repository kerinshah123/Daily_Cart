package com.example.dailycart;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class product_details extends AppCompatActivity {
    CollapsingToolbarLayout coll;
    ImageView productimage,imageplus,imageminus;
    TextView productname,productprice,productdescription,quantity;
    FloatingActionButton cart;
    Button offer_apply;
    EditText offer;
    int number = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        productname=findViewById(R.id.txtfoodname);
        productdescription=findViewById(R.id.txtdescription);
        productprice=findViewById(R.id.txtfoodprice);
        productimage=findViewById(R.id.product_det_image);
        coll=findViewById(R.id.collapsinglayout);
        cart=findViewById(R.id.cart);
        offer_apply=findViewById(R.id.offer_apply);
        offer=findViewById(R.id.offer);

        imageplus=findViewById(R.id.imageplus);
        imageminus=findViewById(R.id.imageminus);
        quantity=findViewById(R.id.quantity);

        coll.setExpandedTitleTextAppearance(R.style.coll2);
        coll.setCollapsedTitleTextAppearance(R.style.coll);

        imageplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                number++;
                quantity.setText(String.valueOf(number));
            }
        });

        imageminus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(number>=1){
                    number--;
                    quantity.setText(String.valueOf(number));
                }

            }
        });
    }
}