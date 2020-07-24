package com.projectx.androidappdevelopment;

import android.app.ActionBar;
import android.app.usage.NetworkStats;
import android.app.usage.NetworkStatsManager;
import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

public class tab2 extends Fragment {

    private Button introButton, exampleButton, furtherReadingButton;
    private RelativeLayout exampleScroller;
    private ImageView bluetoothIndicator, wiFiIndicator, mobileDataIndicator;
    private TextView introText, furtherReadingTextView;
    public static boolean INTRO_ENABLED = true;
    public static boolean EXAMPLE_ENABLED = false;
    public static boolean FURTHER_READING_ENABLED = false;
    ActionBar toolbar;
    public tab2() {
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
        return inflater.inflate(R.layout.fragment_tab2, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        introButton = (Button) getView().findViewById(R.id.introButton2);
        introText = (TextView) getView().findViewById(R.id.introText2);
        exampleButton = (Button) getView().findViewById(R.id.exampleButton2);
        exampleScroller = (RelativeLayout) getView().findViewById(R.id.exampleLayout2);
        furtherReadingButton = (Button) getView().findViewById(R.id.furtherReadingB2);
        furtherReadingTextView = (TextView) getView().findViewById(R.id.furtherReadingText2);
        bluetoothIndicator = (ImageView) getView().findViewById(R.id.bluetoothIndicator);
        wiFiIndicator = (ImageView) getView().findViewById(R.id.wiFiIndicator);
        mobileDataIndicator = (ImageView) getView().findViewById(R.id.mobileDataIndicator);

        toolbar = getActivity().getActionBar();

        String stringTab1 = "BroadcastReceiver in Android";
        getActivity().setTitle(stringTab1 + "");
        getActivity().getWindow().setStatusBarColor(getResources().getColor(R.color.darkRed));
        getActivity().setTitleColor(getResources().getColor(R.color.red));

        IntentFilter bfilter = new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);
        getActivity().getApplicationContext().registerReceiver(broadcastReceiverForBluetooth, bfilter);

        IntentFilter wFilter = new IntentFilter(WifiManager.WIFI_STATE_CHANGED_ACTION);
        getActivity().getApplicationContext().registerReceiver(broadcastReceiverForWiFi, wFilter);

        IntentFilter mFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        getActivity().getApplicationContext().registerReceiver(broadcastReceiverForMobileData, mFilter);

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

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void furtherReadingBtnStateCheck(boolean b) {
        if (b) {
            furtherReadingButton.setBackground(getResources().getDrawable(R.drawable.br_inro_bg));
            furtherReadingTextView.setVisibility(View.VISIBLE);
            FURTHER_READING_ENABLED = true;
        } else {
            furtherReadingButton.setBackground(getResources().getDrawable(R.drawable.br_intro_btn_off));
            furtherReadingTextView.setVisibility(View.GONE);
            FURTHER_READING_ENABLED = false;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void exampleBtnStateCheck(boolean b) {
        if (b) {
            exampleButton.setBackground(getResources().getDrawable(R.drawable.br_inro_bg));
            exampleScroller.setVisibility(View.VISIBLE);
            EXAMPLE_ENABLED = true;
        } else {
            exampleButton.setBackground(getResources().getDrawable(R.drawable.br_intro_btn_off));
            exampleScroller.setVisibility(View.GONE);
            EXAMPLE_ENABLED = false;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void introBtnStateCheck(Boolean b) {
        if (b) {
            introButton.setBackground(getResources().getDrawable(R.drawable.br_inro_bg));
            introText.setVisibility(View.VISIBLE);
            INTRO_ENABLED = true;
        } else {
            introButton.setBackground(getResources().getDrawable(R.drawable.br_intro_btn_off));
            introText.setVisibility(View.GONE);
            INTRO_ENABLED = false;
        }
    }

    private void stateChecker() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getApplicationContext().getSystemService(getActivity().getApplicationContext().CONNECTIVITY_SERVICE);
        NetworkInfo mobileInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        boolean mobileConnected = mobileInfo.getState() == NetworkInfo.State.CONNECTED;
        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        WifiManager wifi = (WifiManager) getActivity().getApplicationContext().getSystemService(getActivity().getApplicationContext().WIFI_SERVICE);
        if (wifi.isWifiEnabled()) {
            wiFiIndicator.setImageDrawable(getResources().getDrawable(R.drawable.green_signal));
        } else {
            wiFiIndicator.setImageDrawable(getResources().getDrawable(R.drawable.red_signal));
        }
        if (mobileConnected) {
            mobileDataIndicator.setImageDrawable(getResources().getDrawable(R.drawable.green_signal));
        } else {
            mobileDataIndicator.setImageDrawable(getResources().getDrawable(R.drawable.red_signal));
        }
        if (mBluetoothAdapter == null) {// Device does not support Bluetooth
        } else if (!mBluetoothAdapter.isEnabled()) {
            bluetoothIndicator.setImageDrawable(getResources().getDrawable(R.drawable.red_signal));
        } else {
            bluetoothIndicator.setImageDrawable(getResources().getDrawable(R.drawable.green_signal));
        }
    }

    private final BroadcastReceiver broadcastReceiverForBluetooth = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
            if (mBluetoothAdapter == null) {// Device does not support Bluetooth
            } else if (!mBluetoothAdapter.isEnabled()) {
                bluetoothIndicator.setImageDrawable(getResources().getDrawable(R.drawable.red_signal));
            } else {
                bluetoothIndicator.setImageDrawable(getResources().getDrawable(R.drawable.green_signal));
            }
        }
    };
    private final BroadcastReceiver broadcastReceiverForWiFi = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            WifiManager wifi = (WifiManager) getActivity().getApplicationContext().getSystemService(Context.WIFI_SERVICE);
            if (wifi.isWifiEnabled()) {
                wiFiIndicator.setImageDrawable(getResources().getDrawable(R.drawable.green_signal));
            } else {
                wiFiIndicator.setImageDrawable(getResources().getDrawable(R.drawable.red_signal));
            }
        }
    };
    private final BroadcastReceiver broadcastReceiverForMobileData = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getApplicationContext().getSystemService(getActivity().getApplicationContext().CONNECTIVITY_SERVICE);
            NetworkInfo mobileInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            boolean mobileConnected = mobileInfo.getState() == NetworkInfo.State.CONNECTED;
            if (mobileConnected) {
                mobileDataIndicator.setImageDrawable(getResources().getDrawable(R.drawable.green_signal));
            } else {
                mobileDataIndicator.setImageDrawable(getResources().getDrawable(R.drawable.red_signal));
            }
        }
    };

    @Override
    public void onResume() {
        super.onResume();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        introBtnStateCheck(INTRO_ENABLED);
        exampleBtnStateCheck(EXAMPLE_ENABLED);
        furtherReadingBtnStateCheck(FURTHER_READING_ENABLED);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().getApplicationContext().unregisterReceiver(broadcastReceiverForBluetooth);
        getActivity().getApplicationContext().unregisterReceiver(broadcastReceiverForMobileData);
        getActivity().getApplicationContext().unregisterReceiver(broadcastReceiverForWiFi);
    }
}