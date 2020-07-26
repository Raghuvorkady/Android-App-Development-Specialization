package com.projectx.androidappdevelopment.ContentProviders;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.projectx.androidappdevelopment.R;

import static android.content.Context.INPUT_METHOD_SERVICE;

public class SaveContactsFragment extends Fragment {

    private Button contactSaveButton;
    RelativeLayout saveLayout;
    //ScrollView scrollViewLayout;
    private EditText contactName, contactPhone, contactEmail;

    public SaveContactsFragment() {
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
        return inflater.inflate(R.layout.fragment_save_contacts, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //((AppCompatActivity) getActivity()).getSupportActionBar().hide();

        //scrollViewLayout = (ScrollView) getView().findViewById(R.id.scroll_view_layout);
        contactSaveButton = (Button) getView().findViewById(R.id.contactSaveButton);
        saveLayout = (RelativeLayout) getView().findViewById(R.id.saveLayout);
        contactName = ((EditText) getView().findViewById(R.id.contactName));
        contactPhone = ((EditText) getView().findViewById(R.id.contactPhone));
        contactEmail = ((EditText) getView().findViewById(R.id.contactEmail));

        contactSaveButton.setOnClickListener(saveContact);
        saveLayout.setOnClickListener(hideKeyboard);
    }

    private View.OnClickListener hideKeyboard = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            contactName.clearFocus();
            contactPhone.clearFocus();
            contactEmail.clearFocus();
            try {
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(INPUT_METHOD_SERVICE);
                assert imm != null;
                imm.hideSoftInputFromWindow(saveLayout.getWindowToken(), 0);
            } catch (Exception e) {
            }
        }
    };

    private View.OnClickListener saveContact = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // Add a new student record
            String name = contactName.getText().toString();
            String phone = contactPhone.getText().toString();
            String email = contactEmail.getText().toString();
            if (name.length() == 0 && phone.length() == 0 && email.length() == 0) {
                Toast.makeText(getActivity().getApplicationContext(), "Input fields cannot be empty", Toast.LENGTH_SHORT).show();
                return;
            } else {
                //getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
                ContentValues values = new ContentValues();
                values.put(ContactsProvider.NAME, name);
                values.put(ContactsProvider.PHONE, phone);
                values.put(ContactsProvider.EMAIL, email);

                Uri uri = getActivity().getApplicationContext().getContentResolver().insert(ContactsProvider.CONTENT_URI, values);

                //your contact saved successfully
                Toast.makeText(getActivity().getApplicationContext(), uri.toString(), Toast.LENGTH_LONG).show();

                try {
                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(saveLayout.getWindowToken(), 0);
                } catch (Exception e) {
                }
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameForContacts, new ViewContactsFragment()).commit();
            }
        }
    };
}