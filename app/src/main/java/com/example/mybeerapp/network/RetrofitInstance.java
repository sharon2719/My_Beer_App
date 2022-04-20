package com.example.mybeerapp.network;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
    private static Retrofit retrofit;
    private static final String BASE_URL = "https://my-json-server.typicode.com/sharon2719/beer_list.json/";

   private static HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();

    private static OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

    public static GetBeerData getRetrofitInstance(){

        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return  retrofit.create(GetBeerData.class);
    }
}
