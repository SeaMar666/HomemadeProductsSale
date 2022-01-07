package com.example.homemadeproductssale.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homemadeproductssale.Adapters.FavoritesAdapter;
import com.example.homemadeproductssale.R;

import java.util.ArrayList;

public class BasketFragment extends Fragment {

    RecyclerView recyclerView;
    FavoritesAdapter customAdapter;
    ArrayList<String> product_id, productName, productPrice;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_basket, container, false);

        recyclerView = rootView.findViewById(R.id.recyclerViewFavs);
        product_id = new ArrayList<>();
        productName = new ArrayList<>();
        productPrice = new ArrayList<>();

        customAdapter = new FavoritesAdapter(this.getActivity(),this.getContext(), product_id, productPrice);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        return rootView;
    }
}
