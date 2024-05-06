package com.example.ddmagicitem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class NotReady extends AppCompatActivity {
    private ImageView imgMimic;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.not_ready);

        //setting Images
        imgMimic = findViewById(R.id.mimicImage);
        imgMimic.setImageResource(R.drawable.mimic);



        //Click listener for button
        findViewById(R.id.btnMonsterMain).setOnClickListener((View v) ->{
            Intent intent = new Intent(getBaseContext(), MainActivity.class);
            startActivity(intent);
        });
    }
}
