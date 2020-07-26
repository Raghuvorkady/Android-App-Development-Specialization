package com.projectx.androidappdevelopment.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.projectx.androidappdevelopment.Classes.Contacts;
import com.projectx.androidappdevelopment.R;

import java.util.ArrayList;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyOwnHolder> {

    private Context context;
    private ArrayList<Contacts> contactsArrayList;

    //constructor used to set the variables context and contactsArrayList
    public MyRecyclerViewAdapter(Context context, ArrayList<Contacts> contactsArrayList) {
        this.context = context;
        this.contactsArrayList = contactsArrayList;
    }

    //used to inflate the RecyclerView
    @Override
    public MyOwnHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View myOwnView = layoutInflater.inflate(R.layout.my_individual_contact, parent, false);
        return new MyOwnHolder(myOwnView);
    }

    //used to set the content into the individual holder one by one
    @Override
    public void onBindViewHolder(MyOwnHolder holder, int position) {
        holder.contactNameView.setText(contactsArrayList.get(position).getName());
        holder.contactPhoneView.setText(contactsArrayList.get(position).getPhone());
        holder.contactEmailView.setText(contactsArrayList.get(position).getEmail());
    }

    //used to retrieve the total number of contacts present
    @Override
    public int getItemCount() {
        return contactsArrayList.size();
    }

    //used to refer the class object with the view
    public class MyOwnHolder extends RecyclerView.ViewHolder {
        TextView contactPhoneView, contactEmailView, contactNameView;

        public MyOwnHolder(View itemView) {
            super(itemView);
            contactNameView = (TextView) itemView.findViewById(R.id.contactNameView);
            contactPhoneView = (TextView) itemView.findViewById(R.id.contactPhoneView);
            contactEmailView = (TextView) itemView.findViewById(R.id.contactEmailView);
        }
    }
}
