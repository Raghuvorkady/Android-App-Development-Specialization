package com.projectx.androidappdevelopment.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.projectx.androidappdevelopment.R;
import com.projectx.androidappdevelopment.Tabs.tab1;
import com.projectx.androidappdevelopment.Tabs.tab2;
import com.projectx.androidappdevelopment.Tabs.tab3;
import com.projectx.androidappdevelopment.Tabs.tab4;

public class FragmentContainer extends Fragment {

    public FragmentContainer() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_container, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        BottomNavigationView bottomNavigationView = (BottomNavigationView) getView().findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new tab1()).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment selectedFragment = null;
                    switch (menuItem.getItemId()) {
                        case R.id.page_1:
                            selectedFragment = new tab1();
                            break;
                        case R.id.page_2:
                            selectedFragment = new tab2();
                            break;
                        case R.id.page_3:
                            selectedFragment = new tab3();
                            break;
                        case R.id.page_4:
                            selectedFragment = new tab4();
                            break;
                    }
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
                    return true;
                }
            };
}