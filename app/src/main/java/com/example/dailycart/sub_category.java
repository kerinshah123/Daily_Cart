package com.example.dailycart;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.dailycart.PojoClass.SubCategoryPojo;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.squareup.picasso.Picasso;

public class sub_category extends AppCompatActivity {
    RecyclerView subcategory_list;
    FirebaseFirestore SubCategoryDB;
    String idIntent = "";

    @Override
    protected void onStart() {
        super.onStart();
        adapterCategory.startListening();
    }

    FirestoreRecyclerAdapter<SubCategoryPojo, SubCategoryView> adapterCategory;

    GridView gridview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_category);


        // gridview = findViewById(R.id.gridview);
        //SubcategoryAdapter subcategoryAdapter = new SubcategoryAdapter(getApplicationContext());
        //gridview.setAdapter(subcategoryAdapter);

        subcategory_list = findViewById(R.id.subcategory_list);

        subcategory_list.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));

        SubCategoryDB = FirebaseFirestore.getInstance();

        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("ID", Context.MODE_PRIVATE);
        idIntent = sharedPreferences.getString("id","").trim();


        final Query Cat_query = SubCategoryDB.collection("product_sub_category_main").whereEqualTo("category_id",idIntent.trim());

        FirestoreRecyclerOptions<SubCategoryPojo> Cat_options = new FirestoreRecyclerOptions.Builder<SubCategoryPojo>()
                .setQuery(Cat_query, SubCategoryPojo.class)
                .build();


        adapterCategory = new FirestoreRecyclerAdapter<SubCategoryPojo, SubCategoryView>(Cat_options) {
            @NonNull
            @Override
            public SubCategoryView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.subcatagory, parent, false);
                return new SubCategoryView(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull SubCategoryView holder, int position, @NonNull SubCategoryPojo model) {
                final String id = getSnapshots().getSnapshot(position).getId();

                    Picasso.with(getApplicationContext())
                            .load(model.getSub_category_image())
                            .into(holder.image_sub);
                    holder.sub_name.setText(model.getSub_category_name().toUpperCase());

                    holder.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("SUB_ID", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("id",id.trim());
                            editor.commit();

                            startActivity(new Intent(getApplicationContext(),product_list.class));
                        }
                    });



            }

        };


        subcategory_list.setAdapter(adapterCategory);

    }

    private class SubCategoryView extends RecyclerView.ViewHolder {

        ImageView image_sub;
        TextView sub_name;

        public SubCategoryView(@NonNull View itemView) {
            super(itemView);

            image_sub = itemView.findViewById(R.id.image_sub);
            sub_name = itemView.findViewById(R.id.sub_name);
        }
    }




//    @Override
//    public void onStart() {
//        super.onStart();
//        adapterCategory.startListening();
//    }
}