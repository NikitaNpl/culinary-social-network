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

    public void init(Context context, SharedPreferences sharedPrefs) {
        this.context = context;
        recipeCommentsRepository = new RecipeCommentsRepository(context);
    }

    public LiveData<List<EntityItem>> getRecipeCommentsLive() {
        return recipeCommentsRepository.getRecipeCommentsLive();
    }

    public void addRecipeComment(EntityItem comment) {
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

            public void saveToFileSharedStorage(String fileName, String data, Context context) {
                String type = Environment.DIRECTORY_DOWNLOADS;
                File file = new File(Environment.getExternalStoragePublicDirectory(type), fileName + ".txt");


                try {
                    FileOutputStream fos = new FileOutputStream(file);
                    fos.write(data.getBytes());
                    fos.close();
                    Log.i("test", String.valueOf(file));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        FileStoreUtility fsu = new FileStoreUtility();
        fsu.saveToFile("LastAddedComment", comment.getText(), context);
        fsu.saveToFileSharedStorage("LastAddedComment", comment.getText(), context);

        recipeCommentsRepository.addRecipeComment(comment);
    }
}
