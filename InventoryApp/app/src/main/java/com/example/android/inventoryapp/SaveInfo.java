package com.example.android.inventoryapp;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.android.inventoryapp.contentprovider.ItemContentProvider;

import java.sql.ResultSet;

/**
 * Created by Mathijs' on 03-Jul-16.
 */
public class SaveInfo extends MainActivity {
    EditText eName, ePrice, eQuantity;
    String name, price;
    String quantity;
    private EditText txName;
    private EditText txPrice;
    private EditText txQuant;

    private Uri todoUri;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.enter_info);

        txName = (EditText)findViewById(R.id.nameInput);
        txPrice = (EditText)findViewById(R.id.priceInput);
        txQuant = (EditText)findViewById(R.id.quantityInput);
        Button confirmButton = (Button) findViewById(R.id.saveButton);

        Bundle extras = getIntent().getExtras();

        todoUri = (bundle == null) ? null : (Uri) bundle
                .getParcelable(ItemContentProvider.CONTENT_ITEM_TYPE);

        if (extras != null) {
            todoUri = extras
                    .getParcelable(ItemContentProvider.CONTENT_ITEM_TYPE);

            fillData(todoUri);
        }

        confirmButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (TextUtils.isEmpty(txPrice.getText().toString())) {
                    makeToast();
                } else {
                    setResult(RESULT_OK);
                    finish();
                }
            }

        });
    }

    private void fillData(Uri uri) {
        String[] projection = {DbContract.Table1.COLUMN_NAME_COL2,
                DbContract.Table1.COLUMN_NAME_COL3, DbContract.Table1.COLUMN_NAME_COL1 };
        Cursor cursor = getContentResolver().query(uri, projection, null, null,
                null);
        if (cursor != null) {
            cursor.moveToFirst();
            txName.setText(cursor.getString(cursor
                    .getColumnIndexOrThrow(DbContract.Table1.COLUMN_NAME_COL1)));
            txPrice.setText(cursor.getString(cursor
                    .getColumnIndexOrThrow(DbContract.Table1.COLUMN_NAME_COL2)));
            txQuant.setText(cursor.getString(cursor
                    .getColumnIndexOrThrow(DbContract.Table1.COLUMN_NAME_COL3)));

            cursor.close();
        }
    }

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        saveState();
        outState.putParcelable(ItemContentProvider.CONTENT_ITEM_TYPE, todoUri);
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveState();
    }

    private void saveState() {
        String name = txName.getText().toString();
        String price = txPrice.getText().toString();
        String quantity = txQuant.getText().toString();

        if (price.length() == 0 || quantity.length() == 0 || name.length()==0) {
            return;
        }

        ContentValues values = new ContentValues();
        values.put(DbContract.Table1.COLUMN_NAME_COL1, name);
        values.put(DbContract.Table1.COLUMN_NAME_COL2, price);
        values.put(DbContract.Table1.COLUMN_NAME_COL3, quantity);

        if (todoUri == null) {
            todoUri = getContentResolver().insert(
                    ItemContentProvider.CONTENT_URI, values);
        } else {
            getContentResolver().update(todoUri, values, null, null);
        }
    }
    private void makeToast() {
        Toast.makeText(SaveInfo.this, "Please maintain a summary",
                Toast.LENGTH_LONG).show();
    }
}
