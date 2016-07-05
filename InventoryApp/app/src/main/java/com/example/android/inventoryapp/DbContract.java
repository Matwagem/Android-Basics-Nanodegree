package com.example.android.inventoryapp;

import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.util.Log;

/**
 * Created by Mathijs' on 03-Jul-16.
 */
public final class DbContract {
    public static final  int    DATABASE_VERSION   = 1;
    public static final  String DATABASE_NAME      = "database.db";
    private static final String TEXT_TYPE          = " TEXT";
    private static final String COMMA_SEP          = ",";

    private DbContract() {}

    public static abstract class Table1 implements BaseColumns {
        public static final String TABLE_NAME       = "InventoryTracker";
        public static final String COLUMN_NAME_COL1 = "Name";
        public static final String COLUMN_NAME_COL2 = "Price";
        public static final String COLUMN_NAME_COL3 = "Quantity";

        public static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+
                "(" + _ID+ " INTEGER PRIMARY KEY,"+ COLUMN_NAME_COL1 + TEXT_TYPE + COMMA_SEP +
                COLUMN_NAME_COL2 + TEXT_TYPE + COMMA_SEP +
                COLUMN_NAME_COL3 + TEXT_TYPE + ")";

        public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

        public static void onCreate(SQLiteDatabase database) {
            database.execSQL(CREATE_TABLE);
        }

        public static void onUpgrade(SQLiteDatabase database, int oldVersion,
                                     int newVersion) {
            Log.w(Table1.class.getName(), "Upgrading database from version "
                    + oldVersion + " to " + newVersion
                    + ", which will destroy all old data");
            database.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(database);
        }
    }
}