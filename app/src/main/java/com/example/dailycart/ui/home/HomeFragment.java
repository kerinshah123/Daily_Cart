package com.example.dailycart.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dailycart.R;

public class HomeFragment extends Fragment {
    ListView category_list;
    RecyclerView offer;

    private HomeViewModel homeViewModel;

    int images[] = {R.drawable.offer1,R.drawable.offer2,R.drawable.offer3,R.drawable.offer4};

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        category_list=root.findViewById(R.id.category_list);
        offer=root.findViewById(R.id.offer);

        CategoryAdapter categoryAdapter=new CategoryAdapter(getActivity().getApplicationContext());
        category_list.setAdapter(categoryAdapter);

        OfferAdapter offerAdapter = new OfferAdapter(getActivity().getApplicationContext(), images);
        offer.setHasFixedSize(true);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        offer.setLayoutManager(layoutManager);
        offer.setAdapter(offerAdapter);

        return root;
    }
}