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

    Context context;
    ArrayList<Contacts> contactsArrayList;

    public MyRecyclerViewAdapter(Context context, ArrayList<Contacts> contactsArrayList) {
        this.context = context;
        this.contactsArrayList = contactsArrayList;
    }

    @Override
    public MyOwnHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View myOwnView = layoutInflater.inflate(R.layout.my_individual_contact, parent, false);
        return new MyOwnHolder(myOwnView);
    }

    @Override
    public void onBindViewHolder(MyOwnHolder holder, int position) {
        holder.contactNameView.setText(contactsArrayList.get(position).getName());
        holder.contactPhoneView.setText(contactsArrayList.get(position).getPhone());
        holder.contactEmailView.setText(contactsArrayList.get(position).getEmail());
    }

    @Override
    public int getItemCount() {
        return contactsArrayList.size();
    }

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
