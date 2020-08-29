package com.example.dailycart;
/**
 *
 * @author Arshdeep  Kaur
 */
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

/**
 * payment class for take the payment details of the customer on buying products
 */
public class Payment extends AppCompatActivity {
    TextInputEditText name,number,dateExpiry,cvv;
    TextInputLayout nameLayout,numberLayout,dateLayout,cvvLayout;
    CheckBox checkBox;
    Button button;
    String namepattern = "[a-zA-Z]+";

    String currentUser;

    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        name = findViewById(R.id.nameOnCard);
        number = findViewById(R.id.cardNumber);
        dateExpiry = findViewById(R.id.expiryDate);
        cvv = findViewById(R.id.cvvNumber);

        nameLayout = findViewById(R.id.nameInputLayout);
        numberLayout = findViewById(R.id.numberInputLayout);
        dateLayout = findViewById(R.id.expiryDateLayout);
        cvvLayout = findViewById(R.id.cvvLayout);

        checkBox = findViewById(R.id.checkBoxSaveCard);
        button = findViewById(R.id.buttonPay);
        Bundle bundle = getIntent().getExtras();

        name.addTextChangedListener(new TextWatcher() {
            /**
             *
             * @param s
             * @param start
             * @param count
             * @param after
             */
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            /**
             *
             * @param s
             * @param start
             * @param before
             * @param count
             */
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            /**
             *
             * @param s
             */
            @Override
            public void afterTextChanged(Editable s) {
                if (name.getText().toString().matches(namepattern) && s.length() > 0) {
                    nameLayout.setError(null);
                } else {
                    nameLayout.setError("Invalid Name");
                }

            }
        });
        number.addTextChangedListener(new TextWatcher() {
            /**
             *
             * @param s
             * @param start
             * @param count
             * @param after
             */
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            /**
             *
             * @param s
             * @param start
             * @param before
             * @param count
             */
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            /**
             *
             * @param s
             */
            @Override
            public void afterTextChanged(Editable s) {
                if(number.getText().toString().trim().length() < 16)
                {
                    numberLayout.setError("Invalid card number");
                }

            }
        });
        dateExpiry.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            /**
             *
             * @param s
             * @param start
             * @param before
             * @param count
             */
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            /**
             *
             * @param s
             */
            @Override
            public void afterTextChanged(Editable s) {
                if(dateExpiry.getText().toString().trim().length() < 7)
                {
                    dateLayout.setError("Invalid Date");
                }
            }
        });
        cvv.addTextChangedListener(new TextWatcher() {
            /**
             *
             * @param s
             * @param start
             * @param count
             * @param after
             */
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            /**
             *
             * @param s
             * @param start
             * @param before
             * @param count
             */
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            /**
             *
             * @param s
             */
            @Override
            public void afterTextChanged(Editable s) {
                if(cvv.getText().toString().trim().length() < 3)
                {
                    cvvLayout.setError("Invalid CVV");
                }

            }
        });




        button.setOnClickListener(new View.OnClickListener() {
            /**
             *
             * @param v
             */
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(name.getText().toString())) {
                    nameLayout.setError("Enter Name Plz..");
                    nameLayout.requestFocus();
                } else if (TextUtils.isEmpty(number.getText().toString())) {
                    numberLayout.setError("Enter Number Plz..");
                    numberLayout.requestFocus();
                } else if (TextUtils.isEmpty(dateExpiry.getText().toString())) {
                    dateLayout.setError("Enter Date Plz..");
                    dateLayout.requestFocus();
                } else if (TextUtils.isEmpty(cvv.getText().toString())) {
                    cvvLayout.setError("Enter CVV Plz..");
                    cvvLayout.requestFocus();
                } else {
                    if(checkBox.isChecked()){
                        //SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("hello,sign in",MODE_PRIVATE);
                        //final String user = sharedPreferences.getString("email", "");

                        final Map<String, Object> data = new HashMap<>();
                        data.put("name", name.getText().toString().trim());
                        data.put("cardnumber",number.getText().toString().trim());
                        data.put("expiryDate",dateExpiry.getText().toString().trim() );
                        data.put("CVV", cvv.getText().toString().trim());
                        //data.put("email",user);

                        final FirebaseFirestore db = FirebaseFirestore.getInstance();
                        db.collection("Payment").document().set(data).addOnSuccessListener(new OnSuccessListener<Void>() {
                            /**
                             *
                             * @param aVoid
                             */
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(Payment.this, "Card Details Saved", Toast.LENGTH_LONG).show();

                            }
                        });

                    }


                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if (user != null) {
                        // email
                        currentUser = user.getEmail();
                        // FirebaseUser.getIdToken() instead.
                        String uid = user.getUid();
                    }
                    final Map<String, Object> data1 = new HashMap<>();
                    data1.put("status", "order");

                    final FirebaseFirestore db = FirebaseFirestore.getInstance();

                    db.collection("shopping_cart")
                            .whereEqualTo("email",currentUser)
                            .whereEqualTo("status","cart")
                            .get()
                            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                /**
                                 *
                                 * @param task
                                 */
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    String userId = null;
                                    for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                                        userId = documentSnapshot.getId();
                                        db.collection("shopping_cart")
                                                .document(userId)
                                                .set(data1, SetOptions.merge());
                                    }
                                }
                            });

                    Toast.makeText(Payment.this, "Payment Done", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), homeActivity.class);
                    startActivity(intent);

                }



            }

        });
    }

    /**
     * onstart method starts with activity loading
     */
    @Override
    public void onStart() {
        super.onStart();

    }
}
