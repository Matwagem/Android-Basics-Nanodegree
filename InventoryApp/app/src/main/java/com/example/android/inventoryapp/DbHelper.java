package com.example.android.inventoryapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.android.inventoryapp.DbContract;

public class DbHelper extends SQLiteOpenHelper {
    private String[] allColumns = { DbContract.Table1.COLUMN_NAME_COL1};

    public DbHelper(Context context) {
        super(context, DbContract.DATABASE_NAME, null, DbContract.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){db.execSQL(DbContract.Table1.CREATE_TABLE);}

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DbContract.Table1.DELETE_TABLE);
        onCreate(db);
    }

    public boolean updateType(String type, int selection){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues data=new ContentValues();
        data.put(DbContract.Table1.COLUMN_NAME_COL1, type);
        long result = db.update(DbContract.Table1.TABLE_NAME, data, null,  null);

        if(result == -1){
            return false;
        }else{
            return true;
        }
    }

    public void deleteEntries(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DbContract.Table1.TABLE_NAME, null, null);
    }

    public boolean insertData(String type, String freq){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DbContract.Table1.COLUMN_NAME_COL1, type);
        contentValues.put(DbContract.Table1.COLUMN_NAME_COL2, freq);
        long result = db.insert(DbContract.Table1.TABLE_NAME, null, contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }


}