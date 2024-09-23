package com.example.rakibapplication.database.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.rakibapplication.data_model.ItemsModel;

import java.util.List;

@Dao
public interface FinalDataDao {

    @Insert
    void insertData(List<ItemsModel> final_api_list);

    @Query("SELECT * FROM ItemsModel")
    List<ItemsModel> getAllUserData();

    @Query("DELETE FROM ItemsModel")
    public void clearFinalTable();

}
