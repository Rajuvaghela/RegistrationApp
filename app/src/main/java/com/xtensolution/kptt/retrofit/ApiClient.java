package com.xtensolution.kptt.retrofit;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiClient {
    public static final String BASE_URL = "http://register.hillcrossfinance.co.za/";
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.connectTimeout(90, TimeUnit.SECONDS);
        client.readTimeout(90, TimeUnit.SECONDS);
        client.writeTimeout(90, TimeUnit.SECONDS);

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client.build())
                    .build();
        }
        return retrofit;
    }/*
    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }*/
}