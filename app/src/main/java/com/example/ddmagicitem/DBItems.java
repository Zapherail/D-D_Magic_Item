package com.example.ddmagicitem;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DBItems extends AppCompatActivity {

    private static final String TAG ="DBItems";

    RecyclerView recyclerView;
    ItemDB itemDB;
    Button home;

    private ArrayList<String> itemID;
    private ArrayList<String> itemName;
    private ArrayList<String> itemRarity;
    private ArrayList<String> itemType;
    private ArrayList<String> itemDesc;
    private ArrayList<String> itemCost;

    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_items);
    //setting recycler view
        recyclerView = findViewById(R.id.recyclerView);
        //setting widget
        home = findViewById(R.id.btnHome);
        
        //bringing database in
        itemDB = new ItemDB(DBItems.this);
        //ArrayList to save information from database
        itemID = new ArrayList<>();
        itemName = new ArrayList<>();
        itemRarity = new ArrayList<>();
        itemType = new ArrayList<>();
        itemDesc = new ArrayList<>();
        itemCost = new ArrayList<>();


        //calling method
        storeDataInArrays();
        //setting customAdapter class
        customAdapter = new CustomAdapter(DBItems.this, this, itemID, itemName, itemRarity,
                                            itemDesc, itemType, itemCost);
        //setting customAdapter to recyclerView adapter for information
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(DBItems.this));

        //setting home button to go back to MainActivity
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }//End of onCreate
    //Reading data from Database
    private void storeDataInArrays(){
        Log.d(TAG, "storeDataInArrays: Called");
        //calling in cursor to read from the database and calling readAllData() method from ItemDB class
        Cursor cursor = itemDB.readAllData();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                //getting information from database
                itemID.add(cursor.getString(0));
                itemName.add(cursor.getString(1));
                itemRarity.add(cursor.getString(2));
                itemType.add(cursor.getString(3));
                itemDesc.add(cursor.getString(4));
                itemCost.add(cursor.getString(5));
            }
        }
    }//End of storeDataInArrays
}
