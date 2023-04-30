package com.naiple.culinary_social_network.ui.viewmodels;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.naiple.culinary_social_network.data.database.EntityItem;
import com.naiple.culinary_social_network.data.model.Item;
import com.naiple.culinary_social_network.data.repositories.RecipeCommentsRepository;

import java.util.List;

public class RecipeCommentsViewModel extends ViewModel {
    private RecipeCommentsRepository recipeCommentsRepository;
    private Context context;

    private SharedPreferences sharedPreferences;

    public void init(Context context, SharedPreferences sharedPrefs) {
        this.context = context;
        this.sharedPreferences = sharedPrefs;
        recipeCommentsRepository = new RecipeCommentsRepository(context);
    }

    public LiveData<List<Item>> getRecipeCommentsLive() {
        return recipeCommentsRepository.getRecipeCommentsLive();
    }

    public void addRecipeComment(Item comment) {
        recipeCommentsRepository.saveToFile("LastAddedComment", comment.getName(), context);
        recipeCommentsRepository.saveToFileExternalStorage("LastAddedComment", comment.getName(), context);
        recipeCommentsRepository.saveToFileSharedStorage("LastAddedComment", comment.getName(), sharedPreferences);
        recipeCommentsRepository.addRecipeComment(comment);
    }
}
