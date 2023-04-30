package com.naiple.culinary_social_network.data.repositories;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import com.naiple.culinary_social_network.data.database.EntityItem;
import com.naiple.culinary_social_network.data.database.ItemDatabase;
import com.naiple.culinary_social_network.data.datasource.DataRecipeComments;
import com.naiple.culinary_social_network.data.model.Item;

import java.util.List;
import java.util.stream.Collectors;

public class RecipeCommentsRepository {
    ItemDatabase databaseSource;

    private DataRecipeComments dataRecipeComments;
    private Context context;

    public RecipeCommentsRepository(Context context) {
        this.context = context;
        databaseSource = ItemDatabase.getDatabase(context);
        dataRecipeComments = new DataRecipeComments();
    }

    public void addRecipeComment(Item comment) {
        ItemDatabase.databaseWriteExecutor.execute(() -> {
            databaseSource.itemDao().insert(new EntityItem(comment.getName()));
        });
    }

    public void removeAllRecipeComment() {
        databaseSource.itemDao().deleteAll();
    }

    public void saveToFile(String fileName, String data, Context context) {
        DataRecipeComments.saveToFile(fileName, data, context);
    }

    public void saveToFileExternalStorage(String fileName, String data, Context context) {
        DataRecipeComments.saveToFileExternalStorage(fileName, data, context);
    }

    public void saveToFileSharedStorage(String key, String data, SharedPreferences sharedPreferences) {
        DataRecipeComments.saveToFileSharedStorage(key, data, sharedPreferences);
    }

    public LiveData<List<Item>> getRecipeCommentsLive() {
        return Transformations.map(
                databaseSource.itemDao().getAll(),
                (values) -> values.stream().map(EntityItem::toDomainModel).collect(Collectors.toList())
        );
    }

    public LiveData<List<Item>> getRandomData() {
        return DataRecipeComments.createList();
    }
}
