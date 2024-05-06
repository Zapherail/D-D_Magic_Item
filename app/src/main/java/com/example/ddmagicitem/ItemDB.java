package com.example.ddmagicitem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class ItemDB extends SQLiteOpenHelper {
    private Context context;
    //setting database objects
    private static final String DATABASE_NAME = "MagicItems.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "Magic_Items";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "Name";
    private static final String COLUMN_RARITY = "Rarity";
    private static final String COLUMN_TYPE = "Type";
    private static final String COLUMN_DESC = "Description";
    private static final String COLUMN_COST = "Cost";

    public ItemDB(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //creating the database
        String query = "CREATE TABLE " + TABLE_NAME +
                        " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_NAME + " TEXT," +
                        COLUMN_RARITY + " TEXT," +
                        COLUMN_TYPE + " TEXT," +
                        COLUMN_DESC + " TEXT," +
                        COLUMN_COST + " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        //empty for now
    }

    void addItem(String name, String rarity, String type, String description, String cost){
        //calling in SQLiteDatabase and ContentValues to write to database
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        //adding user item to the database via ContentValues
        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_RARITY, rarity);
        cv.put(COLUMN_TYPE, type);
        cv.put(COLUMN_DESC, description);
        cv.put(COLUMN_COST, cost);
        db.insert(TABLE_NAME, null, cv);
        db.close();
    }
    Cursor getRandom(String rarity, String type){
        //query to get random item from the database based on user selections
        String query = "SELECT * FROM " + TABLE_NAME +
                        " WHERE Rarity = " + "'" + rarity + "'" +
                        " AND Type = " + "'" +  type +  "'"  +
                        " ORDER BY RANDOM()" + " LIMIT 1;";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            //cursor running the query
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }
    Cursor readAllData(){
        //getting all items in the database
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            //cursor reading all rows in the database
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }
    void deleteAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM  " + TABLE_NAME);
    }
}
