package com.example.dailycart.ui.shoppingcart;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dailycart.OfferDetail;
import com.example.dailycart.PojoClass.CategoryPojo;
import com.example.dailycart.R;
import com.example.dailycart.address;
import com.example.dailycart.sub_category;
import com.example.dailycart.ui.home.HomeFragment;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link shoppingcartFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class shoppingcartFragment extends Fragment {
    //static ListView cart_list;
    public static Button checkout;
    RecyclerView cart_list;
    FirebaseFirestore CartDB;
    FirebaseFirestore db;

    FirestoreRecyclerAdapter<CartPojo, CartView> adapterCart;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public shoppingcartFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment shoppingcartFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static shoppingcartFragment newInstance(String param1, String param2) {
        shoppingcartFragment fragment = new shoppingcartFragment();
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
        View view = inflater.inflate(R.layout.fragment_shoppingcart, container, false);

        cart_list=view.findViewById(R.id.cart_list);
       // CartAdapter cartAdapter =new CartAdapter(getActivity());
        //cart_list.setAdapter(cartAdapter);
        cart_list.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);


        CartDB = FirebaseFirestore.getInstance();
        db = FirebaseFirestore.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


        final Query Cat_query = CartDB.collection("shopping_cart")
                .whereEqualTo("email",FirebaseAuth.getInstance().getCurrentUser().getEmail())
                .whereEqualTo("status","cart");

        FirestoreRecyclerOptions<CartPojo> Cat_options = new FirestoreRecyclerOptions.Builder<CartPojo>()
                .setQuery(Cat_query, CartPojo.class)
                .build();


        adapterCart = new FirestoreRecyclerAdapter<CartPojo, shoppingcartFragment.CartView>(Cat_options) {
            @Override
            protected void onBindViewHolder(@NonNull final shoppingcartFragment.CartView holder, int position, @NonNull final CartPojo model) {
                holder.quantity.setText(model.getQuantity());
                final String idmain = getSnapshots().getSnapshot(position).getId();
                final String id = model.getId();


                holder.cartproductdelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CartDB.collection("shopping_cart").document(idmain)
                                .delete()
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Toast.makeText(getContext(), "Deleted", Toast.LENGTH_SHORT).show();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {

                                    }
                                });
                    }
                });


                //Toast.makeText(getActivity(), ""+id, Toast.LENGTH_SHORT).show();
                db.collection("products_master").document(id).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                            @Override
                            public void onSuccess(DocumentSnapshot documentSnapshot) {
                                checkout.setText("Checkout");
                               Picasso.with(getActivity())
                                        .load(String.valueOf(documentSnapshot.get("product_image")))
                                        .into(holder.cart1);
                                System.out.println();

                                holder.price.setText(String.valueOf((documentSnapshot.get("product_rates"))));
                                holder.txtcart1.setText(String.valueOf((documentSnapshot.get("product_name"))));

                            }
                        });

//                db.collection("products_master").document(id.trim())
//                        .get()
//                        .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//                            @Override
//                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                                DocumentSnapshot documentSnapshot = task.getResult();
//                                Toast.makeText(getContext(), (CharSequence) documentSnapshot.get("product_rates"), Toast.LENGTH_SHORT).show();
//
//                            }
//                        });

//                final Query product_query = CartDB.collection("products_master");


//                holder.txtcart1.setText(model.getCategory_name());



            }

            @NonNull
            @Override
            public shoppingcartFragment.CartView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart,parent,false);
                return new CartView(view);
            }
        };

        cart_list.setAdapter(adapterCart);
        checkout=view.findViewById(R.id.checkout);
        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(checkout.getText().toString().equals("You have no product in cart"))
                {
                    Toast.makeText(getActivity(), "No product found", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent i = new Intent(getActivity(), address.class);
                    view.getContext().startActivity(i);
                }

            }
        });

        return view;

        //return root;
    }

    private class CartView extends RecyclerView.ViewHolder {

        ImageView cart1;
        TextView txtcart1,price,quantity;
        Button cartproductdelete;

        public CartView(@NonNull View itemView) {
            super(itemView);

            cart1 = itemView.findViewById(R.id.cart_image);
            txtcart1 = itemView.findViewById(R.id.cartproductname);
            price= itemView.findViewById(R.id.cartproductrate);
            quantity= itemView.findViewById(R.id.cartproductqty);
            cartproductdelete= itemView.findViewById(R.id.cartproductdelete);
        }
    }


    @Override
    public void onStart() {
        super.onStart();
        adapterCart.startListening();

    }


}