package com.example.rakibapplication.datahandeler;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.rakibapplication.data_model.ItemsModel;
import com.example.rakibapplication.database.api.RetrofitHandler;
import com.example.rakibapplication.database.room.ContentDatabaseRoom;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RoomToApi {

    List<ItemsModel> roomAllUserData;
    Context context;

    public RoomToApi(Context context) {
        this.context = context;
        getRoomAllUserData();
        postDataFromRoom();
        Log.d("checkFetch","data is fetched from room");
    }

    List<ItemsModel> getRoomAllUserData() {
        return roomAllUserData = ContentDatabaseRoom.getContentRoom(context,"content_room")
                .dataDao().getAllData();
    }

    void postDataFromRoom() {

        if (roomAllUserData.isEmpty()) return;

        Call<List<ItemsModel>> listCall =  RetrofitHandler.getDataServices()
                .postData(roomAllUserData);

        listCall.enqueue(new Callback<List<ItemsModel>>() {
            @Override
            public void onResponse(Call<List<ItemsModel>> call, Response<List<ItemsModel>> response) {
                deleteAllDataRoom();
                Log.d("Check Api Data Fetch","Working");
            }

            @Override
            public void onFailure(Call<List<ItemsModel>> call, Throwable throwable) {
                throwable.printStackTrace();
            }
        });
    }

    private void deleteAllDataRoom() {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                ContentDatabaseRoom.getContentRoom(context,"content_room").dataDao().clearTable();
            }
        });
    }

}
