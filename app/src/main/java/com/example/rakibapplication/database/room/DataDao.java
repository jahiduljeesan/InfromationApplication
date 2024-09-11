package com.example.rakibapplication.database.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Update;

import com.example.rakibapplication.ItemsModel;

@Dao
public interface DataDao {
    @Insert
    void insertData(ItemsModel items);
    @Update
    void updateData(ItemsModel items);
}
