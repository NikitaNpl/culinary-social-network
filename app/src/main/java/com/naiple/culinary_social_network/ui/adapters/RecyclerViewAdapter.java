package com.naiple.culinary_social_network.ui.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.naiple.culinary_social_network.R;
import com.naiple.culinary_social_network.data.model.Recipe;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    Context context;
    List<Recipe> recipes;

    public RecyclerViewAdapter(Context context) {
        this.context = context;
        this.recipes = new ArrayList<>();
    }

    public final static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView title;
        final ImageView image;
        ViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.recipeName);
            image = view.findViewById(R.id.recipePicture);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recipe_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        Recipe item = recipes.get(position);
        holder.title.setText(item.getTitle());
        if (item.getImageId() != 0) {
            holder.image.setImageResource(item.getImageId());
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("Adapter", "WORK");
            }
        });
    }

    @SuppressLint("NotifyDataSetChanged")
    public void updateRecipes(List<Recipe> recipes) {
        this.recipes.clear();
        this.recipes = recipes;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }
}
