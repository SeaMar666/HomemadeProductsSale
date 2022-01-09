package com.example.homemadeproductssale.Fragments;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homemadeproductssale.Adapters.OptionsAdapter;
import com.example.homemadeproductssale.R;

import java.util.ArrayList;

public class BasketFragment extends Fragment {

    EditText theFilter;
    ListView listView;
    ArrayAdapter<String> adapter;
    Button button;
    String products[] = {"Chia Aloe Soap","Corneal Soap","Glycerin Soap","Homemade Bread","Cookie","Cookie Choc","Handmade Cleaner"};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_basket, container, false);

        listView = rootView.findViewById(R.id.main_listView);
        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_multiple_choice,products);
        listView.setAdapter(adapter);

        theFilter = rootView.findViewById(R.id.searchFilter);
        theFilter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.getFilter().filter(s);
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        button = rootView.findViewById(R.id.printButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String itemSelected = "Selected product/s: \n";
                for (int i = 0; i < listView.getCount(); i++) {
                    if(listView.isItemChecked(i)){
                        itemSelected  += listView.getItemAtPosition(i) + "\n";
                    }
                }
                Toast.makeText(getContext(), itemSelected, Toast.LENGTH_SHORT).show();
            }
        });
        return rootView;
    }
}
