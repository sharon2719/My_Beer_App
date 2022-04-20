package com.example.mybeerapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class BeerList {
    @SerializedName("beerList")
    private ArrayList<Beer> beerList;

    public ArrayList<Beer> getBeerList() {
        return beerList;
    }

    public void setBeerList(ArrayList<Beer> beerList) {
        this.beerList = beerList;
    }
}
