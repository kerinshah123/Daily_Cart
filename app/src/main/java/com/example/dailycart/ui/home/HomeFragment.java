package com.example.dailycart.ui.home;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dailycart.PojoClass.CategoryPojo;
import com.example.dailycart.R;
import com.example.dailycart.sub_category;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.squareup.picasso.Picasso;

public class HomeFragment extends Fragment {
    RecyclerView category_list;
    RecyclerView offer;
    FirebaseFirestore CategoryDB;

    FirestoreRecyclerAdapter<CategoryPojo, CategoryView> adapterCategory;
    private HomeViewModel homeViewModel;

    int images[] = {R.drawable.offer1,R.drawable.offer2,R.drawable.offer3,R.drawable.offer4};

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, final Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        category_list=root.findViewById(R.id.category_list);

        category_list.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));

        offer=root.findViewById(R.id.offer);

        OfferAdapter offerAdapter = new OfferAdapter(getActivity().getApplicationContext(), images);
        offer.setHasFixedSize(true);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        offer.setLayoutManager(layoutManager);
        offer.setAdapter(offerAdapter);

        CategoryDB = FirebaseFirestore.getInstance();

        final Query Cat_query = CategoryDB.collection("product_category");

        FirestoreRecyclerOptions<CategoryPojo> Cat_options = new FirestoreRecyclerOptions.Builder<CategoryPojo>()
                .setQuery(Cat_query, CategoryPojo.class)
                .build();


        adapterCategory = new FirestoreRecyclerAdapter<CategoryPojo, CategoryView>(Cat_options) {
            @Override
            protected void onBindViewHolder(@NonNull final CategoryView holder, int position, @NonNull final CategoryPojo model) {
                final String id = getSnapshots().getSnapshot(position).getId();

                Picasso.with(getActivity())
                        .load(model.getCategory_image_url())
                        .into(holder.cat1);
                holder.txtcat1.setText(model.getCategory_name());

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SharedPreferences sharedPreferences = getContext().getSharedPreferences("ID", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("id",id);
                        editor.commit();

                        startActivity(new Intent(getContext(),sub_category.class));

                    }
                });

            }

            @NonNull
            @Override
            public CategoryView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.categorylayout,parent,false);
                return new CategoryView(view);
            }
        };

        category_list.setAdapter(adapterCategory);

        return root;
    }

    private class CategoryView extends RecyclerView.ViewHolder {

        ImageView cat1;
        TextView txtcat1;

        public CategoryView(@NonNull View itemView) {
            super(itemView);

            cat1 = itemView.findViewById(R.id.cat1);
            txtcat1 = itemView.findViewById(R.id.txtcat1);
        }
    }


    @Override
    public void onStart() {
        super.onStart();
        adapterCategory.startListening();

    }
}