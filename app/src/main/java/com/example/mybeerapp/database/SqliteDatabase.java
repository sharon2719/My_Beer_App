package com.example.mybeerapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.mybeerapp.model.Beer;
import com.example.mybeerapp.model.BeerList;

import java.util.ArrayList;
import java.util.List;

public class SqliteDatabase extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Beers";
    private static final String TABLE_NAME = "Beers";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "beerName";
    private static final String COLUMN_TAGLINE = "tagline";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_FIRST_BREWED = "_firstBrewed";
    public SqliteDatabase(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_BEER_TABLE = "CREATE TABLE "
                + TABLE_NAME + "("
                + COLUMN_ID
                + " INTEGER PRIMARY KEY,"
                + COLUMN_NAME + " TEXT,"
                + COLUMN_TAGLINE + " TEXT,"
                + COLUMN_DESCRIPTION + " TEXT,"
                + COLUMN_FIRST_BREWED + " TEXT "
                +")";
        sqLiteDatabase.execSQL(CREATE_BEER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

     public List<Beer> beersList(){
        String sql = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        List <Beer> storeBeer = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                int id = Integer.parseInt(cursor.getString(0));
                String beerName = cursor.getString(1);
                String tagline = cursor.getString(2);
                String description = cursor.getString(3);
                String firstBrewed = cursor.getString(4);
                storeBeer.add(new Beer(id,beerName, tagline, description, firstBrewed));
            }
            while ( cursor.moveToNext());


        }
            cursor.close();
            return  storeBeer;
        }

        public void addBeer(Beer beer) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_NAME, beer.getName());
            values.put(COLUMN_TAGLINE, beer.getTagline());
            values.put(COLUMN_DESCRIPTION, beer.getDescription());
            values.put(COLUMN_FIRST_BREWED, beer.getFirstBrewed());
            SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
            sqLiteDatabase.insert(TABLE_NAME, null ,values);
        }

        public void updateBeer(Beer beer){
            ContentValues values = new ContentValues();
            values.put(COLUMN_NAME, beer.getName());
            values.put(COLUMN_TAGLINE, beer.getTagline());
            values.put(COLUMN_DESCRIPTION, beer.getDescription());
            values.put(COLUMN_FIRST_BREWED, beer.getFirstBrewed());
            SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
            sqLiteDatabase.update(TABLE_NAME ,values,COLUMN_ID + " =?",new String[]{String.valueOf(beer.getId())});
        }
        public void deleteBeer( int id){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(TABLE_NAME,COLUMN_ID + "=?" ,new String[]{String.valueOf(id)});
        }
        public void insertBeerList(List<Beer> beerList){
            for (Beer beer: beerList
                 ) {
                addBeer(beer);
            }

        }

    }

