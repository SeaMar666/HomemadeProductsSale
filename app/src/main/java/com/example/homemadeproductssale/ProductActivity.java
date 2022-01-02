package com.example.homemadeproductssale;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ProductActivity extends AppCompatActivity {

    ImageView productImage;
    TextView productName, productPrice;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_page);

        productImage = findViewById(R.id.product_image);
        productName = findViewById(R.id.product_name);
        productPrice = findViewById(R.id.product_price);

        Intent i = getIntent();
        //int Photo = i.getExtras().get("ProductPhoto");
        String Name = i.getExtras().getString("ProductName");
        String Price = i.getExtras().getString("ProductPrice");

        //productImage.setImageResource(Photo);
        productName.setText(Name);
        productPrice.setText(Price);



    }
}
