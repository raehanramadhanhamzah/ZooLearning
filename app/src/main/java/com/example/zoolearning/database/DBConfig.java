package com.example.zoolearning.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DBConfig extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "my_database";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "animals";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_SPECIES = "species";
    private static final String COLUMN_FAMILY = "family";
    private static final String COLUMN_HABITAT = "habitat";
    private static final String COLUMN_PLACE_OF_FOUND = "place_of_found";
    private static final String COLUMN_DIET = "diet";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_WEIGHT_KG = "weight_kg";
    private static final String COLUMN_HEIGHT_CM = "height_cm";

    public DBConfig(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // onCreate untuk membuat table
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE " + TABLE_NAME + " ("
                        + COLUMN_ID + " INTEGER PRIMARY KEY , "
                        + COLUMN_NAME + " TEXT, "
                        + COLUMN_SPECIES + " TEXT, "
                        + COLUMN_FAMILY + " TEXT, "
                        + COLUMN_HABITAT + " TEXT, "
                        + COLUMN_PLACE_OF_FOUND + " TEXT, "
                        + COLUMN_DIET + " TEXT, "
                        + COLUMN_DESCRIPTION + " TEXT, "
                        + COLUMN_WEIGHT_KG + " TEXT, "
                        + COLUMN_HEIGHT_CM + " TEXT)"
        );
    }

    // Create Data

    public void insertRecord(int id,String name, String species, String family, String habitat, String placeOfFound, String diet, String description, String weightKg, String heightCm) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID,id);
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_SPECIES, species);
        values.put(COLUMN_FAMILY, family);
        values.put(COLUMN_HABITAT, habitat);
        values.put(COLUMN_PLACE_OF_FOUND, placeOfFound);
        values.put(COLUMN_DIET, diet);
        values.put(COLUMN_DESCRIPTION, description);
        values.put(COLUMN_WEIGHT_KG, weightKg);
        values.put(COLUMN_HEIGHT_CM, heightCm);
        db.insert(TABLE_NAME, null, values);
    }

    // Get All Data - Cursor untuk mengambil data
    //getRecordById berguna untuk mengecek apakah data hewan tertentu sudah
    // ditambahakan kefavorit atau blm
    public Cursor getRecordById(int id){
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE id = ?", new String[]{String.valueOf(id)});
    }
    public Cursor getAllRecord() {
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }



    // Delete
    public void deleteRecord(int id) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
