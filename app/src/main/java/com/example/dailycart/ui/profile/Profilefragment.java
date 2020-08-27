package com.example.dailycart.ui.profile;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.dailycart.MainActivity;
import com.example.dailycart.R;
import com.example.dailycart.homeActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;
import java.util.zip.Inflater;

public class Profilefragment extends Fragment {

    private ProfileViewModel profileViewModel;
    private TextView name, email, number;
    private ImageView nameImage, emailImage, numberImage;
    private FirebaseAuth mAuth;
    String currentUser;
    String nameString, emailString, numberString,documentId;
    Button save;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        //   profileViewModel =
        //           ViewModelProviders.of(this).get(ProfileViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);

        name = root.findViewById(R.id.username123);
        email = root.findViewById(R.id.useremail123);
        number = root.findViewById(R.id.user_number);

        nameImage = root.findViewById(R.id.name_edit);
        emailImage = root.findViewById(R.id.email_edit);
        numberImage = root.findViewById(R.id.number_edit);

        mAuth = FirebaseAuth.getInstance();
        final FirebaseFirestore db = FirebaseFirestore.getInstance();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // email
            currentUser = user.getEmail();
            // FirebaseUser.getIdToken() instead.
            String uid = user.getUid();
        }

        db.collection("users").whereEqualTo("email", currentUser)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                documentId = document.getId();
                                name.setText((CharSequence) document.get("name"));
                                nameString = (String) document.get("name");
                                email.setText((CharSequence) document.get("email"));
                                emailString = (String) document.get("email");
                                number.setText(String.valueOf(document.get("number")));
                                numberString = (String) document.get("number");
                            }
                        } else {
                            Log.w("ERROR", "Error getting documents.", task.getException());
                        }
                    }
                });

        nameImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                final View customLayout = getLayoutInflater().inflate(R.layout.custom_layout, null);
                builder.setView(customLayout);
                builder.setTitle("Name");
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        EditText editText = customLayout.findViewById(R.id.editTextAlert);
                        if(editText.getText().toString() != ""){
                            nameString = editText.getText().toString().trim();
                            name.setText(nameString);
                        }


                    }
                });
                builder.show();

            }
        });

        emailImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                final View customLayout = getLayoutInflater().inflate(R.layout.custom_layout, null);
                builder.setView(customLayout);
                builder.setTitle("Email");
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        EditText editText = customLayout.findViewById(R.id.editTextAlert);
                        if(editText.getText().toString() != ""){
                            emailString = editText.getText().toString().trim();
                            email.setText(emailString);
                        }

                    }
                });
                builder.show();

            }


        });

        numberImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                final View customLayout = getLayoutInflater().inflate(R.layout.custom_layout, null);
                builder.setView(customLayout);
                builder.setTitle("Number");
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        EditText editText = customLayout.findViewById(R.id.editTextAlert);
                        if(editText.getText().toString() != ""){
                            numberString = editText.getText().toString().trim();
                            number.setText(numberString);
                        }

                    }
                });
                builder.show();

            }


        });

        save = root.findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Map<String,String> userMap = new HashMap<>();
                userMap.put("name",nameString);
                userMap.put("email",emailString);
                userMap.put("number",numberString);


                        db.collection("users")
                        .document(documentId)
                        .set(userMap, SetOptions.merge())
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(getContext(), "Updated", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getContext(), "Something Wrong!", Toast.LENGTH_SHORT).show();
                            }
                        });




            }
        });




//        profileViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//
//            }
//        });
        return root;
    }
}