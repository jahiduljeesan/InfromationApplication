package com.example.rakibapplication.database.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.rakibapplication.data_model.ItemsModel;

@Database(entities = {ItemsModel.class}, version = 1)
public abstract class ContentDatabaseRoom extends RoomDatabase {
    public abstract DataDao dataDao();
    public abstract FinalDataDao finalDataDao();
    public static volatile ContentDatabaseRoom contentRoom;

    public static ContentDatabaseRoom getContentRoom(Context context,String table_name) {
        if (contentRoom == null) {
            contentRoom = Room.databaseBuilder(context,ContentDatabaseRoom.class,table_name)
                    .build();
        }
        return  contentRoom;
    }

//    public static ContentDatabaseRoom getFinalContentRoom(Context context) {
//        if (contentRoom != null) {
//            contentRoom = Room.databaseBuilder(context,ContentDatabaseRoom.class,"final_content_db")
//                    .build();
//        }
//        return contentRoom;
//    }

}
