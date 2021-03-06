package com.projectx.androidappdevelopment.Tabs;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.projectx.androidappdevelopment.R;

public class tab1 extends Fragment {

    private Button introButton, exampleButton, furtherReadingButton;
    private RelativeLayout exampleScroller;
    private TextView introText, furtherReadingTextView;
    private static boolean INTRO_ENABLED = true;
    private static boolean EXAMPLE_ENABLED = false;
    private static boolean FURTHER_READING_ENABLED = false;

    public tab1() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("onCreate()", "started");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Always call the superclass first
        Log.d("onCreateView()", "started");
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_tab1, container, false);
        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Log.d("onViewCreated()", "started");

        //toastSwitch = (Switch) getView().findViewById(R.id.toastSwitch);
        introButton = (Button) getView().findViewById(R.id.introButton);
        introText = (TextView) getView().findViewById(R.id.introText);
        exampleButton = (Button) getView().findViewById(R.id.exampleButton);
        exampleScroller = (RelativeLayout) getView().findViewById(R.id.exampleLayout);
        furtherReadingButton = (Button) getView().findViewById(R.id.furtherReadingB);
        furtherReadingTextView = (TextView) getView().findViewById(R.id.furtherReadingText);

        String stringTab1 = getString(R.string.tab1Name);
        getActivity().setTitle(stringTab1 + "");
        getActivity().getWindow().setStatusBarColor(getResources().getColor(R.color.darkBlue));

        introButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                introBtnStateCheck(!INTRO_ENABLED, getContext());
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

    //used to check the state(pressed or released) of further reading button
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void furtherReadingBtnStateCheck(Boolean b) {
        if (b) {
            furtherReadingButton.setBackground(getResources().getDrawable(R.drawable.activity_intro_bg));
            furtherReadingTextView.setVisibility(View.VISIBLE);
            FURTHER_READING_ENABLED = true;
        } else {
            furtherReadingButton.setBackground(getResources().getDrawable(R.drawable.activity_intro_btn_off));
            furtherReadingTextView.setVisibility(View.GONE);
            FURTHER_READING_ENABLED = false;
        }
    }

    //used to check the state(pressed or released) of example button
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void exampleBtnStateCheck(Boolean b) {
        if (b) {
            exampleButton.setBackground(getResources().getDrawable(R.drawable.activity_intro_bg));
            exampleScroller.setVisibility(View.VISIBLE);
            EXAMPLE_ENABLED = true;
        } else {
            exampleButton.setBackground(getResources().getDrawable(R.drawable.activity_intro_btn_off));
            exampleScroller.setVisibility(View.GONE);
            EXAMPLE_ENABLED = false;
        }
    }

    //used to check the state(pressed or released) of introduction button
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public boolean introBtnStateCheck(Boolean b, Context context) {
        if (b) {
            introButton.setBackground(context.getResources().getDrawable(R.drawable.activity_intro_bg));
            introText.setVisibility(View.VISIBLE);
            INTRO_ENABLED = true;
            return true;
        } else {
            introButton.setBackground(context.getResources().getDrawable(R.drawable.activity_intro_btn_off));
            introText.setVisibility(View.GONE);
            INTRO_ENABLED = false;
            return false;
        }
    }

    //used to save the instance state of buttons
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.d("onSaveInstanceState()", "started");
        savedInstanceState.putBoolean("INTRO_ENABLED_KEY", INTRO_ENABLED);
        savedInstanceState.putString("EXAMPLE_ENABLED_KEY", String.valueOf(EXAMPLE_ENABLED));
        savedInstanceState.putString("FURTHER_READING_ENABLED_KEY", String.valueOf(FURTHER_READING_ENABLED));
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //mActionBarProvider = (ActionBarProvider) context;
        Log.d("onAttach()", "started");

    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("onStart()", "started");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("onResume()", "started");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("onPause()", "started");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("onStop()", "started");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("onDestroyView()", "started");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("onDestroy()", "started");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d("onDetach()", "started");
    }

    //used to retrieve the last instance state
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("onActivityCreated()", "started");
        if (savedInstanceState != null) {
            INTRO_ENABLED = savedInstanceState.getBoolean("FURTHER_READING_ENABLED_TEST");
            EXAMPLE_ENABLED = savedInstanceState.getBoolean("EXAMPLE_ENABLED_KEY");
            FURTHER_READING_ENABLED = savedInstanceState.getBoolean("FURTHER_READING_ENABLED_KEY");
        }
    }

    //used to retrieve the last instance state
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        Log.d("onViewStateRestore()", "started");
        introBtnStateCheck(INTRO_ENABLED, getContext());
        exampleBtnStateCheck(EXAMPLE_ENABLED);
        furtherReadingBtnStateCheck(FURTHER_READING_ENABLED);
    }
}