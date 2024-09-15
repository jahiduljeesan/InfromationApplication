package com.example.rakibapplication.database.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.rakibapplication.data_model.ItemsModel;

@Database(entities = {ItemsModel.class}, version = 1)
public abstract class ContentDatabaseRoom extends RoomDatabase {
    public abstract DataDao dataDao();
    public static volatile ContentDatabaseRoom contentRoom;

    public static ContentDatabaseRoom getContentRoom(Context context) {
        if (contentRoom == null) {
            contentRoom = Room.databaseBuilder(context,ContentDatabaseRoom.class,"content_db")
                    .build();
        }
        return  contentRoom;
    }

}
