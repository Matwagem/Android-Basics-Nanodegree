package com.example.android.newsapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Mathijs' on 30-Jun-16.
 */
public class NewsAdapter extends BaseAdapter {

    private Activity activity;
    private static ArrayList title,trail,url;
    private static LayoutInflater inflater = null;

    public NewsAdapter(Activity a, ArrayList b, ArrayList c, ArrayList d){
        activity = a;
        this.title = b;
        this.trail = c;
        this.url = d;

        inflater = (LayoutInflater) activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        if (convertView == null){
            vi = inflater.inflate(R.layout.list_item, null);
        }

        TextView title2 = (TextView) vi.findViewById(R.id.title); // title
        String author2 = title.get(position).toString();
        title2.setText(author2);

        TextView title22 = (TextView) vi.findViewById(R.id.trail); // notice
        String authorNew = trail.get(position).toString();
        title22.setText(authorNew);

        return vi;
    }

    @Override
    public int getCount() {
        return title.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
