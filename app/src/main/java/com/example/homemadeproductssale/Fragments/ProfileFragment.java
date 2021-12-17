package com.example.homemadeproductssale.Fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.homemadeproductssale.LoginActivity;
import com.example.homemadeproductssale.NavigationDrawer.Fragements.BoughtFragment;
import com.example.homemadeproductssale.NavigationDrawer.Fragements.HomeUserInfoFragment;
import com.example.homemadeproductssale.R;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Locale;

public class ProfileFragment extends Fragment implements NavigationView.OnNavigationItemSelectedListener{

    Toolbar toolbar;
    private DrawerLayout drawer;
    NavigationView navigationView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);
        toolbar = rootView.findViewById(R.id.toolbarNav);
        drawer = rootView.findViewById(R.id.drawer_layout);
        navigationView = rootView.findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        loadLocale();

        DrawerLayout drawer =  rootView.findViewById(R.id.drawer_layout);

        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(getActivity(),drawer,toolbar,
                R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        Fragment fragment = new HomeUserInfoFragment();
        FragmentTransaction ft = getChildFragmentManager().beginTransaction();
        ft.add(R.id.fragment_container, fragment);
        ft.commit();



        return rootView;
    }

    private  void showChangeLanguageDialog(){
        final String[] listItems = {"Turkish", "Spanish", "English"};
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(getContext());
        mBuilder.setTitle("Choose Language...");
        mBuilder.setSingleChoiceItems(listItems, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(i == 0){
                    setLocale("Tr");
                    getActivity().recreate();
                }
                else if(i == 0){
                    setLocale("Spn");
                    getActivity().recreate();
                }
                else if(i == 0){
                    setLocale("Eng");
                    getActivity().recreate();
                }
                dialogInterface.dismiss();
            }
        });
        AlertDialog mDialog = mBuilder.create();
        mDialog.show();
    }

    private void setLocale(String lang){
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getActivity().getResources().updateConfiguration(config, getActivity().getResources().getDisplayMetrics());
        SharedPreferences.Editor editor = this.getActivity().getSharedPreferences("Settings", Context.MODE_PRIVATE).edit();
        editor.putString("My Lang", lang);
        editor.apply();

    }
    public void loadLocale(){
        SharedPreferences prefs = this.getActivity().getSharedPreferences("Settings", Context.MODE_PRIVATE);
        String language = prefs.getString("My_Lang","");
        setLocale(language);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        Fragment fragment = null;
        Intent intent = null;

        switch (item.getItemId()) {
            case R.id.nav_profile:
                fragment = new HomeUserInfoFragment();
                break;
            case R.id.nav_logout:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getContext(), LoginActivity.class));
                break;
            case R.id.nav_language:
                showChangeLanguageDialog();
            case R.id.nav_settings:
                intent = new Intent(android.provider.Settings.ACTION_SETTINGS);
                startActivityForResult(intent, 1);
            default:
                fragment = new BoughtFragment();

        }

        if(fragment != null){
            FragmentTransaction ft = getChildFragmentManager().beginTransaction();
            ft.replace(R.id.fragment_container,fragment);
            ft.commit();
        }else
            startActivity(intent);

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
