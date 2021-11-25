package com.example.taxio;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//
//        return inflater.inflate(R.layout.fragment_home, container, false);
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home,
                container, false);

        //What is ITR
        RelativeLayout whatisITR = (RelativeLayout) rootView.findViewById(R.id.whatisITR);
        whatisITR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                whatisITR();
            }
        });


        //Tax Calculator
        RelativeLayout calculateTax = (RelativeLayout) rootView.findViewById(R.id.calculateTax);
        calculateTax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateTax();
            }
        });

        //File ITR
        RelativeLayout fileITR = (RelativeLayout) rootView.findViewById(R.id.fileITR);
        fileITR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fileITR();
            }
        });

        //Find CAs
        RelativeLayout findCAs = (RelativeLayout) rootView.findViewById(R.id.findCAs);
        findCAs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findCAs();
            }
        });

        //Download ITR forms
        RelativeLayout downloadForms = (RelativeLayout) rootView.findViewById(R.id.downloadForms);
        downloadForms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                downloadForms();
            }
        });

        //HRA Exemption
        RelativeLayout hraExemption = (RelativeLayout) rootView.findViewById(R.id.HRAExemption);
        hraExemption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HRAExemption();
            }
        });

        drawerLayout=(DrawerLayout) rootView.findViewById(R.id.drawer_layout);
        NavigationView navigationView=(NavigationView) rootView.findViewById(R.id.nav_view);
        Toolbar toolbar = (Toolbar) rootView.findViewById(R.id.toolbar);

        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(getActivity(),drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        return rootView;

    }

    public void whatisITR(){
        Intent intent = new Intent(getActivity(),WhatIsITR.class);
        startActivity(intent);
    }

    public void calculateTax(){
        Intent intent = new Intent(getActivity(),CalculateTaxAct.class);
        startActivity(intent);
    }

    public void fileITR(){
        Intent intent = new Intent(getActivity(),FileITR.class);
        startActivity(intent);
    }

    public void findCAs(){
        Intent intent = new Intent(getActivity(),FindCAs.class);
        startActivity(intent);
    }

    public void downloadForms(){
        Intent intent = new Intent(getActivity(),DownloadForms.class);
        startActivity(intent);
    }

    public void HRAExemption(){
        Intent intent = new Intent(getActivity(),HRAExemption.class);
        startActivity(intent);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_home:
                break;
            case R.id.nav_settings:
                Toast.makeText(getActivity(), "Clicked Settings!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_logout:
                startActivity(new Intent(getActivity(),Login.class));
                break;
            case R.id.nav_website:
                try {
                    Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://incometaxindia.gov.in/Pages/default.aspx"));
                    startActivity(myIntent);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(getActivity(), "No application can handle this request."
                            + " Please install a web browser",  Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            case R.id.nav_aboutus:
                startActivity(new Intent(getActivity(),AboutUs.class));
                break;
            case R.id.nav_rateus:
                Toast.makeText(getActivity(), "Thanks for rating us!", Toast.LENGTH_SHORT).show();
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return false;
    }
}