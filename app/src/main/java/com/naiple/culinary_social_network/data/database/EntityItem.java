package com.naiple.culinary_social_network.data.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.naiple.culinary_social_network.data.model.Item;

@Entity
public class EntityItem {
    @PrimaryKey(autoGenerate = true)
    public int uid;
    @ColumnInfo(name = "text")
    private String text;

    public EntityItem(@NonNull String text) {
        this.text = text;
    }

    public int getUid() {
        return uid;
    }

    public String getText() {
        return text;
    }

    public Item toDomainModel() {
        return new Item(text);
    }
}
