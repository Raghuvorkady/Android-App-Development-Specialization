package com.projectx.androidappdevelopment;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class ViewContactsFragment extends Fragment {

    private TextView contactPhoneView, contactEmailView, contactNameView;
    private ArrayList<Contacts> contactsArrayList = new ArrayList<>();

    RecyclerView recyclerView;
    MyOwnAdapter myOwnAdapter;

    private String s2 = new String();
    private String s3 = new String();
    private String s4 = new String();

    public ViewContactsFragment() {
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
        return inflater.inflate(R.layout.fragment_view_contacts, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();

        recyclerView = (RecyclerView) getView().findViewById(R.id.recyclerView);

        // Retrieve student records

        Cursor c = getActivity().managedQuery(ContactsProvider.CONTENT_URI, null, null, null, "name");

        if (c.moveToFirst()) {
            do {
                int s1 = c.getInt(c.getColumnIndex(ContactsProvider._ID));

                Contacts contacts = new Contacts(c.getString(c.getColumnIndex(ContactsProvider.NAME)),
                        c.getString(c.getColumnIndex(ContactsProvider.PHONE)),
                        c.getString(c.getColumnIndex(ContactsProvider.EMAIL)));
                contactsArrayList.add(contacts);
            } while (c.moveToNext());
        }

        myOwnAdapter = new MyOwnAdapter(getActivity().getApplicationContext(),contactsArrayList);
        recyclerView.setAdapter(myOwnAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
    }
}