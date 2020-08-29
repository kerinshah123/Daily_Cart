package com.example.dailycart.ui.order;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dailycart.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.squareup.picasso.Picasso;

public class OrderDetailActivity extends AppCompatActivity {

    TextView orderid,order_detail_productname,order_detail_productrate,order_detail_quantity,txtpayment_status,txtpayment_date,payment_amt;
    ImageView cart_image1;
    FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        orderid = findViewById(R.id.orderid);
        order_detail_productname = findViewById(R.id.order_detail_productname);
        order_detail_productrate = findViewById(R.id.order_detail_productrate);
        order_detail_quantity = findViewById(R.id.order_detail_quantity);
        txtpayment_status = findViewById(R.id.txtpayment_status);
        txtpayment_date = findViewById(R.id.txtpayment_date);
        payment_amt = findViewById(R.id.payment_amt);
        cart_image1 = findViewById(R.id.cart_image1);

        Intent intent = getIntent();
        final String id = intent.getStringExtra("id").trim();

        orderid.setText(id);

        db = FirebaseFirestore.getInstance();

        db.collection("shopping_cart")
                .document(id)
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        final String id = documentSnapshot.getId();

                        order_detail_quantity.setText((CharSequence) documentSnapshot.get("quantity"));
                        db.collection("products_master").document(id).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                            @Override
                            public void onSuccess(DocumentSnapshot documentSnapshot) {
                                Picasso.with(getApplicationContext())
                                        .load(String.valueOf(documentSnapshot.get("product_image")))
                                        .into(cart_image1);
                                System.out.println();
                                order_detail_productrate.setText(String.valueOf((documentSnapshot.get("product_rates"))));
                                order_detail_productname.setText(String.valueOf((documentSnapshot.get("product_name"))));

                            }
                        });
                    }
                });




    }
}