package com.example.dailycart;
/**
 *
 * @author Keyur  Mistry
 */
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

/**
 * product_details class show detailed information about products
 */
public class product_details extends AppCompatActivity {
    CollapsingToolbarLayout coll;
    ImageView productimage,imageplus,imageminus;
    TextView productname,productprice,productdescription,quantity;
    FloatingActionButton cart;
    Button offer_apply;
    EditText offer;
    int number = 1;
    private FirebaseAuth mAuth;
    String email;

    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        final ActionBar actionBar = getSupportActionBar();

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

        mAuth = FirebaseAuth.getInstance();
        email = mAuth.getCurrentUser().getEmail();

        coll.setExpandedTitleTextAppearance(R.style.coll2);
        coll.setCollapsedTitleTextAppearance(R.style.coll);

        final FirebaseFirestore db = FirebaseFirestore.getInstance();

        imageplus.setOnClickListener(new View.OnClickListener() {
            /**
             *
             * @param v
             */
            @Override
            public void onClick(View v) {
                number++;
                quantity.setText(String.valueOf(number));
            }
        });

        imageminus.setOnClickListener(new View.OnClickListener() {
            /**
             *
             * @param v
             */
            @Override

            public void onClick(View v) {
                if(number>=1){
                    number--;
                    quantity.setText(String.valueOf(number));
                }

            }
        });

        Intent intent = getIntent();
        final String id = intent.getStringExtra("id").trim();

        db.collection("products_master").document(id)
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        DocumentSnapshot document = task.getResult();
                        productname.setText((CharSequence) document.get("product_name"));
                        actionBar.setTitle((CharSequence) document.get("product_name"));

                        productdescription.setText((CharSequence) document.get("product_description"));
                        productprice.setText((CharSequence) document.get("product_rates"));
                        Picasso.with(getApplicationContext())
                                .load(String.valueOf((CharSequence)document.get("product_image")))
                                .placeholder(R.drawable.chocolatewalnetp)
                                .into(productimage);

                    }
                });

        cart.setOnClickListener(new View.OnClickListener() {
            /**
             *
             * @param v
             */
            @Override
            public void onClick(View v) {
                Map<String, Object> user = new HashMap<>();
                user.put("id", id);
                user.put("quantity", String.valueOf(number));
                user.put("email", email);
                user.put("status", "cart");

                db.collection("shopping_cart").document(id)
                        .set(user)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            /**
                             *
                             * @param aVoid
                             */
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(product_details.this, "Product Added to cart", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            /**
                             *
                             * @param e
                             */
                            @Override
                            public void onFailure(@NonNull Exception e) {

                            }
                        });

            }
        });


    }
}