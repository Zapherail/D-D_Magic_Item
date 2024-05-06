package com.example.ddmagicitem;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ImageButton imgMain;
    private ImageButton imgWeapon;
    private ImageButton imgArmor;
    private ImageButton imgConsumable;
    private String type;
    private FloatingActionButton allItems;

    ItemDB ItemDB;
    //Tag for Logcat
    private static final String TAG = "Main Activity";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //bringing in sharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("D&DMagicItem", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();

        //bringing in database
        ItemDB = new ItemDB(MainActivity.this);

        //setting Images
        imgMain = findViewById(R.id.imgChest);
        imgMain.setImageResource(R.drawable.tresure_check_gray);

        //setting image buttons
        imgWeapon = findViewById(R.id.imgButtonSword);
        imgWeapon.setImageResource(R.drawable.sword_of_sharpness);
        imgArmor = findViewById(R.id.imgButtonArmor);
        imgArmor.setImageResource(R.drawable.bracers_of_defense);
        imgConsumable = findViewById(R.id.imgButtonDrink);
        imgConsumable.setImageResource(R.drawable.potion_of_healing);
        allItems = findViewById(R.id.fbtnAllItems);

        //onCLick Listener for three chest
        imgMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), AddItem.class);
                startActivity(intent);
            }
        });
        //setting onClick for floating action button
        allItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), DBItems.class);
                startActivity(intent);
            }
        });

        //Onclick listener for Sword
        findViewById(R.id.imgButtonSword).setOnClickListener((View v) ->{
             type = "Weapon";
             informationToNextPage(type);

        });

        //Onclick listener for Armor
        findViewById(R.id.imgButtonArmor).setOnClickListener((View v) -> {
            type = "Armor";
            informationToNextPage(type);
        });

        //OnClick listener for Consumable
        findViewById(R.id.imgButtonDrink).setOnClickListener((View v) ->{
            type = "Consumable";
            informationToNextPage(type);

            /*
            //used to reset database kept in for future use
            ItemDB itemDB = new ItemDB(this);
            itemDB.deleteAllData();
            Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show();

             */


        });


    }

    private void informationToNextPage(String type) {
        Log.d(TAG, "informationToNextPage: Called");
        //sending user choice to next page
        Intent intent = new Intent(getBaseContext(), SelectRarity.class);
        //saving user choice
        intent.putExtra("type", type);
        startActivity(intent);

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        //saving information on outState
        outState.putString("type", type);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        //restoring user choice
        this.type = savedInstanceState.getString("type");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        Log.d(TAG, "onOptionsItemSelected: Called");
        int id = item.getItemId();
        //sending user to action_settings menu
        if(id == R.id.action_settings){
            DialogSettingsMenu settings = new DialogSettingsMenu();
            settings.show(getSupportFragmentManager(), "123");

        }
        return true;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        Log.d(TAG, "onCreateOptionsMenu: Called");
        //set for the about me section
        getMenuInflater().inflate(R.menu.settings_menu, menu);
        return true;
    }
}//End of program