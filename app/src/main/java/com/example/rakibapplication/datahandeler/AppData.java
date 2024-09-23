package com.example.rakibapplication.datahandeler;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.rakibapplication.data_model.ItemsModel;
import com.example.rakibapplication.database.api.RetrofitHandler;
import com.example.rakibapplication.database.room.ContentDatabaseRoom;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AppData {
    Context context;
    public List<ItemsModel> gaan_list = new ArrayList<>();
    public List<ItemsModel> gazal_list = new ArrayList<>();
    public List<ItemsModel> quote_list = new ArrayList<>();
    public List<ItemsModel> api_all_list = new ArrayList<>();
    public List<ItemsModel> final_all_list = new ArrayList<>();
    public List<ItemsModel> final_room_list = new ArrayList<>();


    public AppData(Context context) {
        this.context = context;
        getDataFromApi();
        Log.d("empty check gaan list",gaan_list.isEmpty()+"");
    }

    private void getDataFromApi() {
        Call<List<ItemsModel>> api_all_list_call =  RetrofitHandler.getDataServices().getData();
        api_all_list_call.enqueue(new Callback<List<ItemsModel>>() {
            @Override
            public void onResponse(Call<List<ItemsModel>> call, Response<List<ItemsModel>> response) {
                api_all_list =  response.body();
                Log.d("api all data check ",api_all_list.isEmpty()+"");
                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {
                        setApiToRoom();
                        initAllData();
                    }
                });
            }

            @Override
            public void onFailure(Call<List<ItemsModel>> call, Throwable throwable) {

            }
        });
    }

    private void initAllData() {
            for (ItemsModel item : final_all_list) {
                String category = item.getCategory();
                Log.d("CategoryName",category);
                switch (category) {
                    case "Gaan": gaan_list.add(item);
                        break;
                    case "Gazal": gazal_list.add(item);
                        break;
                    case "Quote": quote_list.add(item);
                        break;
                }
        }
    }

    private void setApiToRoom() {

       if (!api_all_list.equals(final_all_list)) {
           ContentDatabaseRoom.getContentRoom(context,"final_content_room").finalDataDao().clearFinalTable();
           ContentDatabaseRoom.getContentRoom(context,"final_content_room").finalDataDao().insertData(api_all_list);
       }
        final_all_list = ContentDatabaseRoom.getContentRoom(context,"final_content_room").finalDataDao().getAllUserData();

       Log.d("final_all_list_check", final_all_list.isEmpty()+"" );
    }

}
