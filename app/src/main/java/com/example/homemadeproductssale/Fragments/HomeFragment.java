package com.example.homemadeproductssale.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.homemadeproductssale.Adapters.ProductsAdapter;
import com.example.homemadeproductssale.DBHelper;
import com.example.homemadeproductssale.Model.Product;
import com.example.homemadeproductssale.ProductPageActivity;
import com.example.homemadeproductssale.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment  implements ProductsAdapter.OnProductListener {

    private static final String TAG = " Home ";
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
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        dbHelper = new DBHelper(this.getContext());
        insertValues();

        Toolbar toolbar = rootView.findViewById(R.id.toolbarHome);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);

        products = dbHelper.getAllProducts();
        recyclerView = rootView.findViewById(R.id.recyclerviewProducts);
        mName = rootView.findViewById(R.id.productName_text);
        mPrice = rootView.findViewById(R.id.price_text);
        imageView = rootView.findViewById(R.id.product_image);
        layoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(layoutManager);
        productsAdapter = new ProductsAdapter(this.getContext(), products, this);
        recyclerView.setAdapter(productsAdapter);
        //handmadeProductsAdapter.clear();
        //dbHelper.deleteProduct();

        return rootView;
    }

    @Override
    public void onProductClick(int position) {
        //products.get(position);
        Log.d(TAG, "onProductClick: clicked");
        Intent intent = new Intent(getContext(), ProductPageActivity.class);
       // intent.putExtra("product_name","productName");
        startActivity(intent);
    }


    public void insertValues(){
        //dbHelper.insertProduct("Chia Aloe Soap",50 , R.drawable.chia_aloe_exfoliating_soap);
        //dbHelper.insertProduct("Corneal Soap",50 , R.drawable.cornmeal_exfoliating_soap);
        //dbHelper.insertProduct("Glycerin Soap",50 , R.drawable.glycerin_holiday_soap);

    }


}
