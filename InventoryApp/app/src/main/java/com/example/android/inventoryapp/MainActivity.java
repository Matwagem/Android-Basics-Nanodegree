package com.example.android.inventoryapp;


import android.app.Activity;
import android.app.ListActivity;
import android.content.ContentProvider;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.content.CursorLoader;
import android.app.LoaderManager;
import android.content.Loader;
import android.support.v4.widget.CursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.support.v4.app.ListFragment;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.inventoryapp.contentprovider.ItemContentProvider;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends ListActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    private static final int ACTIVITY_CREATE = 0;
    private static final int ACTIVITY_EDIT = 1;
    private static final int DELETE_ID = Menu.FIRST + 1;
    // private Cursor cursor;
    private CustomItemAdapter adapter;
    private static Context context;
    private GoogleApiClient client;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MainActivity.context = getApplicationContext();
        Button addItem = (Button) findViewById(R.id.button);
        Button clear = (Button) findViewById(R.id.deleteButton);

        addItem.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                createTodo();
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                /*DbHelper helper = new DbHelper(context);
                helper.deleteDatabase();*/
            }
        });

        this.getListView().setDividerHeight(2);
        fillData();
        registerForContextMenu(getListView());
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


    private void createTodo() {
        Intent i = new Intent(this, SaveInfo.class);
        startActivity(i);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Intent i = new Intent(this, DetailView.class);
        Uri todoUri = Uri.parse(ItemContentProvider.CONTENT_URI + "/" + id);
        i.putExtra(ItemContentProvider.CONTENT_ITEM_TYPE, todoUri);

        startActivity(i);
    }

    public void ClickHandler(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Uri todoUri = Uri.parse(ItemContentProvider.CONTENT_URI + "/" + id);
        Log.d("uri logger", String.valueOf(todoUri));
    }

    private void fillData() {
        String[] from = new String[]{DbContract.Table1.COLUMN_NAME_COL1, DbContract.Table1.COLUMN_NAME_COL2, DbContract.Table1.COLUMN_NAME_COL3};
        int[] to = new int[]{R.id.nameVal, R.id.priceVal, R.id.quantityVal};
        getLoaderManager().initLoader(0, null, this);
        adapter = new CustomItemAdapter(this, R.layout.list_item, null, from,
                to, 0);

        setListAdapter(adapter);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection = {DbContract.Table1._ID, DbContract.Table1.COLUMN_NAME_COL1};
        CursorLoader cursorLoader = new CursorLoader(this,
                ItemContentProvider.CONTENT_URI, null, null, null, null);
        return cursorLoader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        adapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        adapter.swapCursor(null);
    }

    @Override
    public void onStart() {
        super.onStart();
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.android.inventoryapp/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.android.inventoryapp/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}





