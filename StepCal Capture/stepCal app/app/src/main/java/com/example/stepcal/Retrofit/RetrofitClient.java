package com.example.stepcal.Retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    public static Retrofit retrofit;

    private static final String base_url="http://172.20.92.35:4006";

    public static Retrofit getRetrofit(){
        if(retrofit==null){
            retrofit=new Retrofit.Builder().baseUrl(base_url).
                    addConverterFactory(GsonConverterFactory.
                            create()).
                    build();
        }
        return retrofit;
    }
}
