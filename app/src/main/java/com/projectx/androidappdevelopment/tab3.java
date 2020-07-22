package com.projectx.androidappdevelopment;

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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link tab3#newInstance} factory method to
 * create an instance of this fragment.
 */
public class tab3 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Button introButton, exampleButton, furtherReadingButton;
    private RelativeLayout exampleScroller;
    private TextView introText, furtherReadingTextView;
    public static boolean INTRO_ENABLED = true;
    public static boolean EXAMPLE_ENABLED = false;
    public static boolean FURTHER_READING_ENABLED = false;
    public tab3() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment tab3.
     */
    // TODO: Rename and change types and number of parameters
    public static tab3 newInstance(String param1, String param2) {
        tab3 fragment = new tab3();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tab3, container, false);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        introButton = (Button) getView().findViewById(R.id.introButton3);
        introText = (TextView) getView().findViewById(R.id.introText3);
        exampleButton = (Button) getView().findViewById(R.id.exampleButton3);
        exampleScroller = (RelativeLayout) getView().findViewById(R.id.exampleLayout3);
        furtherReadingButton = (Button) getView().findViewById(R.id.furtherReadingB3);
        furtherReadingTextView = (TextView) getView().findViewById(R.id.furtherReadingText3);

        String stringTab1 = "Services in Android";
        getActivity().setTitle(stringTab1 + "");

        exampleScroller.setVisibility(View.GONE);
        furtherReadingTextView.setVisibility(View.GONE);

        introButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                if (INTRO_ENABLED == false) {
                    introButton.setBackground(getResources().getDrawable(R.drawable.s_intro_bg));
                    introText.setVisibility(View.VISIBLE);
                    INTRO_ENABLED = true;
                } else {
                    introButton.setBackground(getResources().getDrawable(R.drawable.s_intro_btn_off));
                    introText.setVisibility(View.GONE);
                    INTRO_ENABLED = false;
                }
            }
        });

        exampleButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                if (EXAMPLE_ENABLED == false) {
                    exampleButton.setBackground(getResources().getDrawable(R.drawable.s_intro_bg));
                    exampleScroller.setVisibility(View.VISIBLE);
                    EXAMPLE_ENABLED = true;
                } else {
                    exampleButton.setBackground(getResources().getDrawable(R.drawable.s_intro_btn_off));
                    exampleScroller.setVisibility(View.GONE);
                    EXAMPLE_ENABLED = false;
                }
            }
        });

        furtherReadingButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                if (FURTHER_READING_ENABLED == false) {
                    furtherReadingButton.setBackground(getResources().getDrawable(R.drawable.s_intro_btn_off));
                    furtherReadingTextView.setVisibility(View.VISIBLE);
                    FURTHER_READING_ENABLED = true;
                } else {
                    furtherReadingButton.setBackground(getResources().getDrawable(R.drawable.s_intro_btn_off));
                    furtherReadingTextView.setVisibility(View.GONE);
                    FURTHER_READING_ENABLED = false;
                }
            }
        });
    }
}