package com.projectx.androidappdevelopment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    boolean isInActionMode = false;
    TextView toolbarTitle, toolbarSubtitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //if (tab1.toastSwitch.isChecked())
        toolbar = (Toolbar) findViewById(R.id.toolbarMain);
        toolbarTitle = (TextView)findViewById(R.id.textTitleToolBar);
        toolbarSubtitle = (TextView) findViewById(R.id.textSubtitleToolBar);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.toolbar_layout);
        toolbarTitle.setText("My Notes");
        toolbarSubtitle.setVisibility(View.GONE);


        /*TextView textView = getSupportActionBar().getCustomView().findViewById(R.id.toolbar_title);
        textView.setText("My Custom Title");*/
        {
            Toast.makeText(getApplicationContext(),"onCreate()",Toast.LENGTH_SHORT).show();
        }
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new tab1()).commit();
    }
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment selectedFragment = null;
                    switch (menuItem.getItemId()){
                        case R.id.page_1:selectedFragment = new tab1();
                            break;
                        case R.id.page_2: selectedFragment = new tab2();
                            break;
                        case R.id.page_3:selectedFragment = new tab3();
                            break;
                        case R.id.page_4:selectedFragment = new tab4();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();
                    return true;
                }
            };

   /*@Override
    protected void onStart() {
        super.onStart();
       //if (tab1.toastSwitch.isChecked())
       {
           Toast.makeText(getApplicationContext(),"onStart()",Toast.LENGTH_SHORT).show();
       }
    }

    @Override
    protected void onResume() {
        super.onResume();
        //if (tab1.toastSwitch.isChecked())
        {
            Toast.makeText(getApplicationContext(),"onResume()",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(getApplicationContext(),"onRestart()",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(getApplicationContext(),"onPause()",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(getApplicationContext(),"onStop()",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(getApplicationContext(),"onDestroy()",Toast.LENGTH_SHORT).show();
    }*/
}