package com.example.android.inventoryapp;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.inventoryapp.contentprovider.ItemContentProvider;

public class DetailView extends AppCompatActivity {
/*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);
    }
    */

    private TextView mCategory;
    private TextView mTitleText;
    private TextView mBodyText;

    private Uri todoUri;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_detail_view);
        Button orderButton = (Button) findViewById(R.id.orderButton);
        Button deleteButton = (Button) findViewById(R.id.deleteButton);

        mCategory = (TextView) findViewById(R.id.category);
        mTitleText = (TextView) findViewById(R.id.todo_edit_summary);
        mBodyText = (TextView) findViewById(R.id.todo_edit_description);

        Bundle extras = getIntent().getExtras();

        todoUri = (bundle == null) ? null : (Uri) bundle
                .getParcelable(ItemContentProvider.CONTENT_ITEM_TYPE);

        if (extras != null) {
            todoUri = extras
                    .getParcelable(ItemContentProvider.CONTENT_ITEM_TYPE);

            fillData(todoUri);
        }

        orderButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:9999999999"));
                startActivity(intent);
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
            String category = cursor.getString(cursor
                    .getColumnIndexOrThrow(DbContract.Table1.COLUMN_NAME_COL1));

            mCategory.setText(cursor.getString(cursor
                    .getColumnIndexOrThrow(DbContract.Table1.COLUMN_NAME_COL1)));
            mTitleText.setText(cursor.getString(cursor
                    .getColumnIndexOrThrow(DbContract.Table1.COLUMN_NAME_COL2)));
            mBodyText.setText(cursor.getString(cursor
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
        String category = mCategory.getText().toString();
        String summary = mTitleText.getText().toString();
        String description = mBodyText.getText().toString();
        if (description.length() == 0 && summary.length() == 0) {
            return;
        }

        ContentValues values = new ContentValues();
        values.put(DbContract.Table1.COLUMN_NAME_COL1, category);
        values.put(DbContract.Table1.COLUMN_NAME_COL2, summary);
        values.put(DbContract.Table1.COLUMN_NAME_COL3, description);

        if (todoUri == null) {
            // New todo
            todoUri = getContentResolver().insert(
                    ItemContentProvider.CONTENT_URI, values);
        } else {
            // Update todo
            getContentResolver().update(todoUri, values, null, null);
        }
    }

    private void makeToast() {
        Toast.makeText(DetailView.this, "Please maintain a summary",
                Toast.LENGTH_LONG).show();
    }
}

