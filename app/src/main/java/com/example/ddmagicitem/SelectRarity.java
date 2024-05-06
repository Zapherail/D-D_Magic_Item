package com.example.ddmagicitem;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SelectRarity extends AppCompatActivity {

    ImageButton imgBtnCommon, imgBtnUnCommon, imgBtnRare, imgBtnVeryRare;
    Button btnLegendary, btnMain;
    String type;


    //log d tag
    private static final String TAG = "SelectRarity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_rarity);
        Log.d(TAG, getIntent().getExtras().getString("type"));


        //setting image buttons
        imgBtnCommon = findViewById(R.id.imgBtnCommon);
        imgBtnCommon.setImageResource(R.drawable.common_item);
        imgBtnUnCommon = findViewById(R.id.imgBtnUnCommon);
        imgBtnUnCommon.setImageResource(R.drawable.uncommon_item);
        imgBtnRare = findViewById(R.id.imgBtnRare);
        imgBtnRare.setImageResource(R.drawable.rare_item);
        imgBtnVeryRare = findViewById(R.id.imgBtnVeryRare);
        imgBtnVeryRare.setImageResource(R.drawable.very_rare_item);

        //bringing in type from mainactivity
        String toMove = getIntent().getExtras().getString("type");

        //setting regular buttons
        btnLegendary = findViewById(R.id.btnLegendary);
        btnMain = findViewById(R.id.btnMain);
        //calling method and passing the intent string to it
        buttonControls(toMove);

        //setting Main Menu button to go back to MainActivity
        btnMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }//End of on create

    //Setting button controls
    private void buttonControls(String toMove){
        imgBtnCommon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type = "Common";
                getItem(type, toMove);
            }
        });
        imgBtnUnCommon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type = "Uncommon";
                getItem(type, toMove);
            }
        });
        imgBtnRare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type = "Rare";
                getItem(type, toMove);
            }
        });
        imgBtnVeryRare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type = "Very Rare";
                getItem(type, toMove);
            }
        });
        btnLegendary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type = "Legendary";
                //setting sound to play when button is pressed
                final MediaPlayer mp = MediaPlayer.create(SelectRarity.this, R.raw.heaven);
                mp.start();
                mp.setVolume(100, 100);
                getItem(type, toMove);
            }
        });
    }//End of buttonControls
    private void getItem(String rarity, String toMove){
        //bringing in intent to move to Result class
        Intent intent = new Intent(getBaseContext(), Result.class);
        //adding the type string from MainActivity and the rarity from this class to move to
        //result class
        intent.putExtra("type", toMove);
        intent.putExtra("rarity", rarity);
        //starting activity
        startActivity(intent);
    }//End of getItems
}//End of class
