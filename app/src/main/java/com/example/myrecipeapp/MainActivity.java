package com.example.myrecipeapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button searchButton = findViewById(R.id.searchButton);
        EditText searchText = findViewById(R.id.searchEditText);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (searchText.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "You entered nothing... ", Toast.LENGTH_SHORT).show();
                    return;
                }
                // if user entered something, we want to show the results.
                Intent intent = new Intent(MainActivity.this, RecipeListActivity.class);
                intent.putExtra("query", searchText.getText().toString());
                startActivity(intent);

            }
        });

    }

}