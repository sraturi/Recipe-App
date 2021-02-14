package com.example.myrecipeapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    List<RecipeItem> items;

    RecyclerAdapter(List<RecipeItem> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RecipeItem recipeItem = items.get(position);
        TextView titleView = holder.itemView.findViewById(R.id.recipeTitleView);
        ImageView imageView = holder.itemView.findViewById(R.id.recipeImageview);

        titleView.setText(recipeItem.title);
        // Glide fetches the image from url and loads it into the imageview we provide.
        Glide.with(imageView).load(recipeItem.imageUrl).into(imageView);

        /*
         *If user clicks a single item from the list. we display a toast message with item information.
         * todo can we use intent to launch the browser application and search for the actual recipe?
         *  hint: YES!
         */
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(holder.itemView.getContext(),recipeItem.toString(),Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
