package com.projectx.androidappdevelopment.ContentProviders;

import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.projectx.androidappdevelopment.Adapters.MyRecyclerViewAdapter;
import com.projectx.androidappdevelopment.Classes.Contacts;
import com.projectx.androidappdevelopment.R;

import java.util.ArrayList;

public class ViewContactsFragment extends Fragment {

    private ArrayList<Contacts> contactsArrayList = new ArrayList<>();

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

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = (RecyclerView) getView().findViewById(R.id.recyclerView);

        //Cursor object is used to iterate through the database records
        Cursor cursor = getActivity().managedQuery(ContactsProvider.CONTENT_URI, null,
                null, null, "name");

        if (cursor.moveToFirst()) {
            do {
                int s1 = cursor.getInt(cursor.getColumnIndex(ContactsProvider._ID));

                Contacts contacts = new Contacts(cursor.getString(cursor.getColumnIndex(ContactsProvider.NAME)),
                        cursor.getString(cursor.getColumnIndex(ContactsProvider.PHONE)),
                        cursor.getString(cursor.getColumnIndex(ContactsProvider.EMAIL)));
                contactsArrayList.add(contacts);
            } while (cursor.moveToNext());
        }

        //creating object of MyRecyclerViewAdapter to inflate the contact record views into it
        MyRecyclerViewAdapter myRecyclerViewAdapter = new MyRecyclerViewAdapter(getActivity().getApplicationContext(), contactsArrayList);
        //using the MyRecyclerViewAdapter object as an adapter
        recyclerView.setAdapter(myRecyclerViewAdapter);
        //setting up the Layout for the RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
    }
}