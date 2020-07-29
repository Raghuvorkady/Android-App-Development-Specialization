package com.projectx.androidappdevelopment.ContentProviders;

import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.projectx.androidappdevelopment.R;

import static android.content.Context.INPUT_METHOD_SERVICE;

public class SaveContactsFragment extends Fragment {

    private RelativeLayout saveLayout;
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
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button contactSaveButton = (Button) getView().findViewById(R.id.contactSaveButton);
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
            //used to remove the focus from the EditText View
            contactName.clearFocus();
            contactPhone.clearFocus();
            contactEmail.clearFocus();
            //used to hide the keyboard
            try {
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(INPUT_METHOD_SERVICE);
                assert imm != null;
                imm.hideSoftInputFromWindow(saveLayout.getWindowToken(), 0);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    private View.OnClickListener saveContact = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String name = contactName.getText().toString();
            String phone = contactPhone.getText().toString();
            String email = contactEmail.getText().toString();
            // used to check for empty input fields
            //TODO check for proper name, phone, email
            if (name.length() == 0 || phone.length() == 0 || email.length() == 0) {
                Toast.makeText(getActivity().getApplicationContext(),
                        "Input fields cannot be empty", Toast.LENGTH_SHORT).show();
            } else {
                //getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
                //the below code is used to put name, phone, email into ContentValues object
                ContentValues values = new ContentValues();
                values.put(ContactsProvider.NAME, name);
                values.put(ContactsProvider.PHONE, phone);
                values.put(ContactsProvider.EMAIL, email);

                //used to insert values into the SQLite DB
                Uri uri = getActivity().getApplicationContext().getContentResolver().insert(ContactsProvider.CONTENT_URI, values);

                //your contact saved successfully
                assert uri != null;
                Toast.makeText(getActivity().getApplicationContext(), uri.toString(), Toast.LENGTH_LONG).show();

                //used to hide the keyboard after successful record insertion
                try {
                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(INPUT_METHOD_SERVICE);
                    assert imm != null;
                    imm.hideSoftInputFromWindow(saveLayout.getWindowToken(), 0);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //used to jump into the ViewContactsFragment after successful record insertion
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameForContacts, new ViewContactsFragment()).commit();
            }
        }
    };
}