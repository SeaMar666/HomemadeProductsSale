package com.example.homemadeproductssale.Fragments;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homemadeproductssale.Adapters.OptionsAdapter;
import com.example.homemadeproductssale.AddIdeaActivity;
import com.example.homemadeproductssale.DatabaseHelpers.DBHelperIdeas;
import com.example.homemadeproductssale.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class YourOptionsFragment extends Fragment {

    RecyclerView recyclerView;
    FloatingActionButton add_button;
    ImageView empty_imageview;
    TextView no_data;

    DBHelperIdeas myDB;
    ArrayList<String> product_id, product_name_idea, product_price_idea;
    OptionsAdapter customAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_your_options, container, false);

        recyclerView = rootView.findViewById(R.id.recyclerViewIdeas);
        add_button = rootView.findViewById(R.id.add_button);
        empty_imageview = rootView.findViewById(R.id.empty_imageview);
        no_data = rootView.findViewById(R.id.no_data);

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AddIdeaActivity.class);
                startActivity(intent);
            }
        });

        myDB = new DBHelperIdeas(getContext());
        product_id = new ArrayList<>();
        product_name_idea = new ArrayList<>();
        product_price_idea = new ArrayList<>();

        storeDataInArrays();

        customAdapter = new OptionsAdapter(getActivity(), getContext(),product_id,product_price_idea,product_name_idea );

        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        setHasOptionsMenu(true);

        refreshFragment();

        return rootView;
    }


    void storeDataInArrays(){
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0){
            empty_imageview.setVisibility(View.VISIBLE);
            no_data.setVisibility(View.VISIBLE);
        }else{
            while (cursor.moveToNext()){
                product_id.add(cursor.getString(0));
                product_name_idea.add(cursor.getString(1));
                product_price_idea.add(cursor.getString(2));
            }
            empty_imageview.setVisibility(View.GONE);
            no_data.setVisibility(View.GONE);
        }
    }

    private void refreshFragment() {
        getFragmentManager()
                .beginTransaction()
                .detach(this)
                .attach(this)
                .addToBackStack(null)
                .commit();
    }

}
