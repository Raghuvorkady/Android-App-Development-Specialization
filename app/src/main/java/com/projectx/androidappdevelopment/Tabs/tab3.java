package com.projectx.androidappdevelopment.Tabs;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.projectx.androidappdevelopment.R;
import com.projectx.androidappdevelopment.Services.MyAlarmService;
import com.projectx.androidappdevelopment.Services.MyRingtoneService;

public class tab3 extends Fragment {

    private Button introButton, exampleButton, furtherReadingButton, ringtoneButton, alarmButton;
    private RelativeLayout exampleScroller;
    private TextView introText, furtherReadingTextView, ringtoneText, alarmText;
    private static boolean INTRO_ENABLED = true;
    private static boolean EXAMPLE_ENABLED = false;
    private static boolean FURTHER_READING_ENABLED = false;

    public tab3() {
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
        return inflater.inflate(R.layout.fragment_tab3, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        introButton = (Button) getView().findViewById(R.id.introButton3);
        introText = (TextView) getView().findViewById(R.id.introText3);
        exampleButton = (Button) getView().findViewById(R.id.exampleButton3);
        exampleScroller = (RelativeLayout) getView().findViewById(R.id.exampleLayout3);
        furtherReadingButton = (Button) getView().findViewById(R.id.furtherReadingB3);
        furtherReadingTextView = (TextView) getView().findViewById(R.id.furtherReadingText3);
        ringtoneButton = (Button) getView().findViewById(R.id.ringtoneButton);
        alarmButton = (Button) getView().findViewById(R.id.alarmButton);
        ringtoneText = (TextView) getView().findViewById(R.id.ringtoneText);
        alarmText = (TextView) getView().findViewById(R.id.alarmText);

        String stringTab1 = getString(R.string.tab3Name);
        getActivity().setTitle(stringTab1 + "");
        getActivity().getWindow().setStatusBarColor(getResources().getColor(R.color.darkYellow));
        getActivity().setTitleColor(getResources().getColor(R.color.yellow));

        ringtoneButton.setOnClickListener(startBtn);
        alarmButton.setOnClickListener(stopBtn);

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

    //method to play ringtone(start/stop service)
    private View.OnClickListener startBtn = new View.OnClickListener() {
        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public void onClick(View v) {
            if (!isMyServiceRunning(MyRingtoneService.class)) {
                getActivity().startService(new Intent(getActivity().getApplicationContext(), MyRingtoneService.class));
            } else {
                getActivity().stopService(new Intent(getActivity().getApplicationContext(), MyRingtoneService.class));
            }
            setRingtoneButton();
        }
    };

    //method to change the ui of ringtone textView
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void setRingtoneButton() {
        if (isMyServiceRunning(MyRingtoneService.class)) {
            ringtoneButton.setBackground(getResources().getDrawable(R.drawable.pause_button));
            ringtoneText.setTextColor(getResources().getColor(R.color.blue));

        } else {
            ringtoneButton.setBackground(getResources().getDrawable(R.drawable.play_button));
            ringtoneText.setTextColor(getResources().getColor(R.color.black));
        }
    }

    //method to play alarm(start/stop service)
    private View.OnClickListener stopBtn = new View.OnClickListener() {
        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public void onClick(View v) {
            if (!isMyServiceRunning(MyAlarmService.class)) {
                getActivity().startService(new Intent(getActivity().getApplicationContext(), MyAlarmService.class));
                alarmButton.setBackground(getResources().getDrawable(R.drawable.pause_button));
            } else {
                getActivity().stopService(new Intent(getActivity().getApplicationContext(), MyAlarmService.class));
                alarmButton.setBackground(getResources().getDrawable(R.drawable.play_button));
            }
            setAlarmButton();
        }
    };

    //method to change the ui of alarm textView
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void setAlarmButton() {
        if (isMyServiceRunning(MyAlarmService.class)) {
            alarmButton.setBackground(getResources().getDrawable(R.drawable.pause_button));
            alarmText.setTextColor(getResources().getColor(R.color.blue));
        } else {
            alarmButton.setBackground(getResources().getDrawable(R.drawable.play_button));
            alarmText.setTextColor(getResources().getColor(R.color.black));
        }
    }

    //method to check running state of services
    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getActivity().getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    //used to check the state(pressed or released) of further reading button
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void furtherReadingBtnStateCheck(boolean b) {
        if (b) {
            furtherReadingButton.setBackground(getResources().getDrawable(R.drawable.s_intro_bg));
            furtherReadingTextView.setVisibility(View.VISIBLE);
            FURTHER_READING_ENABLED = true;
        } else {
            furtherReadingButton.setBackground(getResources().getDrawable(R.drawable.s_intro_btn_off));
            furtherReadingTextView.setVisibility(View.GONE);
            FURTHER_READING_ENABLED = false;
        }
    }

    //used to check the state(pressed or released) of example button
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void exampleBtnStateCheck(boolean b) {
        if (b) {
            exampleButton.setBackground(getResources().getDrawable(R.drawable.s_intro_bg));
            exampleScroller.setVisibility(View.VISIBLE);
            EXAMPLE_ENABLED = true;
        } else {
            exampleButton.setBackground(getResources().getDrawable(R.drawable.s_intro_btn_off));
            exampleScroller.setVisibility(View.GONE);
            EXAMPLE_ENABLED = false;
        }
    }

    //used to check the state(pressed or released) of introduction button
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public boolean introBtnStateCheck(boolean b, Context appContext) {
        if (b) {
            introButton.setBackground(appContext.getResources().getDrawable(R.drawable.s_intro_bg));
            introText.setVisibility(View.VISIBLE);
            INTRO_ENABLED = true;
            return true;
        } else {
            introButton.setBackground(appContext.getResources().getDrawable(R.drawable.s_intro_btn_off));
            introText.setVisibility(View.GONE);
            INTRO_ENABLED = false;
            return false;
        }
    }

    //used to retrieve the last instance state
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        introBtnStateCheck(INTRO_ENABLED, getContext());
        exampleBtnStateCheck(EXAMPLE_ENABLED);
        furtherReadingBtnStateCheck(FURTHER_READING_ENABLED);
        setRingtoneButton();
        setAlarmButton();
    }
}