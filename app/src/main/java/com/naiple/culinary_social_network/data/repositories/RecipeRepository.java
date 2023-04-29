package com.naiple.culinary_social_network.data.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.naiple.culinary_social_network.data.datasource.DataRecipeComments;
import com.naiple.culinary_social_network.data.model.Recipe;

import java.util.ArrayList;
import java.util.List;

public class RecipeRepository {
    private List<Recipe> recipes;
    private final MutableLiveData<List<Recipe>> recipesLive = new MutableLiveData<>();

    public RecipeRepository() {
        recipes = new ArrayList<>();
        recipes.add(new Recipe("Honey Cake"));
        recipes.add(new Recipe("Lasagna"));
        recipes.add(new Recipe("Pepperoni pizza"));
        recipesLive.setValue(recipes);
    }

    public void addRecipe(Recipe recipe) {
        recipes.add(recipe);
        recipesLive.setValue(recipes);
    }

    public void removeRecipe(Recipe recipe) {
        recipes.remove(recipe);
        recipesLive.setValue(recipes);
    }

    public LiveData<List<Recipe>> getRecipesLive() {
        return recipesLive;
    }
}
