package com.example.android.inventoryapp;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.inventoryapp.contentprovider.ItemContentProvider;

/**
 * Created by Mathijs' on 05-Jul-16.
 */
public class CustomItemAdapter extends SimpleCursorAdapter{
    Context context;
    Activity activity;

    public CustomItemAdapter(Context context, int layout, Cursor c, String[] from, int[] to, int flags) {
        super(context, layout, c, from, to, flags);
        this.context=context;
        this.activity=(Activity) context;
    }
    public View newView(Context _context, Cursor _cursor, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) _context.getSystemService(_context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.list_item, parent, false);
        return view;
    }

    @Override
    public void bindView(View view, Context Context, Cursor cursor) {
        String name = cursor.getString(cursor.getColumnIndex(DbContract.Table1.COLUMN_NAME_COL1));
        String price = cursor.getString(cursor.getColumnIndex(DbContract.Table1.COLUMN_NAME_COL2));
        String quantity = cursor.getString(cursor.getColumnIndex(DbContract.Table1.COLUMN_NAME_COL3));
        TextView txName = (TextView) view.findViewById(R.id.nameVal);
        txName.setText(name);
        TextView txPrice = (TextView) view.findViewById(R.id.priceVal);
        txPrice.setText(price);
        TextView txQuant = (TextView) view.findViewById(R.id.quantityVal);
        txQuant.setText(quantity);
        Button saleButton = (Button) view.findViewById(R.id.sale);
        saleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view != null) {
                    Object obj = view.getTag();
                    String st = obj.toString();

                    //Log.i("Log_five", text1);
                    ItemContentProvider.updateDatabase(st);
                    notifyDataSetChanged();
                    //form.deleteForm(Long.valueOf(st).longValue());
                    Toast.makeText(context, "Delete row with id = " + st, Toast.LENGTH_LONG).show();

                }
            }
        });
        Object obj = cursor.getString(cursor.getColumnIndex(DbContract.Table1._ID));
        saleButton.setTag(obj);
    }

    static class ViewHolder {
        public Button btn;
    }

}
