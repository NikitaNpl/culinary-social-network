package com.naiple.culinary_social_network.data.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.naiple.culinary_social_network.data.datasource.DataRecipeComments;
import com.naiple.culinary_social_network.data.model.Item;
import com.naiple.culinary_social_network.data.model.Recipe;

import java.util.ArrayList;
import java.util.List;

public class RecipeCommentsRepository {
    private List<Item> comments;
    private final MutableLiveData<List<Item>> commentsLive = new MutableLiveData<>();

    public RecipeCommentsRepository() {
        comments = new ArrayList<>();
        comments.add(new Item("UserName1"));
        comments.add(new Item("UserName2"));
        comments.add(new Item("UserName3"));
        commentsLive.setValue(comments);
    }

    public void addRecipeComment(Item comment) {
        comments.add(comment);
        commentsLive.setValue(comments);
    }

    public void removeRecipeComment(Item comment) {
        comments.remove(comment);
        commentsLive.setValue(comments);
    }

    public LiveData<List<Item>> getRecipeCommentsLive() {
        return commentsLive;
    }

    public LiveData<List<Item>> getRandomData() {
        return DataRecipeComments.createList();
    }
}
