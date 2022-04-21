package com.example.mybeerapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.mybeerapp.R;
import com.example.mybeerapp.adapter.BeerAdapter;
import com.example.mybeerapp.model.Beer;
import com.example.mybeerapp.model.BeerList;
import com.example.mybeerapp.network.GetBeerData;
import com.example.mybeerapp.network.RetrofitInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private BeerAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Handle for retrofit interface
        GetBeerData data = RetrofitInstance.getRetrofitInstance();

//        Call the method with parameter in the interface to get the beer data
        Call<BeerList> call =data.getBeerData();

//        Log the URL called
        Log.wtf("URL Called",call.request().url() + "");
        call.enqueue(new Callback<BeerList>() {
            @Override
            public void onResponse(Call<BeerList> call, Response<BeerList> response) {
                generateBeerList(response.body().getBeerList());
            }

            @Override
            public void onFailure(Call<BeerList> call, Throwable t) {
                Toast.makeText(MainActivity.this,"Something went wrong ... please try again later",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void generateBeerList(ArrayList<Beer>beerDataList){
        recyclerView =(RecyclerView) findViewById(R.id.rvBeer);
        adapter = new BeerAdapter(beerDataList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}