package com.example.android.inventoryapp.contentprovider;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

import com.example.android.inventoryapp.DatabaseHelper;
import com.example.android.inventoryapp.DbContract;

import java.util.Arrays;
import java.util.HashSet;

/**
 * Created by Mathijs' on 05-Jul-16.
 */
public class ItemContentProvider extends android.content.ContentProvider{

    // database
    public DatabaseHelper database;
    public static DatabaseHelper dbh;

    private static final int ITEMS = 10;
    private static final int ITEM_ID = 20;
    private static final String AUTHORITY = "com.example.android.inventoryapp";
    private static final String BASE_PATH = "items";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY
            + "/" + BASE_PATH);

    public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE
            + "/items";
    public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE
            + "/item";

    private static final UriMatcher sURIMatcher = new UriMatcher(
            UriMatcher.NO_MATCH);
    static {
        sURIMatcher.addURI(AUTHORITY, BASE_PATH, ITEMS);
        sURIMatcher.addURI(AUTHORITY, BASE_PATH + "/#", ITEM_ID);
    }

    @Override
    public boolean onCreate() {
        database = new DatabaseHelper(getContext());
        dbh = new DatabaseHelper(getContext());
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {

        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        checkColumns(projection);
        queryBuilder.setTables(DbContract.Table1.TABLE_NAME);

        int uriType = sURIMatcher.match(uri);
        switch (uriType) {
            case ITEMS:
                break;
            case ITEM_ID:
                // adding the ID to the original query
                queryBuilder.appendWhere(DbContract.Table1._ID + "="
                        + uri.getLastPathSegment());
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }

        SQLiteDatabase db = database.getWritableDatabase();
        Cursor cursor = queryBuilder.query(db, projection, selection,
                selectionArgs, null, null, sortOrder);
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    public static Boolean updateDatabase(String id){
        SQLiteDatabase db = dbh.getWritableDatabase();
        ContentValues data=new ContentValues();
        String quantity = "0";
        data.put(DbContract.Table1.COLUMN_NAME_COL3, quantity);
        long result = db.update(DbContract.Table1.TABLE_NAME, data, "_id=" + id, null);

        if(result == -1){
            return false;
        }else{
            return true;
        }
    }

    public static Boolean updateDatabaseMinus(String id){
        SQLiteDatabase db = dbh.getWritableDatabase();
        ContentValues data=new ContentValues();
        String tempQuantity = "5";
        int quantity = Integer.parseInt(tempQuantity);
        quantity-=1;
        String newQuantity = String.valueOf(quantity);
        data.put(DbContract.Table1.COLUMN_NAME_COL3, newQuantity);
        long result = db.update(DbContract.Table1.TABLE_NAME, data, "_id=" + id, null);

        if(result == -1){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        int uriType = sURIMatcher.match(uri);
        SQLiteDatabase sqlDB = database.getWritableDatabase();
        long id = 0;
        switch (uriType) {
            case ITEMS:
                id = sqlDB.insert(DbContract.Table1.TABLE_NAME, null, values);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return Uri.parse(BASE_PATH + "/" + id);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int uriType = sURIMatcher.match(uri);
        SQLiteDatabase sqlDB = database.getWritableDatabase();
        int rowsDeleted = 0;
        switch (uriType) {
            case ITEMS:
                rowsDeleted = sqlDB.delete(DbContract.Table1.TABLE_NAME, selection,
                        selectionArgs);
                break;
            case ITEM_ID:
                String id = uri.getLastPathSegment();
                if (TextUtils.isEmpty(selection)) {
                    rowsDeleted = sqlDB.delete(
                            DbContract.Table1.TABLE_NAME,
                            DbContract.Table1._ID + "=" + id,
                            null);
                } else {
                    rowsDeleted = sqlDB.delete(
                            DbContract.Table1.TABLE_NAME,
                            DbContract.Table1._ID + "=" + id
                                    + " and " + selection,
                            selectionArgs);
                }
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return rowsDeleted;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {

        int uriType = sURIMatcher.match(uri);
        SQLiteDatabase sqlDB = database.getWritableDatabase();
        int rowsUpdated = 0;
        switch (uriType) {
            case ITEMS:
                rowsUpdated = sqlDB.update(DbContract.Table1.TABLE_NAME,
                        values,
                        selection,
                        selectionArgs);
                break;
            case ITEM_ID:
                String id = uri.getLastPathSegment();
                if (TextUtils.isEmpty(selection)) {
                    rowsUpdated = sqlDB.update(DbContract.Table1.TABLE_NAME,
                            values,
                            DbContract.Table1._ID + "=" + id,
                            null);
                } else {
                    rowsUpdated = sqlDB.update(DbContract.Table1.TABLE_NAME,
                            values,
                            DbContract.Table1._ID + "=" + id
                                    + " and "
                                    + selection,
                            selectionArgs);
                }
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return rowsUpdated;
    }

    private void checkColumns(String[] projection) {
        String[] available = { DbContract.Table1.COLUMN_NAME_COL1,
                DbContract.Table1.COLUMN_NAME_COL2, DbContract.Table1.COLUMN_NAME_COL3,
                DbContract.Table1._ID };
        if (projection != null) {
            HashSet<String> requestedColumns = new HashSet<String>(
                    Arrays.asList(projection));
            HashSet<String> availableColumns = new HashSet<String>(
                    Arrays.asList(available));
            // check if all columns which are requested are available
            if (!availableColumns.containsAll(requestedColumns)) {
                throw new IllegalArgumentException(
                        "Unknown columns in projection");
            }
        }
    }

}

