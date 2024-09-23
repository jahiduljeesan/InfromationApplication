package com.example.rakibapplication.database.api;

import com.example.rakibapplication.data_model.ItemsModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface DataServices {

    @POST("items/additem")
    Call<List<ItemsModel>> postData(@Body List<ItemsModel> itemsModelList);

    @GET("items/getitems")
    Call<List<ItemsModel>> getData();



}
