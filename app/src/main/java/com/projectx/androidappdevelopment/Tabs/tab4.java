package com.projectx.androidappdevelopment.Tabs;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.projectx.androidappdevelopment.R;
import com.projectx.androidappdevelopment.ContentProviders.SaveContactsFragment;
import com.projectx.androidappdevelopment.ContentProviders.ViewContactsFragment;

public class tab4 extends Fragment {

    private Button introButton, exampleButton, furtherReadingButton, saveContactsTab, viewContactsTab;
    private RelativeLayout exampleScroller;
    private TextView introText, furtherReadingTextView;
    public static boolean INTRO_ENABLED = true;
    public static boolean EXAMPLE_ENABLED = false;
    public static boolean FURTHER_READING_ENABLED = false;
    public static boolean SAVE_CONTACT_ENABLED = false;

    public tab4() {
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
        return inflater.inflate(R.layout.fragment_tab4, container, false);
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        introButton = (Button) getView().findViewById(R.id.introButton4);
        introText = (TextView) getView().findViewById(R.id.introText4);
        exampleButton = (Button) getView().findViewById(R.id.exampleButton4);
        exampleScroller = (RelativeLayout) getView().findViewById(R.id.exampleLayout4);
        furtherReadingButton = (Button) getView().findViewById(R.id.furtherReadingB4);
        furtherReadingTextView = (TextView) getView().findViewById(R.id.furtherReadingText4);
        viewContactsTab = (Button) getView().findViewById(R.id.viewContactsTab);
        saveContactsTab = (Button) getView().findViewById(R.id.saveContactsTab);

        saveContactsTab.setBackground(getResources().getDrawable(R.drawable.cp_save_menu_selected));
        viewContactsTab.setBackground(getResources().getDrawable(R.drawable.cp_view_menu_unselected));

        initialiseFrame(SAVE_CONTACT_ENABLED);

        String stringTab1 = getString(R.string.tab4Name);
        getActivity().setTitle(stringTab1 + "");
        getActivity().getWindow().setStatusBarColor(getResources().getColor(R.color.darkGreen));
        getActivity().setTitleColor(getResources().getColor(R.color.green));

        viewContactsTab.setOnClickListener(viewContactsTabObject);
        saveContactsTab.setOnClickListener(saveContactsTabObject);

        introButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                introBtnStateCheck(!INTRO_ENABLED);
            }
        });

        exampleButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                exampleBtnStateCheck(!EXAMPLE_ENABLED);
            }
        });

        furtherReadingButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                furtherReadingBtnStateCheck(!FURTHER_READING_ENABLED);
            }
        });
    }

    private void initialiseFrame(Boolean b) {
        //TODO check for clicked button

        //if (saveContactsTab.getBackground() != null && saveContactsTab.getBackground().getConstantState().equals(getResources().getDrawable(R.drawable.cp_save_menu_selected).getConstantState())) {
        if (b){
            callSaveContactsFragment();
            SAVE_CONTACT_ENABLED = false;
        } else {
            callViewContactsFragment();
            SAVE_CONTACT_ENABLED = true;
        }

    }

    private View.OnClickListener viewContactsTabObject = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            callViewContactsFragment();
        }
    };

    @SuppressLint("NewApi")
    private void callViewContactsFragment() {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameForContacts, new ViewContactsFragment()).commit();
        viewContactsTab.setBackground(getActivity().getResources().getDrawable(R.drawable.cp_view_menu_selected));
        saveContactsTab.setBackground(getActivity().getResources().getDrawable(R.drawable.cp_save_menu_unselected));
    }

    private View.OnClickListener saveContactsTabObject = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            callSaveContactsFragment();
        }
    };

    @SuppressLint("NewApi")
    private void callSaveContactsFragment() {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameForContacts, new SaveContactsFragment()).commit();
        saveContactsTab.setBackground(getActivity().getResources().getDrawable(R.drawable.cp_save_menu_selected));
        viewContactsTab.setBackground(getActivity().getResources().getDrawable(R.drawable.cp_view_menu_unselected));
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void furtherReadingBtnStateCheck(boolean b) {
        if (b) {
            furtherReadingButton.setBackground(getResources().getDrawable(R.drawable.cp_intro_bg));
            furtherReadingTextView.setVisibility(View.VISIBLE);
            FURTHER_READING_ENABLED = true;
        } else {
            furtherReadingButton.setBackground(getResources().getDrawable(R.drawable.cp_intro_btn_off));
            furtherReadingTextView.setVisibility(View.GONE);
            FURTHER_READING_ENABLED = false;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void exampleBtnStateCheck(boolean b) {
        if (b) {
            exampleButton.setBackground(getResources().getDrawable(R.drawable.cp_intro_bg));
            exampleScroller.setVisibility(View.VISIBLE);
            EXAMPLE_ENABLED = true;
        } else {
            exampleButton.setBackground(getResources().getDrawable(R.drawable.cp_intro_btn_off));
            exampleScroller.setVisibility(View.GONE);
            EXAMPLE_ENABLED = false;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void introBtnStateCheck(boolean b) {
        if (b) {
            introButton.setBackground(getResources().getDrawable(R.drawable.cp_intro_bg));
            introText.setVisibility(View.VISIBLE);
            INTRO_ENABLED = true;
        } else {
            introButton.setBackground(getResources().getDrawable(R.drawable.cp_intro_btn_off));
            introText.setVisibility(View.GONE);
            INTRO_ENABLED = false;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        introBtnStateCheck(INTRO_ENABLED);
        exampleBtnStateCheck(EXAMPLE_ENABLED);
        furtherReadingBtnStateCheck(FURTHER_READING_ENABLED);
        initialiseFrame(SAVE_CONTACT_ENABLED);
    }
}