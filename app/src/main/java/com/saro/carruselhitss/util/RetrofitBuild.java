package com.saro.carruselhitss.util;

import com.saro.carruselhitss.service.JSONService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitBuild
{
    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

public static final JSONService apiService = retrofit.create(JSONService.class);
}
