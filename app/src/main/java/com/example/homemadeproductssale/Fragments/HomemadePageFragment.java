package com.example.homemadeproductssale.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homemadeproductssale.DBHelper;
import com.example.homemadeproductssale.Product;
import com.example.homemadeproductssale.ProductsAdapter;
import com.example.homemadeproductssale.R;

import java.util.ArrayList;
import java.util.List;

public class HomemadePageFragment extends Fragment {

    RecyclerView recyclerView;
    ProductsAdapter productsAdapter;
    DBHelper dbHelper;
    RecyclerView.LayoutManager layoutManager;
    List<Product> products = new ArrayList<>();
    EditText mName, mPrice;
    ImageView imageView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_homemade, container, false);

        dbHelper = new DBHelper(this.getContext());


        products = dbHelper.getAllProducts();
        recyclerView = rootView.findViewById(R.id.recyclerview);
        mName = rootView.findViewById(R.id.productName_text);
        mPrice = rootView.findViewById(R.id.price_text);
        imageView = rootView.findViewById(R.id.product_image);
        layoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(layoutManager);
        productsAdapter = new ProductsAdapter(this.getContext(), products);
        recyclerView.setAdapter(productsAdapter);

        insertValues();

        return rootView;
    }


    public void insertValues(){
        dbHelper.insertProduct("Soap1",20 , R.drawable.chia_aloe_exfoliating_soap);
        dbHelper.insertProduct("Soap2",20 , R.drawable.glycerin_holiday_soap);
        dbHelper.insertProduct("Soap3",20 , R.drawable.cornmeal_exfoliating_soap);
    }
}
