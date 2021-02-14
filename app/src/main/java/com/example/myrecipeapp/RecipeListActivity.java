package com.example.myrecipeapp;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RecipeListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);

        // we find the go back button and once user clicks it, we destroy the current activity
        //so we can go back to the previous one.
        findViewById(R.id.goBackButton).setOnClickListener(v -> finish());

        String query = "";
        if (getIntent().hasExtra("query")) {
            query = getIntent().getStringExtra("query");
        }

        fetchRecipesFromNetwork(query);

    }

    /**
     * make a network call
     * get API key at https://spoonacular.com/food-api/console#Dashboard
     *
     * @param query
     */
    private void fetchRecipesFromNetwork(String query) {
        String apiKey = "ENTER YOUR KEY HERE";
        String url = "https://api.spoonacular.com/recipes/complexSearch?apiKey=+" + apiKey + "&query=" + query;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, response -> {
            try {
                /**
                 * First we parse the data and than setup a recycler view with the data.
                 */
                Log.d("data", response.toString(4));
                JSONArray jsonArray = response.getJSONArray("results");
                List<RecipeItem> items = new ArrayList<>();

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject object = jsonArray.getJSONObject(i);
                    items.add(new RecipeItem(object.getString("title"), object.getString("image"), object.getInt("id")));
                }
                setupRecycler(items);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> {
            /**
             * In case of Error, we want to setup a dummy recyler view
             */
            Log.d("data", error.toString());
            List<RecipeItem> items = new ArrayList<>();

            for (int i = 0; i < 100; i++) {
                items.add(new RecipeItem(query + " " + i, "", i));
            }
            setupRecycler(items);
        });

        Volley.newRequestQueue(this).add(jsonObjectRequest);
    }

    private void setupRecycler(List<RecipeItem> items) {
        RecyclerView recyclerView = findViewById(R.id.recyclerview);

        recyclerView.setAdapter(new RecyclerAdapter(items));
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
    }
}