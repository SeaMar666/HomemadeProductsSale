package com.example.homemadeproductssale;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.homemadeproductssale.DatabaseHelpers.DBHelperIdeas;
import com.example.homemadeproductssale.Fragments.YourOptionsFragment;

public class AddIdeaActivity extends AppCompatActivity {

    EditText name_input, price_input;
    Button add_button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);


        Toolbar toolbar = findViewById(R.id.toolbarAddActivity);
        toolbar.setTitle("Add Product Idea");
        setSupportActionBar(toolbar);

        toolbar.setBackgroundColor(getResources().getColor(R.color.followingBg));

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        name_input = findViewById(R.id.productName_input);
        price_input = findViewById(R.id.priceProduct_input);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelperIdeas myDB = new DBHelperIdeas(AddIdeaActivity.this);
                myDB.addProductIdea(name_input.getText().toString().trim(),
                        Integer.valueOf(price_input.getText().toString().trim()));

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
