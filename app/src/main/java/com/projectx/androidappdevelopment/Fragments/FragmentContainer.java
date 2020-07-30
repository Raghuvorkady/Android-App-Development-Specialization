package com.projectx.androidappdevelopment.Fragments;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.Toolbar;
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
    private Toolbar toolbar;

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
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) getView().findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

        //used to create a default tab
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new tab1()).commit();
    }

    //used to switch between tabs
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                @Override
                public boolean onNavigationItemSelected(MenuItem menuItem) {
                    Fragment selectedFragment = null;
                    switch (menuItem.getItemId()) {
                        case R.id.page_1:
                            //toolbar.setBackground(getResources().getDrawable(R.color.tab1Title));
                            selectedFragment = new tab1();
                            break;
                        case R.id.page_2:
                            //toolbar.setBackground(getResources().getDrawable(R.color.tab2Title));
                            selectedFragment = new tab2();
                            break;
                        case R.id.page_3:
                            //toolbar.setBackground(getResources().getDrawable(R.color.tab3Title));
                            selectedFragment = new tab3();
                            break;
                        case R.id.page_4:
                            //toolbar.setBackground(getResources().getDrawable(R.color.tab4Title));
                            selectedFragment = new tab4();
                            break;
                    }
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();
                    return true;
                }
            };
}