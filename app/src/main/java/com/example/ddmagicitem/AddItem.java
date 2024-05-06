package com.example.ddmagicitem;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class AddItem extends AppCompatActivity {

    EditText name, desc, cost;
    RadioGroup rarityGroup1, rarityGroup2;
    RadioButton common, uncommon, rare, veryRare, legendary;
    Switch swWeapon, swArmor, swConsumable;
    //setting value for switch choice
    String swChoice = "";
    //setting value for radio button choice
    String rbChoice = "";
    Button addButton;
    Button btnCancel;
    FloatingActionButton fabtnAddPhoto;



    //Database
   private ItemDB itemDB;
    //Database objects
    ArrayList<String> itemName;
    ArrayList<String> itemRarity;
    ArrayList<String> itemType;
    ArrayList<String> itemDesc;
    ArrayList<String> itemCost;

    //Tag for Logcat
    private static final String TAG ="Add Item";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_item);

        //getting resources
        name = findViewById(R.id.txtAddName);
        rarityGroup1 = findViewById(R.id.rarityGroup1);
        rarityGroup2 = findViewById(R.id.rarityGroup2);
        swWeapon = findViewById(R.id.swWeapon);
        swArmor = findViewById(R.id.swArmor);
        swConsumable = findViewById(R.id.swConsumable);
        desc = findViewById(R.id.txtAddDes);
        cost = findViewById(R.id.txtAddCost);


        //Radio Buttons
        common = findViewById(R.id.rbCommon);
        uncommon = findViewById(R.id.rbUncommon);
        rare = findViewById(R.id.rbRare);
        veryRare = findViewById(R.id.rbVeryRare);
        legendary = findViewById(R.id.rbLegendary);



        //Buttons
        addButton = findViewById(R.id.btnAddItem);
        btnCancel = findViewById(R.id.btnCancel);

        //Bringing Database class into main
        itemDB = new ItemDB(AddItem.this);
        //Creating array to send to database
        itemName = new ArrayList<>();
        itemRarity = new ArrayList<>();
        itemType = new ArrayList<>();
        itemDesc = new ArrayList<>();
        itemCost = new ArrayList<>();

        //Control methods
        switchControls();



        //send information to database and user back to add photo.
        addButton.setOnClickListener((View v) ->{
            getDataFromForm();
           Intent intent = new Intent(getBaseContext(), MainActivity.class);
           startActivity(intent);

        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(AddItem.this, "No Item Added", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
            }
        });


    }//End of onCreate

    private void getDataFromForm(){
        Log.d(TAG, "getDataFromForm: Called");
        String itemName = name.getText().toString();
        String itemDesc = desc.getText().toString();
        String itemCost = cost.getText().toString();
        radioControls();
        itemDB.addItem(itemName, rbChoice, swChoice, itemDesc, itemCost);


        //Making a message to show item has been added in
        Toast.makeText(AddItem.this, "Item has been added", Toast.LENGTH_SHORT).show();


    }

    private void switchControls(){
        Log.d(TAG, "switchControls: Controlling switches");
        //setting switch controls
        swWeapon.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){
                    swArmor.setChecked(false);
                    swConsumable.setChecked(false);
                    swChoice = "Weapon";
                }
            }
        });
        swArmor.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){
                    swWeapon.setChecked(false);
                    swConsumable.setChecked(false);
                    swChoice = "Armor";
                }
            }
        });
        swConsumable.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){
                    swWeapon.setChecked(false);
                    swArmor.setChecked(false);
                    swChoice = "Consumable";
                }
            }
        });
    }//End of switchControls
    public void radioControls(){
        Log.d(TAG, "radioControls: Controlling RadioButtons");
       int selectID = rarityGroup1.getCheckedRadioButtonId();
        RadioButton radioButton = findViewById(selectID);
        if(selectID == -1){
            int selectID2 = rarityGroup2.getCheckedRadioButtonId();
            RadioButton radioButton2 = findViewById(selectID2);
            rbChoice = String.valueOf(radioButton2.getText());
        }else{
            rbChoice = String.valueOf(radioButton.getText());
        }


    }//End of radioControls
}//End of Class
