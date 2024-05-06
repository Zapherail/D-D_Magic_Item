package com.example.ddmagicitem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Locale;

public class UpdateActivity extends AppCompatActivity {

    EditText name, description, cost, rarity;
    ImageView img;
    Button update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        name = findViewById(R.id.editTxtName);
        rarity = findViewById(R.id.editTxtRarity);
        description = findViewById(R.id.editTxtDescription);
        cost = findViewById(R.id.editTxtCost);
        update = findViewById(R.id.btnUpdate);
        img = findViewById(R.id.updateImgResult);


        getIntentData();

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void getIntentData(){
        if(getIntent().hasExtra("desc") && getIntent().hasExtra("name") &&
            getIntent().hasExtra("rarity") && getIntent().hasExtra("cost")){
            String sName = getIntent().getStringExtra("name");
            String sRarity = getIntent().getStringExtra("rarity");
            String sDesc = getIntent().getStringExtra("desc");
            String sCost = getIntent().getStringExtra("cost");


            //sending data to be set
            settingUpdate(sName, sRarity, sDesc, sCost);
        }else{
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }
    }//end of getIntentData
    private void settingUpdate(String sName, String sRarity, String sDesc, String sCost){
        name.setText(sName);
        rarity.setText(sRarity);
        description.setText(sDesc);
        cost.setText(sCost);

        String formatName = sName.toLowerCase(Locale.ROOT);
        formatName = formatName.replaceAll("\\s", "_");

        Resources res = getResources();
        int resID = res.getIdentifier(formatName, "drawable", getPackageName());

        try{
            img.setImageResource(resID);
        }catch (java.lang.RuntimeException Resources$NotFoundException){
            img.setImageResource(R.drawable.very_rare_item);
        }
    }
}