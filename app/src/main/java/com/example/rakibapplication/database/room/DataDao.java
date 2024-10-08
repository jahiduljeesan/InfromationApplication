package com.example.rakibapplication.database.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.rakibapplication.data_model.ItemsModel;

import java.util.List;

import retrofit2.http.GET;

@Dao
public interface DataDao {
    @Insert
    public void insertData(ItemsModel items);

    @Query("SELECT * FROM ItemsModel")
    public List<ItemsModel> getAllData();

    @Update
    public void updateData(ItemsModel items);

    @Query("DELETE FROM ItemsModel")
    public void clearTable();
}
