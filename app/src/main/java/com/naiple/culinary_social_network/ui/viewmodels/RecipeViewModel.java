package com.naiple.culinary_social_network.ui.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.naiple.culinary_social_network.data.model.Recipe;
import com.naiple.culinary_social_network.data.repositories.RecipeRepository;

import java.util.List;

public class RecipeViewModel extends ViewModel {
    private LiveData<List<Recipe>> recipeLive;
    private RecipeRepository recipeRepository;

    public RecipeViewModel() {
        recipeRepository = new RecipeRepository();
        recipeLive = recipeRepository.getRecipesLive();
    }

    public LiveData<List<Recipe>> getRecipeLive() {
        return recipeLive;
    }

    public void addRecipe(Recipe recipe) {
        recipeRepository.addRecipe(recipe);
    }

    public void removeRecipe(Recipe recipe) {
        recipeRepository.removeRecipe(recipe);
    }
}
