package com.naiple.culinary_social_network.ui.viewmodels;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.naiple.culinary_social_network.data.database.EntityItem;
import com.naiple.culinary_social_network.data.database.ItemDAO;
import com.naiple.culinary_social_network.data.model.Item;
import com.naiple.culinary_social_network.data.model.Recipe;
import com.naiple.culinary_social_network.data.repositories.RecipeCommentsRepository;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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

    public LiveData<List<EntityItem>> getRecipeCommentsLive() {
        return recipeCommentsRepository.getRecipeCommentsLive();
    }

    public void addRecipeComment(EntityItem comment) {
        recipeCommentsRepository.saveToFileSharedStorage("LastAddedComment", comment.getText(), sharedPreferences);
        recipeCommentsRepository.addRecipeComment(comment);
    }
}
