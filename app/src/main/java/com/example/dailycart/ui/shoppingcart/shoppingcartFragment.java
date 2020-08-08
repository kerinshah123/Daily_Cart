package com.example.dailycart.ui.shoppingcart;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.example.dailycart.R;
import com.example.dailycart.address;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link shoppingcartFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class shoppingcartFragment extends Fragment {
    static ListView cart_list;
    public static Button checkout;
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
        CartAdapter cartAdapter =new CartAdapter(getActivity());
        cart_list.setAdapter(cartAdapter);

        checkout=view.findViewById(R.id.checkout);
        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(checkout.getText().toString().equals("You Dont Have any product in cart"))
                {

                }

                else if(checkout.getText().toString().equals("Checkout $ 0"))
                {

                }
                else
                {
                    Intent i = new Intent(getActivity(), address.class);
                    view.getContext().startActivity(i);

                }

            }
        });

        return view;
    }
}