package com.naiple.culinary_social_network.data.repositories;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Room;

import com.naiple.culinary_social_network.data.database.EntityItem;
import com.naiple.culinary_social_network.data.database.ItemDAO;
import com.naiple.culinary_social_network.data.database.ItemDatabase;
import com.naiple.culinary_social_network.data.datasource.DataRecipeComments;
import com.naiple.culinary_social_network.data.model.Item;
import com.naiple.culinary_social_network.data.model.Recipe;

import java.util.ArrayList;
import java.util.List;

public class RecipeCommentsRepository {
    private List<EntityItem> comments;
    ItemDatabase db;
    ItemDAO itemDao;
    private Context context;
    private final MutableLiveData<List<EntityItem>> commentsLive = new MutableLiveData<>();

    public RecipeCommentsRepository(Context context) {
        this.context = context;
        ItemDatabase db = ItemDatabase.getDatabase(context);
        ItemDAO itemDao = db.itemDao();

        comments = new ArrayList<>();
        comments.add(new EntityItem("Comment1"));
        comments.add(new EntityItem("Comment2"));
        comments.add(new EntityItem("Comment3"));

        itemDao.insertAll(comments);

        commentsLive.setValue(itemDao.getAll());
    }

    public void addRecipeComment(EntityItem comment) {
        itemDao.insert(comment);
        commentsLive.setValue(comments);
    }

    public void removeAllRecipeComment(EntityItem comment) {
        itemDao.deleteAll();
        commentsLive.setValue(comments);
    }

    public LiveData<List<EntityItem>> getRecipeCommentsLive() {
        return commentsLive;
    }

    public LiveData<List<Item>> getRandomData() {
        return DataRecipeComments.createList();
    }
}
