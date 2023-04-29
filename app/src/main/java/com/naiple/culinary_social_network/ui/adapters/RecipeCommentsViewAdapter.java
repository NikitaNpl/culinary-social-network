package com.naiple.culinary_social_network.ui.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.naiple.culinary_social_network.R;
import com.naiple.culinary_social_network.data.model.Item;

import java.util.ArrayList;
import java.util.List;

public class RecipeCommentsViewAdapter extends RecyclerView.Adapter<RecipeCommentsViewAdapter.RecipeCommentsViewAdapterHolder> {
    String TAG = "CommentsRecipeCardFragment";
    Context context;
    List<Item> comments;

    public RecipeCommentsViewAdapter(Context context) {
        this.context = context;
        this.comments = new ArrayList<>();
    }

    public final static class RecipeCommentsViewAdapterHolder extends RecyclerView.ViewHolder {
        TextView text;
        RecyclerView comments;

        public RecipeCommentsViewAdapterHolder(@NonNull View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.listTextValue);
        }
    }

    @NonNull
    @Override
    public RecipeCommentsViewAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View items = LayoutInflater.from(context).inflate(R.layout.fragment_list_element, parent, false);
        return new RecipeCommentsViewAdapterHolder(items);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeCommentsViewAdapterHolder holder, int position) {
        Item comment = comments.get(position);
        holder.text.setText(comment.getName());
        holder.itemView.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "Нажали на странице с комментариями");
                Toast toast = Toast.makeText(v.getContext(), "нажали на " +
                        (position+1), Toast.LENGTH_LONG );
                toast.show();
            }});
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }


    @SuppressLint("NotifyDataSetChanged")
    public void updateComments(List<Item> comments) {
        this.comments.clear();
        this.comments = comments;
        notifyDataSetChanged();
    }
}
