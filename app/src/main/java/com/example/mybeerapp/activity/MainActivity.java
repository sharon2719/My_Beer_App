package com.example.mybeerapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.mybeerapp.R;
import com.example.mybeerapp.adapter.BeerAdapter;
import com.example.mybeerapp.database.SqliteDatabase;
import com.example.mybeerapp.model.Beer;
import com.example.mybeerapp.model.BeerList;
import com.example.mybeerapp.network.GetBeerData;
import com.example.mybeerapp.network.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private BeerAdapter adapter;
    private RecyclerView recyclerView;

    private SqliteDatabase myDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView beerView = findViewById(R.id.rvBeer);
        LinearLayoutManager layoutManager =new LinearLayoutManager(this);
        beerView.setLayoutManager(layoutManager);
        beerView.setHasFixedSize(true);
        myDatabase = new SqliteDatabase(this);

        Log.e("description", String.valueOf(R.id.tvDescription));

//        Handle for retrofit interface
        GetBeerData data = RetrofitInstance.getRetrofitInstance();

//        Call the method with parameter in the interface to get the beer data
        Call<BeerList> call =data.getBeerData();

//        Log the URL called
        Log.wtf("URL Called",call.request().url() + "");
        call.enqueue(new Callback<BeerList>() {

            @Override
            public void onResponse(Call<BeerList> call, Response<BeerList> response) {

                myDatabase.insertBeerList(response.body().getBeerList());
                generateBeerList();
            }

            @Override
            public void onFailure(Call<BeerList> call, Throwable t) {
                Toast.makeText(MainActivity.this,"Something went wrong ... please try again later",Toast.LENGTH_SHORT).show();
                generateBeerList();
                Log.e("app failure",t.getMessage());
            }
        });
    }

    private void generateBeerList(){
        recyclerView =(RecyclerView) findViewById(R.id.rvBeer);
        adapter = new BeerAdapter(myDatabase.beersList());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}