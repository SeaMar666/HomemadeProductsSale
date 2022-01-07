package com.example.homemadeproductssale.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homemadeproductssale.Adapters.ProductsAdapter;
import com.example.homemadeproductssale.DatabaseHelpers.DBHelper;
import com.example.homemadeproductssale.Model.Product;
import com.example.homemadeproductssale.R;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment  implements ProductsAdapter.OnProductListener {

    private static final String TAG = " Home ";
    RecyclerView recyclerView;
    ProductsAdapter productsAdapter;
    DBHelper dbHelper;
    RecyclerView.LayoutManager layoutManager;
    List<Product> products = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        dbHelper = new DBHelper(this.getContext());
        insertValues();

        Toolbar toolbar = rootView.findViewById(R.id.toolbarHome);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);
        toolbar.setBackgroundColor(getResources().getColor(R.color.followingBg));

        products = dbHelper.getAllProducts();
        recyclerView = rootView.findViewById(R.id.recyclerviewProducts);
        layoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(layoutManager);
        productsAdapter = new ProductsAdapter(this.getContext(), products, this);
        recyclerView.setAdapter(productsAdapter);
        //productsAdapter.clear();
        //dbHelper.deleteProduct();

        return rootView;
    }

    @Override
    public void onProductClick(int position) {
        Log.d(TAG, "onProductClick: clicked");
    }

    public void insertValues(){
        //dbHelper.insertProduct("Chia Aloe Soap",50 , R.drawable.chia_aloe_exfoliating_soap);
        //dbHelper.insertProduct("Corneal Soap",50 , R.drawable.cornmeal_exfoliating_soap);
        //dbHelper.insertProduct("Glycerin Soap",50 , R.drawable.glycerin_holiday_soap);
        //  dbHelper.insertProduct("Homemade Bread",50 , R.drawable.bread_1);
        //  dbHelper.insertProduct("Cookie",50 , R.drawable.cookie);
        //  dbHelper.insertProduct("Cookie Choc",50 , R.drawable.cookide_2);
        // dbHelper.insertProduct("Handmade Cleaner",50 , R.drawable.homemade_cleaner);
    }


}
