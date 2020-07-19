package com.projectx.androidappdevelopment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.TextView;

public class tab1 extends Fragment {

    private Button introButton, exampleButton, furtherReadingButton;
    private RelativeLayout exampleScroller;
    private TextView introText, furtherReadingTextView;
    public static Switch toastSwitch;
    public static boolean INTRO_ENABLED = true;
    public static boolean EXAMPLE_ENABLED = false;
    public static boolean FURTHER_READING_ENABLED = false;

    public tab1() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tab1, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        toastSwitch = (Switch) getView().findViewById(R.id.toastSwitch);
        introButton = (Button) getView().findViewById(R.id.introButton);
        introText = (TextView) getView().findViewById(R.id.introText);
        exampleButton = (Button) getView().findViewById(R.id.exampleButton);
        exampleScroller = (RelativeLayout) getView().findViewById(R.id.exampleLayout);
        furtherReadingButton = (Button) getView().findViewById(R.id.furtherReadingB);
        furtherReadingTextView = (TextView) getView().findViewById(R.id.furtherReadingText);

        introButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (INTRO_ENABLED == false) {
                    introText.setVisibility(View.VISIBLE);
                    INTRO_ENABLED = true;
                } else {
                    introText.setVisibility(View.GONE);
                    INTRO_ENABLED = false;
                }
            }
        });

        exampleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (EXAMPLE_ENABLED == false) {
                    exampleScroller.setVisibility(View.VISIBLE);
                    EXAMPLE_ENABLED = true;
                } else {
                    exampleScroller.setVisibility(View.GONE);
                    EXAMPLE_ENABLED = false;
                }
            }
        });

        furtherReadingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (FURTHER_READING_ENABLED == false) {
                    furtherReadingTextView.setVisibility(View.VISIBLE);
                    FURTHER_READING_ENABLED = true;
                } else {
                    furtherReadingTextView.setVisibility(View.GONE);
                    FURTHER_READING_ENABLED = false;
                }
            }
        });
    }
}