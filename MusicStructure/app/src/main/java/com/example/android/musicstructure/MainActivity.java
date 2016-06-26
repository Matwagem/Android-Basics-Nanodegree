/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.musicstructure;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the content of the activity to use the activity_main.xml layout file
        setContentView(R.layout.activity_main);

        // Find the View that shows the numbers category
        TextView libraryVar = (TextView) findViewById(R.id.library);

        // Set a click listener on that View
        libraryVar.setOnClickListener(new OnClickListener() {
            // The code in this method will be executed when the numbers category is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link NumbersActivity}
                Intent libraryIntent = new Intent(MainActivity.this, LibraryActivity.class);

                // Start the new activity
                startActivity(libraryIntent);
            }
        });

        // Find the View that shows the family category
        TextView paymentVar = (TextView) findViewById(R.id.payment);

        // Set a click listener on that View
        paymentVar.setOnClickListener(new OnClickListener() {
            // The code in this method will be executed when the family category is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link FamilyActivity}
                Intent paymentIntent = new Intent(MainActivity.this, PaymentActivity.class);

                // Start the new activity
                startActivity(paymentIntent);
            }
        });

        // Find the View that shows the colors category
        TextView playerVar = (TextView) findViewById(R.id.player);

        // Set a click listener on that View
        playerVar.setOnClickListener(new OnClickListener() {
            // The code in this method will be executed when the colors category is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link ColorsActivity}
                Intent playerIntent = new Intent(MainActivity.this, PlayerActivity.class);

                // Start the new activity
                startActivity(playerIntent);
            }
        });

        // Find the View that shows the phrases category
        TextView storeVar = (TextView) findViewById(R.id.store);

        // Set a click listener on that View
        storeVar.setOnClickListener(new OnClickListener() {
            // The code in this method will be executed when the phrases category is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link PhrasesActivity}
                Intent storeIntent = new Intent(MainActivity.this, StoreActivity.class);

                // Start the new activity
                startActivity(storeIntent);
            }
        });
    }
}
