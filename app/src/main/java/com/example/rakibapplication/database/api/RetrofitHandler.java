package com.example.rakibapplication.database.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHandler {

    static DataServices dataServices;
    static final String BASE_URL = "https://water-supplier.vercel.app/";

    public static DataServices getDataServices() {
        if (dataServices == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            dataServices = retrofit.create(DataServices.class);
        }
        return dataServices;
    }
}
