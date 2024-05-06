package com.example.ddmagicitem;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AddPhoto extends AppCompatActivity {
    ImageView imgGallery;
    Button btnGallery;

    private final int GALLERY_REQ_CODE = 1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_photo);

        //setting resources
       // imgGallery = findViewById(R.id.userImage);
        btnGallery = findViewById(R.id.btnGallery);



        btnGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



            }
        });

    }//End of onCreate

}//End of class
