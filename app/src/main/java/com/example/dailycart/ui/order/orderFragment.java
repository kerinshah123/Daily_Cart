package com.example.dailycart.ui.order;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dailycart.R;
import com.example.dailycart.product_details;
import com.example.dailycart.ui.shoppingcart.CartPojo;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link orderFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class orderFragment extends Fragment {

    RecyclerView order_fram_list;
    FirebaseFirestore OrderDb;
    FirebaseFirestore db;

    FirestoreRecyclerAdapter<CartPojo,Orderview> adapterorder;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public orderFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment orderFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static orderFragment newInstance(String param1, String param2) {
        orderFragment fragment = new orderFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_order, container, false);

        db = FirebaseFirestore.getInstance();

        order_fram_list = view.findViewById(R.id.order_fram_list);
        order_fram_list.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));

        OrderDb = FirebaseFirestore.getInstance();

        final Query Cart_query = OrderDb.collection("shopping_cart")
                .whereEqualTo("email", FirebaseAuth.getInstance().getCurrentUser().getEmail())
                .whereEqualTo("status", "order");

        FirestoreRecyclerOptions<CartPojo> Cart_options = new FirestoreRecyclerOptions.Builder<CartPojo>()
                .setQuery(Cart_query, CartPojo.class)
                .build();

        adapterorder = new FirestoreRecyclerAdapter<CartPojo, Orderview>(Cart_options) {
            @Override
            protected void onBindViewHolder(@NonNull final Orderview holder, int position, @NonNull final CartPojo model) {
                holder.cartproductqty.setText(model.getQuantity());
                final int quantity = Integer.parseInt(model.getQuantity());
                final String idmain = getSnapshots().getSnapshot(position).getId();
                final String id = model.getId();

                holder.show_order_details.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent1 = new Intent(getActivity(), OrderDetailActivity.class);
                        intent1.putExtra("id",idmain);
                        startActivity(intent1);
                    }
                });

                db.collection("products_master").document(id).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        Picasso.with(getActivity())
                                .load(String.valueOf(documentSnapshot.get("product_image")))
                                .into(holder.cart_image);
                        System.out.println();
                        int price = Integer.parseInt(String.valueOf((documentSnapshot.get("product_rates"))));
                        int Final = quantity*price;
                        holder.cartproductrate.setText(String.valueOf(String.valueOf(Final)));
                      //  holder.cartproductrate.setText(String.valueOf((documentSnapshot.get("product_rates"))));
                        holder.cartproductname.setText(String.valueOf((documentSnapshot.get("product_name"))));

                    }
                });
            }

            @NonNull
            @Override
            public Orderview onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_custom,parent,false);
                return new Orderview(view);
            }
        };

        order_fram_list.setAdapter(adapterorder);

//        OrderAdapter orderAdapter = new OrderAdapter(getActivity().getApplicationContext());
//        order_fram_list.setAdapter(orderAdapter);

        return view;
    }

    private class Orderview extends RecyclerView.ViewHolder {

        ImageView cart_image;
        TextView cartproductname,cartproductrate,cartproductqty;
        Button show_order_details;

        public Orderview(@NonNull View itemView) {
            super(itemView);

            cart_image = itemView.findViewById(R.id.cart_image);
            cartproductname = itemView.findViewById(R.id.cartproductname);
            cartproductrate = itemView.findViewById(R.id.cartproductrate);
            cartproductqty = itemView.findViewById(R.id.cartproductqty);
            show_order_details = itemView.findViewById(R.id.show_order_details);
        }
    }


    @Override
    public void onStart() {
        super.onStart();
        adapterorder.startListening();

    }
}