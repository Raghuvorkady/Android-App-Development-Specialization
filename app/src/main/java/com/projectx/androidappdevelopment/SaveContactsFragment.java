package com.projectx.androidappdevelopment;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SaveContactsFragment extends Fragment {

    private Button contactSaveButton;

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

        contactSaveButton = (Button) getView().findViewById(R.id.contactSaveButton);

        contactSaveButton.setOnClickListener(saveContact);

    }
    private View.OnClickListener saveContact = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // Add a new student record
            //TODO it does not check for empty strings
            ContentValues values = new ContentValues();
            values.put(ContactsProvider.NAME, ((EditText) getView().findViewById(R.id.contactName)).getText().toString());
            values.put(ContactsProvider.PHONE, ((EditText) getView().findViewById(R.id.contactPhone)).getText().toString());
            values.put(ContactsProvider.EMAIL, ((EditText) getView().findViewById(R.id.contactEmail)).getText().toString());

            Uri uri = getActivity().getApplicationContext().getContentResolver().insert(ContactsProvider.CONTENT_URI, values);

            //your contact saved successfully
            Toast.makeText(getActivity().getApplicationContext(), uri.toString(), Toast.LENGTH_LONG).show();

            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameForContacts, new ViewContactsFragment()).commit();
        }
    };
}