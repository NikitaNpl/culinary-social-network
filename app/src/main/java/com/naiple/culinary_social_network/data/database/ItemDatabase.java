package com.naiple.culinary_social_network.data.database;

import android.content.Context;

import androidx.room.*;

@Database(entities = {EntityItem.class}, version = 1)
public abstract class ItemDatabase extends RoomDatabase {
    public abstract ItemDAO itemDao();

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
