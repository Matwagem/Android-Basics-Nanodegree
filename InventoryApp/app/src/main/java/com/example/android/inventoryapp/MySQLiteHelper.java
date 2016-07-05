package com.example.android.inventoryapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Mathijs' on 03-Jul-16.
 */
public class MySQLiteHelper extends SQLiteOpenHelper {
    private String[] allColumns = { DbContract.Table1.COLUMN_NAME_COL1};


    public MySQLiteHelper(Context context) {
        super(context, DbContract.DATABASE_NAME, null, DbContract.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DbContract.Table1.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DbContract.Table1.DELETE_TABLE);
        onCreate(db);
    }

    public void deleteDatabase(Context context, SQLiteDatabase db){
        context.deleteDatabase(DbContract.DATABASE_NAME);
    }

    public void deleteEntries(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DbContract.Table1.TABLE_NAME, null, null);
    }

    public boolean insertData(SQLiteDatabase db, String name, String price, String quantity){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DbContract.Table1.COLUMN_NAME_COL1, name);
        contentValues.put(DbContract.Table1.COLUMN_NAME_COL2, price);
        contentValues.put(DbContract.Table1.COLUMN_NAME_COL3, quantity);
        long result = db.insert(DbContract.Table1.TABLE_NAME, null, contentValues);
        Log.d("database operations", "DATA INSERTED");
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }
}