package com.example.mybeerapp.network;

import com.example.mybeerapp.model.BeerList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetBeerData {
    @GET("db")
    Call<BeerList>getBeerData();
}
