package com.example.rakibapplication.database.api;

import com.example.rakibapplication.data_model.ItemsModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface DataServices {

    @POST
    Call<ItemsModel> postData(@Body ItemsModel itemsModel);

}
