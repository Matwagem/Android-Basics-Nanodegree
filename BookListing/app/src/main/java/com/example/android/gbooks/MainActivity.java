package com.example.android.gbooks;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.xml.sax.Parser;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> title_array = new ArrayList<String>();
    ArrayList<String> author_array = new ArrayList<String>();
    ListView list;
    BookAdapter adapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setElevation(0);
        setContentView(R.layout.activity_main);
        list = (ListView) findViewById(R.id.tv_result);
        final Button button = (Button) findViewById(R.id.searchButton);
        button.setOnClickListener(new View.OnClickListener()   {
            @Override
            public void onClick(View v)  {
                EditText text = (EditText)findViewById(R.id.searchBar);
                String inputText = text.getText().toString();
                new FetchJSONData().execute(inputText);

                String strJson= FetchJSONData.gBooksResponse;
                String data = "";
                try {
                    JSONObject  jsonRootObject = new JSONObject(strJson);

                    JSONArray jsonArray = jsonRootObject.optJSONArray("items");

                    for(int i=0; i < jsonArray.length(); i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        JSONObject volumeInfo = jsonObject.getJSONObject("volumeInfo");
                        JSONArray authors = volumeInfo.getJSONArray("authors");
                        JSONArray isbn = volumeInfo.getJSONArray("industryIdentifiers");

                        title_array.add(volumeInfo.getString("title").toString());
                        String authorNames = volumeInfo.getString("authors");
                        authorNames = authorNames.replaceAll("\\[", "").replaceAll("\\]","");
                        authorNames = authorNames.replaceAll("\"", "").replaceAll("\"","");
                        author_array.add(authorNames);
                    }

                    adapter = new BookAdapter(MainActivity.this, title_array, author_array);
                    list.setAdapter(adapter);
                } catch (JSONException e) {e.printStackTrace();}
            }
        });
    }
}
