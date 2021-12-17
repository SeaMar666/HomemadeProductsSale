package com.example.homemadeproductssale.NavigationDrawer.Fragements;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.homemadeproductssale.R;

public class BoughtFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate (R.layout.fragment_bought, container, false);


        return  rootView;
    }
}
