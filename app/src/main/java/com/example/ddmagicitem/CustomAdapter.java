package com.example.ddmagicitem;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList<String> itemID;
    private ArrayList<String> itemName;
    private ArrayList<String> itemRarity;
    private ArrayList<String> itemType;
    private ArrayList<String> itemDesc;
    private ArrayList<String> itemCost;


    CustomAdapter(Activity activity,
                  Context context,
                  ArrayList itemID,
                  ArrayList itemName,
                  ArrayList itemRarity,
                  ArrayList itemDesc,
                  ArrayList itemType,
                  ArrayList itemCost){
        this.activity = activity;
        this.context = context;
        this.itemID = itemID;
        this.itemName = itemName;
        this.itemRarity = itemRarity;
        this.itemDesc = itemDesc;
        this.itemType = itemType;
        this.itemCost = itemCost;



    }

    @NonNull
    @Override
    public CustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);

        return new MyViewHolder(view);

    }//end of OnCreateViewHolder


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.txtItemID.setText(String.valueOf(itemID.get(position)));
        holder.txtItemName.setText(String.valueOf(itemName.get(position)));
        holder.txtItemRarity.setText(String.valueOf(itemRarity.get(position)));

        holder.txtItemType.setText(String.valueOf(itemType.get(position)));

    }

    @Override
    public int getItemCount() {
        return itemName.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txtItemID, txtItemName, txtItemRarity, txtItemType;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtItemID = itemView.findViewById(R.id.txt_ItemID);
            txtItemName = itemView.findViewById(R.id.txt_ItemName);
            txtItemRarity = itemView.findViewById(R.id.txt_ItemType);
            txtItemType = itemView.findViewById(R.id.txt_ItemRarity);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
