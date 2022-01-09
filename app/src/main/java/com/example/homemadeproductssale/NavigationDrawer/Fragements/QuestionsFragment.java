package com.example.homemadeproductssale.NavigationDrawer.Fragements;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.homemadeproductssale.R;

public class QuestionsFragment extends Fragment {

    Button button;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_questions, container, false);

        button = rootView.findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentContainerQuestions secondFragment = new FragmentContainerQuestions();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id._Layout, secondFragment);
                transaction.addToBackStack(null);
                transaction.commit();
                button.setVisibility(View.GONE);
            }
        });
        return rootView;
    }
}
