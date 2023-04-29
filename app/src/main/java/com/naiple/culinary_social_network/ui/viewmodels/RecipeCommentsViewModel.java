package com.naiple.culinary_social_network.ui.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.naiple.culinary_social_network.data.model.Item;
import com.naiple.culinary_social_network.data.model.Recipe;
import com.naiple.culinary_social_network.data.repositories.RecipeCommentsRepository;

import java.util.List;

public class RecipeCommentsViewModel extends ViewModel {
    private LiveData<List<Item>> comments;
    private RecipeCommentsRepository recipeCommentsRepository;

    public RecipeCommentsViewModel() {
        recipeCommentsRepository = new RecipeCommentsRepository();
        comments = recipeCommentsRepository.getRandomData();
    }

    public LiveData<List<Item>> getRecipeCommentsLive() {
        return comments;
    }

    public void addRecipeComment(Item comment) {
        recipeCommentsRepository.addRecipeComment(comment);
    }

    public void removeRecipeComment(Item comment) {
        recipeCommentsRepository.removeRecipeComment(comment);
    }
}
