package com.example.ddmagicitem;

import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Scroller;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Result extends AppCompatActivity {
    private ImageView imgResult;
    private TextView txtName;
    private TextView txtRarity;
    private TextView txtDescription;
    private TextView txtCost;
    private Button btnMain;
    //array list to hold users results
    List<Item> item = new ArrayList<>();
    //customAdapter to work with results
    CustomAdapter customAdapter;

    //tag for Log
    private static final String TAG = "Result";

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_result);

        //bringing in widgets
        imgResult = findViewById(R.id.imgResult);
        txtName = findViewById(R.id.txtName);
        txtRarity = findViewById(R.id.txtRarity);
        txtDescription = findViewById(R.id.txtDescription);
        txtCost = findViewById(R.id.txtCost);
        btnMain = findViewById(R.id.btnResultMain);
        Intent logTest = getIntent();
        Log.d(TAG, logTest.getExtras().getString("type"));
        getResult();




        findViewById(R.id.btnResultMain).setOnClickListener((View v) ->{
            //moving user back to the MainActivity
            Intent move = new Intent(getBaseContext(), MainActivity.class);
            startActivity(move);
        });

    }
    public void getResult(){
        //getting Intent
        Intent intent = getIntent();
        //getting the strings and passing them into new strings
        String type = intent.getExtras().getString("type");
        String rarity = intent.getExtras().getString("rarity");
        //Log to check if the strings where moving correctly
        Log.d(TAG, intent.getExtras().getString("type"));
        Log.d(TAG, intent.getExtras().getString("rarity"));

        //bringing database in
        ItemDB itemDB = new ItemDB(Result.this);
        //calling getRandom function from database
        Cursor cursor = itemDB.getRandom(rarity, type);
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                //reading from the database and passing information from the database into strings
               String name = cursor.getString(1);
                rarity = cursor.getString(2);
                type = cursor.getString(3);
                String desc = cursor.getString(4);
                String cost = cursor.getString(5);

                displayResult(name, rarity, desc, cost, type);
            }
        }
    }//End of getResults

    public void displayResult(String name, String rarity, String description, String cost, String type){
        //formatting the name so the image can change
        String formatName = name.toLowerCase(Locale.ROOT);
        formatName = formatName.replaceAll("\\s", "_");

        Resources res = getResources();
        int resID = res.getIdentifier(formatName, "drawable", getPackageName());
        //try catch to handle event an image isn't found or it draws a user image
        try{
            imgResult.setImageResource(resID);
        }catch (java.lang.RuntimeException Resources$NotFoundException){
            switch (rarity){
                case "Common":
                    imgResult.setImageResource(R.drawable.common_item);
                    break;
                case "Uncommon":
                    imgResult.setImageResource(R.drawable.uncommon_item);
                    break;
                case "Rare":
                    imgResult.setImageResource(R.drawable.rare_item);
                    break;
                case "Very Rare":
                    imgResult.setImageResource(R.drawable.very_rare_item);
                    break;
                case "Legendary":
                    imgResult.setImageResource(R.drawable.hammer_of_thunderbolts);
            }
        }
        //setting the values for the database to the correct widget
        txtName.setText(name);
        txtRarity.setText(rarity);
        txtDescription.setText(description);
        //setting ability to scroll
        txtDescription.setScroller(new Scroller(this));
        txtDescription.setVerticalScrollBarEnabled(true);
        txtDescription.setMovementMethod(new ScrollingMovementMethod());
        txtCost.setText(cost);


        }
        //was being used to try and have a recycler view of previous items as well
/*
    public void fillItemList(String name, String type, String rarity, int resID, String cost){

        item.add(name);

        customAdapter = new CustomAdapter(this, resID, name, type, rarity, cost);
    }

 */
}
