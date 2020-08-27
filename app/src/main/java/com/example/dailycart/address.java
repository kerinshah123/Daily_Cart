package com.example.dailycart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

public class address extends AppCompatActivity {
    Button add, addaddress;
    RelativeLayout add_address;
    EditText address1, address2, address3, pincodeedit;
    Spinner city, state, area;
    RadioGroup grp;
    RadioButton address_type1, address_type2, address_type3;
    TextInputLayout nameLayout, addressLayout, codeLayout, cityLayout;
    private FirebaseAuth mAuth;
    String currentUser;
    String name, address, postal_code, city_address, address_type = "Home";
    CheckBox checkBox;

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
        grp = findViewById(R.id.addresstyperg);
        addaddress = findViewById(R.id.addaddress);
//        state=findViewById(R.id.spinnerstate);
//        area=findViewById(R.id.spinnerarea);
//        city=findViewById(R.id.spinnercity);
        address_type1 = findViewById(R.id.addresstype1);
        address_type2 = findViewById(R.id.addresstype2);
        address_type3 = findViewById(R.id.addresstype3);

        nameLayout = findViewById(R.id.addLine1);
        addressLayout = findViewById(R.id.addline2);
        codeLayout = findViewById(R.id.pincode);
        cityLayout = findViewById(R.id.addLine3);
        checkBox = findViewById(R.id.checkBoxAdd);

        address_type1.setChecked(true);

        mAuth = FirebaseAuth.getInstance();
        final FirebaseFirestore db = FirebaseFirestore.getInstance();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // email
            currentUser = user.getEmail();
            // FirebaseUser.getIdToken() instead.
            String uid = user.getUid();
        }



        grp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = findViewById(checkedId);

                address_type = radioButton.getText().toString();

            }
        });

        db.collection("address").whereEqualTo("email", currentUser)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                if (document.exists()) {
                                    address1.setText((CharSequence) document.get("name"));
                                    address2.setText((CharSequence) document.get("address"));
                                    address3.setText((CharSequence) document.get("city"));
                                    pincodeedit.setText((CharSequence) document.get("postalcode"));

                                    String type = (String) document.get("type");

                                    switch (type) {
                                        case "Home":
                                            address_type1.setChecked(true);
                                            break;
                                        case "Office":
                                            address_type2.setChecked(true);
                                            break;
                                        case "Other":
                                            address_type3.setChecked(true);
                                            break;
                                        default:
                                            address_type1.setChecked(true);
                                    }

                                }
                            }
                        } else {
                            Log.w("ERROR", "Error getting documents.", task.getException());
                        }
                    }
                });

        addaddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
                if (checkBox.isChecked()) {
                    Map<String, String> userMap = new HashMap<>();
                    userMap.put("name", name);
                    userMap.put("email", currentUser);
                    userMap.put("address", address);
                    userMap.put("city", city_address);
                    userMap.put("postalcode", postal_code);
                    userMap.put("type", address_type);

                    db.collection("address")
                            .add(userMap)
                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {

                                    Toast.makeText(getApplicationContext(), "Successfully Added", Toast.LENGTH_SHORT).show();
//
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                }


            }

            private void getData() {


                if (address1.getText().toString().length() <= 0) {
                    nameLayout.setError("Please Enter Name");
                }
                else if(address2.getText().toString().length() <= 0) {
                    addressLayout.setError("Please Enter Andress");
                }
               else if (pincodeedit.getText().toString().length() <= 0) {
                    codeLayout.setError("Please Enter Code");
                }

               else if (address3.getText().toString().length() <= 0) {
                    cityLayout.setError("Please Enter City");
                }

               else if(address_type.equals(null)){
                    Toast.makeText(address.this, "Please Select", Toast.LENGTH_SHORT).show();
                }else {
                    name = address1.getText().toString().trim();
                    address = address2.getText().toString().trim();
                    city_address = address3.getText().toString().trim();
                    postal_code = pincodeedit.getText().toString().trim();

                }
            }
        });

    }


}