package com.naiple.culinary_social_network.data.database;

import android.content.Context;

import androidx.room.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {EntityItem.class}, version = 1)
public abstract class ItemDatabase extends RoomDatabase {
    public abstract ItemDAO itemDao();

    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static ItemDatabase INSTANCE;

    public static ItemDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ItemDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    ItemDatabase.class, "title database").allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
