package com.naiple.culinary_social_network.ui.viewmodels;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

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

    public void init(Context context) {
        this.context = context;
        recipeCommentsRepository = new RecipeCommentsRepository();
    }

    public LiveData<List<Item>> getRecipeCommentsLive() {
        return recipeCommentsRepository.getRandomData();
    }

    public void addRecipeComment(Item comment) {
        class FileStoreUtility {
            public void saveToFile(String fileName, String data, Context context) {
                File file = new File(context.getFilesDir(), fileName + ".txt");
                try {
                    FileOutputStream fos = new FileOutputStream(file);
                    fos.write(data.getBytes());
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        FileStoreUtility fsu = new FileStoreUtility();
        fsu.saveToFile("LastAddedComment", comment.getName(), context);

        recipeCommentsRepository.addRecipeComment(comment);
    }

    public void removeRecipeComment(Item comment) {
        recipeCommentsRepository.removeRecipeComment(comment);
    }
}
